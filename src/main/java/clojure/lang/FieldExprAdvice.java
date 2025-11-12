package clojure.lang;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import clojure.asm.commons.GeneratorAdapter;
import clojure.java.api.Clojure;
import clojure.lang.Compiler.FieldExpr;
import clojure.lang.Compiler.ObjExpr;

public final class FieldExprAdvice {
  public final static IFn handler;
  public final static Keyword onEnter = Keyword.intern("field-expr/enter");
  public final static Keyword onExit = Keyword.intern("field-expr/exit");

  static {
    final IFn require = Clojure.var("clojure.core", "require");
    require.invoke(Clojure.read("koala.instrumentation"));
    handler = Clojure.var("koala.instrumentation", "handler");
  }

  public static void instrument(final ClassReloadingStrategy strategy) {
    new ByteBuddy()
        .redefine(FieldExpr.class)
        .visit(Advice.to(FieldExprAdvice.class)
            .on(ElementMatchers.named("emit")
                .and(ElementMatchers.takesArguments(3))))
        .make()
        .load(FieldExpr.class.getClassLoader(), strategy);
  }

  @Advice.OnMethodEnter
  public static void onMethodEnter(
      @Advice.This FieldExpr expr,
      @Advice.Argument(0) Compiler.C context,
      @Advice.Argument(1) ObjExpr objx,
      @Advice.Argument(2) GeneratorAdapter gen) {
    if (handler != null && expr != null) {
      handler.invoke(onEnter, expr, context, objx, gen);
    }
  }

  @Advice.OnMethodExit
  public static void onMethodExit(
      @Advice.This FieldExpr expr,
      @Advice.Argument(0) Compiler.C context,
      @Advice.Argument(1) ObjExpr objx,
      @Advice.Argument(2) GeneratorAdapter gen) {
    if (handler != null && expr != null) {
      handler.invoke(onExit, expr, context, objx, gen);
    }
  }

}
