package clojure.lang;

import clojure.asm.commons.GeneratorAdapter;
import clojure.lang.Compiler.*;

import koala.Api;

public final class Extractor {

  public final static Keyword contextKey = Keyword.intern("context");
  public final static Keyword contextStatementKey = Keyword.intern("statement");
  public final static Keyword contextExpressionKey = Keyword.intern("expression");
  public final static Keyword contextReturnKey = Keyword.intern("return");

  public final static Keyword contextEvalKey = Keyword.intern("eval");
  public final static Keyword methodKey = Keyword.intern("method");
  public final static Keyword varKey = Keyword.intern("var");
  public final static Keyword metaKey = Keyword.intern("meta");
  public final static Keyword fexprKey = Keyword.intern("fexpr");

  public static Keyword asMethod(final String method, final Compiler.Expr expr) {
    return Keyword.intern(expr.getClass().getSimpleName(), method);
  }

  public static Keyword asContext(final Compiler.C context) {
    return switch (context) {
      case STATEMENT -> contextStatementKey;
      case EXPRESSION -> contextExpressionKey;
      case RETURN -> contextReturnKey;
      case EVAL -> contextEvalKey;
    };
  }

  public static IPersistentMap extractGen(final GeneratorAdapter gen) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient();
    return info.persistent();
  }

  public static IPersistentMap extract(
      final String method,
      final Compiler.Expr expr,
      final Compiler.C context,
      final Compiler.ObjExpr objx,
      final GeneratorAdapter gen) {
    return extract(expr)
        .assoc(methodKey, asMethod(method, expr))
        .assoc(contextKey, asContext(context));
  }

  public static IPersistentMap extract(final Compiler.Expr expr) {
    return switch (expr) {
      // case AssignExpr e -> extract(e);
      // case BodyExpr e -> extract(e);
      // case CaseExpr e -> extract(e);
      case DefExpr e -> extract(e);
      // case FieldExpr e -> extract(e);
      // case FnExpr e -> extract(e);
      // case HostExpr e -> extract(e);
      // case IfExpr e -> extract(e);
      // case ImportExpr e -> extract(e);
      // case InstanceFieldExpr e -> extract(e);
      // case InstanceOfExpr e -> extract(e);
      case InvokeExpr e -> extract(e);
      // case KeywordExpr e -> extract(e);
      // case KeywordInvokeExpr e -> extract(e);
      // case LetExpr e -> extract(e);
      // case LetFnExpr e -> extract(e);
      // case ListExpr e -> extract(e);
      // case LiteralExpr e -> extract(e);
      // case LocalBindingExpr e -> extract(e);
      // case MapExpr e -> extract(e);
      // case MetaExpr e -> extract(e);
      // case MethodParamExpr e -> extract(e);
      // case MonitorEnterExpr e -> extract(e);
      // case MonitorExitExpr e -> extract(e);
      // case NewExpr e -> extract(e);
      // case NewInstanceExpr e -> extract(e);
      // case ObjExpr e -> extract(e);
      // case QualifiedMethodExpr e -> extract(e);
      // case RecurExpr e -> extract(e);
      // case SetExpr e -> extract(e);
      // case StaticInvokeExpr e -> extract(e);
      // case TheVarExpr e -> extract(e);
      // case ThrowExpr e -> extract(e);
      // case TryExpr e -> extract(e);
      // case UnresolvedVarExpr e -> extract(e);
      // case UntypedExpr e -> extract(e);
      // case VarExpr e -> extract(e);
      // case VectorExpr e -> extract(e);
      default -> {
        System.out.println("unhandled Compile.Expr type");
        yield PersistentHashMap.EMPTY;
      }
    };
  }

  public static IPersistentMap extract(final ConstantExpr expr) {
    return switch (expr.v) {
      case IPersistentMap v -> (IPersistentMap) v;
      default -> PersistentHashMap.EMPTY;
    };
  }

  public static IPersistentMap extract(final DefExpr expr) {
    final Var var = expr.var;
    final IPersistentMap varMeta = var.meta();
    final IPersistentMap exprMeta = extract((ConstantExpr) expr.meta);
    final IPersistentMap meta = Api.merge(varMeta, exprMeta);
    return PersistentHashMap.EMPTY
        .assoc(varKey, var)
        .assoc(metaKey, meta);
  }

  public static IPersistentMap extract(final InvokeExpr expr) {
    return switch (expr.fexpr) {
      case VarExpr e -> {
        // extract expr.args
        yield extract(e);
      }
      default -> {
        // System.out.println("unhandled InvokeExpr/fexpr type: " +
        // expr.fexpr.getClass().getSimpleName());
        yield PersistentHashMap.EMPTY;
      }
    };
  }

  public static IPersistentMap extract(final VarExpr expr) {
    final Var var = expr.var;
    final IPersistentMap meta = var.meta();
    return PersistentHashMap.EMPTY
        .assoc(varKey, var)
        .assoc(metaKey, meta);
  }

}
