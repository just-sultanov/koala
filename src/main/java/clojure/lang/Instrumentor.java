package clojure.lang;

import java.util.List;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import clojure.asm.commons.GeneratorAdapter;
import clojure.lang.Compiler.*;

import koala.Api;

public final class Instrumentor {

  public static IPersistentMap config = PersistentHashMap.EMPTY;

  public static void setConfig(final IPersistentMap config) {
    Instrumentor.config = config;
  }

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

    ExprKind(final Class<?> clazz) {
      this.clazz = clazz;
    }

    public Class<?> getClazz() {
      return clazz;
    }

    public static List<ExprKind> asList() {
      return List.of(values());
    }
  }

  public static class EmitAdvice {

    @Advice.OnMethodEnter
    public static void onMethodEnter(
        @Advice.Origin("#m") final String methodName,
        @Advice.This final Compiler.Expr expr,
        @Advice.Argument(0) final Compiler.C context,
        @Advice.Argument(1) final Compiler.ObjExpr objx,
        @Advice.Argument(2) final GeneratorAdapter gen) {
      final String method = methodName + "Enter";
      final IPersistentMap data = Extractor.extract(method, expr, context, objx, gen);
      Api.invoke(config, data);
    }

    @Advice.OnMethodExit
    public static void onMethodExit(
        @Advice.Origin("#m") final String methodName,
        @Advice.This final Compiler.Expr expr,
        @Advice.Argument(0) final Compiler.C context,
        @Advice.Argument(1) final Compiler.ObjExpr objx,
        @Advice.Argument(2) final GeneratorAdapter gen) {
      final String method = methodName + "Exit";
      final IPersistentMap data = Extractor.extract(method, expr, context, objx, gen);
      Api.invoke(config, data);
    }

  }

  public static void instrumentEmit(final ExprKind kind, final ClassReloadingStrategy strategy) {
    new ByteBuddy()
        .redefine(kind.getClazz())
        .visit(Advice.to(Instrumentor.EmitAdvice.class)
            .on(ElementMatchers.named("emit")
                .and(ElementMatchers.takesArguments(3))))
        .make()
        .load(kind.getClazz().getClassLoader(), strategy);
  }

  public static void instrument() {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    instrument(ExprKind.asList(), strategy);
  }

  public static void instrument(final ClassReloadingStrategy strategy) {
    instrument(ExprKind.asList(), strategy);
  }

  public static void instrument(final ExprKind kind, final ClassReloadingStrategy strategy) {
    instrumentEmit(kind, strategy);
  }

  public static void instrument(final List<ExprKind> kinds, final ClassReloadingStrategy strategy) {
    kinds.forEach(kind -> instrumentEmit(kind, strategy));
  }

}
