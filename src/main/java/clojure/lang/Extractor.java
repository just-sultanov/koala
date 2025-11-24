package clojure.lang;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import clojure.asm.commons.GeneratorAdapter;
import clojure.lang.Compiler.*;

import koala.Api;

public final class Extractor {

  public final static Keyword contextKey = Keyword.intern("context");
  public final static Keyword contextStatementKey = Keyword.intern("statement");
  public final static Keyword contextExpressionKey = Keyword.intern("expression");
  public final static Keyword contextReturnKey = Keyword.intern("return");
  public final static Keyword contextEvalKey = Keyword.intern("eval");

  public final static Keyword currentNSKey = Keyword.intern("current-ns");
  public final static Keyword nsKey = Keyword.intern("ns");
  public final static Keyword nameKey = Keyword.intern("name");
  public final static Keyword methodKey = Keyword.intern("method");
  public final static Keyword varKey = Keyword.intern("var");
  public final static Keyword metaKey = Keyword.intern("meta");
  public final static Keyword fexprKey = Keyword.intern("fexpr");

  public final static Keyword lineKey = Keyword.intern("line");
  public final static Keyword columnKey = Keyword.intern("column");

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
        .assoc(currentNSKey, Compiler.currentNS())
        .assoc(methodKey, asMethod(method, expr))
        .assoc(contextKey, asContext(context));
  }

  public static IPersistentMap extract(final Compiler.Expr expr) {
    return switch (expr) {
      // case AssignExpr e -> extract(e);
      // case final BodyExpr e -> extract(e);
      // case CaseExpr e -> extract(e);
      case final DefExpr e -> extract(e);
      // case FieldExpr e -> extract(e);
      // case FnExpr e -> extract(e);
      // case HostExpr e -> extract(e);
      // case IfExpr e -> extract(e);
      // case ImportExpr e -> extract(e);
      // case InstanceFieldExpr e -> extract(e);
      // case InstanceOfExpr e -> extract(e);
      case final InvokeExpr e -> extract(e);
      // case KeywordExpr e -> extract(e);
      // case KeywordInvokeExpr e -> extract(e);
      // case final LetExpr e -> extract(e);
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
        // System.out.println("unhandled Compiler.Expr type: " +
        // expr.getClass().getSimpleName());
        yield PersistentHashMap.EMPTY;
      }
    };
  }

  @SuppressWarnings("unchecked")
  public static IPersistentMap extract(final BodyExpr expr) {
    final Iterator<Compiler.Expr> iterator = expr.exprs().iterator();
    final List<IPersistentMap> exprs = Stream.generate(() -> null)
        .takeWhile(x -> iterator.hasNext())
        .map(x -> iterator.next())
        .map(e -> extract(e))
        .collect(Collectors.toList());

    // System.out.println();
    // System.out.println("===== ns: " + Compiler.currentNS());
    // System.out.println("===== exprs: " + exprs);
    // System.out.println();

    return PersistentHashMap.EMPTY
        .assoc(Keyword.intern("exprs"), exprs);
  }

  public static IPersistentMap extract(final ConstantExpr expr) {
    return switch (expr.v) {
      case final IPersistentMap v -> (IPersistentMap) v;
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
      // case final InvokeExpr e -> extract(e.fexpr);
      case final VarExpr e -> {
        // extract expr.args
        yield extract(e);
      }
      case final FnExpr e -> {
        yield extract(e);
      }
      case final TheVarExpr e -> extract(e);
      default -> {
        // System.out.println("unhandled InvokeExpr/fexpr type: " +
        // expr.fexpr.getClass().getSimpleName());
        yield PersistentHashMap.EMPTY.assoc("simpleName", expr.fexpr.getClass().getSimpleName());
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

  public static IPersistentMap extract(final TheVarExpr expr) {
    final Var var = expr.var;
    final IPersistentMap meta = var.meta();
    return PersistentHashMap.EMPTY
        .assoc(varKey, var)
        .assoc(metaKey, meta);
  }

  public static IPersistentMap extract(final FnExpr expr) {
    // final ObjExpr objexpr = (ObjExpr) expr;
    final IPersistentMap meta = PersistentHashMap.EMPTY
        .assoc(nameKey, Symbol.intern(expr.name()))
        .assoc(nsKey, Compiler.currentNS())
        .assoc(lineKey, expr.line())
        .assoc(columnKey, expr.column());

    return PersistentHashMap.EMPTY
        .assoc(metaKey, meta);
  }

}
