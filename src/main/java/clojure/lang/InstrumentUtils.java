package clojure.lang;

import clojure.asm.commons.GeneratorAdapter;

import clojure.lang.Compiler.AssignExpr;
// import clojure.lang.Compiler.AssignableExpr;
import clojure.lang.Compiler.BodyExpr;
import clojure.lang.Compiler.BooleanExpr;
import clojure.lang.Compiler.CaseExpr;
import clojure.lang.Compiler.ConstantExpr;
import clojure.lang.Compiler.DefExpr;
import clojure.lang.Compiler.EmptyExpr;
import clojure.lang.Compiler.FieldExpr;
import clojure.lang.Compiler.FnExpr;
import clojure.lang.Compiler.HostExpr;
import clojure.lang.Compiler.IfExpr;
import clojure.lang.Compiler.ImportExpr;
import clojure.lang.Compiler.InstanceFieldExpr;
import clojure.lang.Compiler.InstanceOfExpr;
import clojure.lang.Compiler.InvokeExpr;
import clojure.lang.Compiler.KeywordExpr;
import clojure.lang.Compiler.KeywordInvokeExpr;
import clojure.lang.Compiler.LetExpr;
import clojure.lang.Compiler.LetFnExpr;
import clojure.lang.Compiler.ListExpr;
import clojure.lang.Compiler.LiteralExpr;
import clojure.lang.Compiler.LocalBindingExpr;
import clojure.lang.Compiler.MapExpr;
import clojure.lang.Compiler.MetaExpr;
import clojure.lang.Compiler.MethodParamExpr;
import clojure.lang.Compiler.MonitorEnterExpr;
import clojure.lang.Compiler.MonitorExitExpr;
import clojure.lang.Compiler.NewExpr;
import clojure.lang.Compiler.NewInstanceExpr;
import clojure.lang.Compiler.NilExpr;
import clojure.lang.Compiler.NumberExpr;
import clojure.lang.Compiler.ObjExpr;
import clojure.lang.Compiler.QualifiedMethodExpr;
import clojure.lang.Compiler.RecurExpr;
import clojure.lang.Compiler.SetExpr;
import clojure.lang.Compiler.StaticInvokeExpr;
import clojure.lang.Compiler.StringExpr;
import clojure.lang.Compiler.TheVarExpr;
import clojure.lang.Compiler.ThrowExpr;
import clojure.lang.Compiler.TryExpr;
import clojure.lang.Compiler.UnresolvedVarExpr;
import clojure.lang.Compiler.UntypedExpr;
import clojure.lang.Compiler.VarExpr;
import clojure.lang.Compiler.VectorExpr;

import koala.Api;

public final class InstrumentUtils {

  public final static Keyword contextKey = Keyword.intern("context");
  public final static Keyword contextStatementKey = Keyword.intern("statement");
  public final static Keyword contextExpressionKey = Keyword.intern("expression");
  public final static Keyword contextReturnKey = Keyword.intern("return");
  public final static Keyword contextEvalKey = Keyword.intern("eval");

  public final static Keyword objxKey = Keyword.intern("objx");
  public final static Keyword genKey = Keyword.intern("gen");

  public final static Keyword methodKey = Keyword.intern("method");
  public final static Keyword varKey = Keyword.intern("var");
  public final static Keyword metaKey = Keyword.intern("meta");

  public static IPersistentMap extract(
      final String method,
      final Compiler.Expr expr,
      final Compiler.C context,
      final Compiler.ObjExpr objx,
      final GeneratorAdapter gen) {
    final Keyword ctx = extractContext(context);
    return extractExpr(method, expr)
        .assoc(contextKey, ctx);
  }

  public static Keyword extractContext(final Compiler.C context) {
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

  public static IPersistentMap extractMeta(final Var var, final ConstantExpr expr) {
    final IPersistentMap v = (IPersistentMap) expr.v;
    return (IPersistentMap) Api.merge.invoke(var.meta(), v);
  }

  public static IPersistentMap extractExpr(final String method, final Compiler.Expr expr) {
    return switch (expr) {
      case AssignExpr e -> extractExpr(method, e);
      case BodyExpr e -> extractExpr(method, e);
      case CaseExpr e -> extractExpr(method, e);
      case DefExpr e -> extractExpr(method, e);
      case FieldExpr e -> extractExpr(method, e);
      case FnExpr e -> extractExpr(method, e);
      case HostExpr e -> extractExpr(method, e);
      case IfExpr e -> extractExpr(method, e);
      case ImportExpr e -> extractExpr(method, e);
      // case InstanceFieldExpr e -> extractExpr(method, e);
      case InstanceOfExpr e -> extractExpr(method, e);
      case InvokeExpr e -> extractExpr(method, e);
      case KeywordExpr e -> extractExpr(method, e);
      case KeywordInvokeExpr e -> extractExpr(method, e);
      case LetExpr e -> extractExpr(method, e);
      case LetFnExpr e -> extractExpr(method, e);
      case ListExpr e -> extractExpr(method, e);
      case LiteralExpr e -> extractExpr(method, e);
      case LocalBindingExpr e -> extractExpr(method, e);
      case MapExpr e -> extractExpr(method, e);
      case MetaExpr e -> extractExpr(method, e);
      case MethodParamExpr e -> extractExpr(method, e);
      case MonitorEnterExpr e -> extractExpr(method, e);
      case MonitorExitExpr e -> extractExpr(method, e);
      case NewExpr e -> extractExpr(method, e);
      case NewInstanceExpr e -> extractExpr(method, e);
      case ObjExpr e -> extractExpr(method, e);
      case QualifiedMethodExpr e -> extractExpr(method, e);
      case RecurExpr e -> extractExpr(method, e);
      case SetExpr e -> extractExpr(method, e);
      case StaticInvokeExpr e -> extractExpr(method, e);
      case TheVarExpr e -> extractExpr(method, e);
      case ThrowExpr e -> extractExpr(method, e);
      case TryExpr e -> extractExpr(method, e);
      case UnresolvedVarExpr e -> extractExpr(method, e);
      case UntypedExpr e -> extractExpr(method, e);
      case VarExpr e -> extractExpr(method, e);
      case VectorExpr e -> extractExpr(method, e);
      default -> PersistentHashMap.EMPTY;
    };
  }

  public static IPersistentMap extractExpr(final String method, final AssignExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("AssignExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final BodyExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("BodyExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final BooleanExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("BooleanExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final CaseExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("CaseExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ConstantExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("ConstantExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final DefExpr expr) {
    final IPersistentMap meta = extractMeta(expr.var, (ConstantExpr) expr.meta);
    return PersistentHashMap.EMPTY
        .assoc(methodKey, Keyword.intern("DefExpr", method))
        .assoc(varKey, expr.var)
        .assoc(metaKey, meta);
  }

  public static IPersistentMap extractExpr(final String method, final EmptyExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("EmptyExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final FieldExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("FieldExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final FnExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("FnExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final HostExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("HostExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final IfExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("IfExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ImportExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("ImportExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final InstanceFieldExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("InstanceFieldExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final InstanceOfExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("InstanceOfExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final InvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("InvokeExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final KeywordExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("KeywordExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final KeywordInvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("KeywordInvokeExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final LetExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("LetExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final LetFnExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("LetFnExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ListExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("ListExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final LiteralExpr expr) {
    return switch (expr) {
      case BooleanExpr e -> extractExpr(method, e);
      case ConstantExpr e -> extractExpr(method, e);
      case NilExpr e -> extractExpr(method, e);
      case NumberExpr e -> extractExpr(method, e);
      case StringExpr e -> extractExpr(method, e);
      default -> PersistentHashMap.EMPTY;
    };
  }

  public static IPersistentMap extractExpr(final String method, final LocalBindingExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("LocalBindingExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MapExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("MapExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MetaExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("MetaExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MethodParamExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("MethodParamExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MonitorEnterExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("MonitorEnterExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MonitorExitExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("MonitorExitExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NewExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("NewExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NewInstanceExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("NewInstanceExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NilExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("NilExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NumberExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("NumberExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ObjExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("ObjExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final QualifiedMethodExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("QualifiedMethodExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final RecurExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("RecurExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final SetExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("SetExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final StaticInvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("StaticInvokeExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final StringExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("StringExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final TheVarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("TheVarExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ThrowExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("ThrowExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final TryExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("TryExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final UnresolvedVarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("UnresolvedVarExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final UntypedExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("UntypedExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final VarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("VarExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final VectorExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(methodKey, Keyword.intern("VectorExpr", method));
    return info.persistent();
  }
}
