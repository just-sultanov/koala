package clojure.lang;

import java.util.List;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import clojure.asm.commons.GeneratorAdapter;

import clojure.lang.Compiler.*;

import koala.Api;

public final class Instrument {

  public enum ExprKind {
    AssignExpr(AssignExpr.class),
    BodyExpr(BodyExpr.class),
    BooleanExpr(BooleanExpr.class),
    CaseExpr(CaseExpr.class),
    ConstantExpr(ConstantExpr.class),
    DefExpr(DefExpr.class),
    EmptyExpr(EmptyExpr.class),
    FieldExpr(FieldExpr.class),
    FnExpr(FnExpr.class),
    HostExpr(HostExpr.class),
    IfExpr(IfExpr.class),
    ImportExpr(ImportExpr.class),
    InstanceFieldExpr(InstanceFieldExpr.class),
    InstanceOfExpr(InstanceOfExpr.class),
    InvokeExpr(InvokeExpr.class),
    KeywordExpr(KeywordExpr.class),
    KeywordInvokeExpr(KeywordInvokeExpr.class),
    LetExpr(LetExpr.class),
    LetFnExpr(LetFnExpr.class),
    ListExpr(ListExpr.class),
    LiteralExpr(LiteralExpr.class),
    LocalBindingExpr(LocalBindingExpr.class),
    MapExpr(MapExpr.class),
    MetaExpr(MetaExpr.class),
    MethodParamExpr(MethodParamExpr.class),
    MonitorEnterExpr(MonitorEnterExpr.class),
    MonitorExitExpr(MonitorExitExpr.class),
    NewExpr(NewExpr.class),
    NewInstanceExpr(NewInstanceExpr.class),
    NilExpr(NilExpr.class),
    NumberExpr(NumberExpr.class),
    ObjExpr(ObjExpr.class),
    QualifiedMethodExpr(QualifiedMethodExpr.class),
    RecurExpr(RecurExpr.class),
    SetExpr(SetExpr.class),
    StaticInvokeExpr(StaticInvokeExpr.class),
    StringExpr(StringExpr.class),
    TheVarExpr(TheVarExpr.class),
    ThrowExpr(ThrowExpr.class),
    TryExpr(TryExpr.class),
    UnresolvedVarExpr(UnresolvedVarExpr.class),
    UntypedExpr(UntypedExpr.class),
    VarExpr(VarExpr.class),
    VectorExpr(VectorExpr.class);

    private final Class<?> clazz;

    ExprKind(Class<?> clazz) {
      this.clazz = clazz;
    }

    public Class<?> getClazz() {
      return clazz;
    }

    public static List<ExprKind> asList() {
      return List.of(values());
    }
  }

  @Advice.OnMethodEnter
  public static void onMethodEnter(
      @Advice.Origin("#m") String method,
      @Advice.This Compiler.Expr expr,
      @Advice.Argument(0) Compiler.C context,
      @Advice.Argument(1) Compiler.ObjExpr objx,
      @Advice.Argument(2) GeneratorAdapter gen) {
    final IPersistentMap opts = InstrumentUtils.makeOpts(method, expr, context, objx, gen);
    Api.invoke(opts);
  }

  @Advice.OnMethodExit
  public static void onMethodExit(
      @Advice.Origin("#m") String method,
      @Advice.This Compiler.Expr expr,
      @Advice.Argument(0) Compiler.C context,
      @Advice.Argument(1) Compiler.ObjExpr objx,
      @Advice.Argument(2) GeneratorAdapter gen) {
    final IPersistentMap opts = InstrumentUtils.makeOpts(method, expr, context, objx, gen);
    Api.invoke(opts);
  }

  public static void instrument() {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    instrument(ExprKind.asList(), strategy);
  }

  public static void instrument(final ClassReloadingStrategy strategy) {
    instrument(ExprKind.asList(), strategy);
  }

  public static void instrument(final ExprKind exprKind, final ClassReloadingStrategy strategy) {
    new ByteBuddy()
        .redefine(exprKind.getClazz())
        .visit(Advice.to(Instrument.class)
            .on(ElementMatchers.named("emit")
                .and(ElementMatchers.takesArguments(3))))
        .make()
        .load(exprKind.getClazz().getClassLoader(), strategy);
  }

  public static void instrument(final List<ExprKind> exprKinds, final ClassReloadingStrategy strategy) {
    for (ExprKind exprKind : exprKinds) {
      new ByteBuddy()
          .redefine(exprKind.getClazz())
          .visit(Advice.to(Instrument.class)
              .on(ElementMatchers.named("emit")
                  .and(ElementMatchers.takesArguments(3))))
          .make()
          .load(exprKind.getClazz().getClassLoader(), strategy);
    }
  }

}
