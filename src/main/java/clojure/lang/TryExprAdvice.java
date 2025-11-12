package clojure.lang;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import clojure.asm.commons.GeneratorAdapter;
import clojure.java.api.Clojure;
import clojure.lang.Compiler.ObjExpr;
import clojure.lang.Compiler.TryExpr;

public final class TryExprAdvice {
  public final static IFn handler;
  public final static Keyword onEnter = Keyword.intern("try-expr/enter");
  public final static Keyword onExit = Keyword.intern("try-expr/exit");

  static {
    final IFn require = Clojure.var("clojure.core", "require");
    require.invoke(Clojure.read("koala.instrumentation"));
    handler = Clojure.var("koala.instrumentation", "handler");
  }

  public static void instrument(final ClassReloadingStrategy strategy) {
    new ByteBuddy()
        .redefine(TryExpr.class)
        .visit(Advice.to(TryExprAdvice.class)
            .on(ElementMatchers.named("emit")
                .and(ElementMatchers.takesArguments(3))))
        .make()
        .load(TryExpr.class.getClassLoader(), strategy);
  }

  @Advice.OnMethodEnter
  public static void onMethodEnter(
      @Advice.This TryExpr expr,
      @Advice.Argument(0) Compiler.C context,
      @Advice.Argument(1) ObjExpr objx,
      @Advice.Argument(2) GeneratorAdapter gen) {
    if (handler != null && expr != null) {
      handler.invoke(onEnter, expr, context, objx, gen);
    }
  }

  @Advice.OnMethodExit
  public static void onMethodExit(
      @Advice.This TryExpr expr,
      @Advice.Argument(0) Compiler.C context,
      @Advice.Argument(1) ObjExpr objx,
      @Advice.Argument(2) GeneratorAdapter gen) {
    if (handler != null && expr != null) {
      handler.invoke(onExit, expr, context, objx, gen);
    }
  }

}
