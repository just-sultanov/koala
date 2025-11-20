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
  public final static Keyword targetKey = Keyword.intern("target");
  public final static Keyword exprKey = Keyword.intern("expr");
  public final static Keyword contextKey = Keyword.intern("context");
  public final static Keyword contextStatementKey = Keyword.intern("statement");
  public final static Keyword contextExpressionKey = Keyword.intern("expression");
  public final static Keyword contextReturnKey = Keyword.intern("return");
  public final static Keyword contextEvalKey = Keyword.intern("eval");
  public final static Keyword objxKey = Keyword.intern("objx");
  public final static Keyword genKey = Keyword.intern("gen");

  public static IPersistentMap extract(
      final String method,
      final Compiler.Expr expr,
      final Compiler.C context,
      final Compiler.ObjExpr objx,
      final GeneratorAdapter gen) {
    final Keyword ctx = extractContext(context);
    final IPersistentMap exprInfo = extractExpr(method, expr);
    final IPersistentMap objxInfo = extractExpr(method, objx);
    final IPersistentMap genInfo = extractGen(gen);
    final ITransientMap opts = PersistentHashMap.EMPTY.asTransient()
        // .assoc(targetKey, target)
        .assoc(exprKey, exprInfo)
        .assoc(contextKey, ctx)
        .assoc(objxKey, objxInfo)
        .assoc(genKey, genInfo);

    // if (expr.fexpr instanceof VarExpr) {
    // final Var var = ((VarExpr) expr.fexpr).var;
    // System.out.println("InvokeExpr -> This " +
    // opts.assoc("var", var)
    // .assoc("varmeta", var.meta())
    // .assoc("source", expr.source)
    // .assoc("tag", expr.tag)
    // .assoc("line", expr.line)
    // .assoc("column", expr.column));
    // } else {
    // System.out.println("InvokeExpr -> This " +
    // opts.assoc("source", expr.source)
    // .assoc("tag", expr.tag)
    // .assoc("line", expr.line)
    // .assoc("column", expr.column));
    // }
    // System.out.println("InvokeExpr -> ObjExpr " +
    // opts.assoc("name", objx.name())
    // .assoc("internalName", objx.internalName())
    // .assoc("thisName", objx.thisName())
    // .assoc("objtype", objx.objtype())
    // .assoc("tag", objx.tag)
    // .assoc("line", objx.line())
    // .assoc("column", objx.column())
    // .assoc("src", objx.src));
    return opts.persistent();
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
        .assoc(targetKey, Api.asKeyword("AssignExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final BodyExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("BodyExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final BooleanExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("BooleanExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final CaseExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("CaseExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ConstantExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("ConstantExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final DefExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("DefExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final EmptyExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("EmptyExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final FieldExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("FieldExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final FnExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("FnExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final HostExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("HostExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final IfExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("IfExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ImportExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("ImportExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final InstanceFieldExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("InstanceFieldExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final InstanceOfExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("InstanceOfExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final InvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("InvokeExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final KeywordExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("KeywordExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final KeywordInvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("KeywordInvokeExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final LetExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("LetExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final LetFnExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("LetFnExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ListExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("ListExpr", method));
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
        .assoc(targetKey, Api.asKeyword("LocalBindingExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MapExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("MapExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MetaExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("MetaExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MethodParamExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("MethodParamExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MonitorEnterExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("MonitorEnterExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final MonitorExitExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("MonitorExitExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NewExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("NewExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NewInstanceExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("NewInstanceExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NilExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("NilExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final NumberExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("NumberExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ObjExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("ObjExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final QualifiedMethodExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("QualifiedMethodExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final RecurExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("RecurExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final SetExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("SetExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final StaticInvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("StaticInvokeExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final StringExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("StringExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final TheVarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("TheVarExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final ThrowExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("ThrowExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final TryExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("TryExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final UnresolvedVarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("UnresolvedVarExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final UntypedExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("UntypedExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final VarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("VarExpr", method));
    return info.persistent();
  }

  public static IPersistentMap extractExpr(final String method, final VectorExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc(targetKey, Api.asKeyword("VectorExpr", method));
    return info.persistent();
  }
}
