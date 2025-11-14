package clojure.lang;

import clojure.asm.commons.GeneratorAdapter;

import clojure.lang.Compiler.AssignExpr;
import clojure.lang.Compiler.AssignableExpr;
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
  public final static Keyword objxKey = Keyword.intern("objx");
  public final static Keyword genKey = Keyword.intern("gen");

  public static IPersistentMap makeOpts(
      final String methodName,
      final Compiler.Expr expr,
      final Compiler.C context,
      final Compiler.ObjExpr objx,
      final GeneratorAdapter gen) {
    // final Keyword target = Api.asKeyword(targetName, String.format("%sEnter",
    // methodName));
    final Keyword ctx = Api.asKeyword(context.name().toLowerCase());
    final IPersistentMap exprInfo = extractExprInfo(expr);
    final IPersistentMap objxInfo = extractExprInfo(objx);
    final IPersistentMap genInfo = extractGenInfo(gen);
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

  public static IPersistentMap extractGenInfo(final GeneratorAdapter gen) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient();
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final Compiler.Expr expr) {
    return switch (expr) {
      case AssignExpr e -> extractExprInfo(e);
      case BodyExpr e -> extractExprInfo(e);
      case CaseExpr e -> extractExprInfo(e);
      case DefExpr e -> extractExprInfo(e);
      case FieldExpr e -> extractExprInfo(e);
      case FnExpr e -> extractExprInfo(e);
      case HostExpr e -> extractExprInfo(e);
      case IfExpr e -> extractExprInfo(e);
      case ImportExpr e -> extractExprInfo(e);
      // case InstanceFieldExpr e -> extractExprInfo(e);
      case InstanceOfExpr e -> extractExprInfo(e);
      case InvokeExpr e -> extractExprInfo(e);
      case KeywordExpr e -> extractExprInfo(e);
      case KeywordInvokeExpr e -> extractExprInfo(e);
      case LetExpr e -> extractExprInfo(e);
      case LetFnExpr e -> extractExprInfo(e);
      case ListExpr e -> extractExprInfo(e);
      case LiteralExpr e -> extractExprInfo(e);
      case LocalBindingExpr e -> extractExprInfo(e);
      case MapExpr e -> extractExprInfo(e);
      case MetaExpr e -> extractExprInfo(e);
      case MethodParamExpr e -> extractExprInfo(e);
      case MonitorEnterExpr e -> extractExprInfo(e);
      case MonitorExitExpr e -> extractExprInfo(e);
      case NewExpr e -> extractExprInfo(e);
      case NewInstanceExpr e -> extractExprInfo(e);
      case ObjExpr e -> extractExprInfo(e);
      case QualifiedMethodExpr e -> extractExprInfo(e);
      case RecurExpr e -> extractExprInfo(e);
      case SetExpr e -> extractExprInfo(e);
      case StaticInvokeExpr e -> extractExprInfo(e);
      case TheVarExpr e -> extractExprInfo(e);
      case ThrowExpr e -> extractExprInfo(e);
      case TryExpr e -> extractExprInfo(e);
      case UnresolvedVarExpr e -> extractExprInfo(e);
      case UntypedExpr e -> extractExprInfo(e);
      case VarExpr e -> extractExprInfo(e);
      case VectorExpr e -> extractExprInfo(e);
      default -> PersistentHashMap.EMPTY;
    };
  }

  public static IPersistentMap extractExprInfo(final AssignExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("AssignExpr", "AssignExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final BodyExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("BodyExpr", "BodyExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final BooleanExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("BooleanExpr", "BooleanExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final CaseExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("CaseExpr", "CaseExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final ConstantExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("ConstantExpr", "ConstantExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final DefExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("DefExpr", "DefExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final EmptyExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("EmptyExpr", "EmptyExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final FieldExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("FieldExpr", "FieldExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final FnExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("FnExpr", "FnExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final HostExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("HostExpr", "HostExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final IfExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("IfExpr", "IfExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final ImportExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("ImportExpr", "ImportExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final InstanceFieldExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("InstanceFieldExpr", "InstanceFieldExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final InstanceOfExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("InstanceOfExpr", "InstanceOfExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final InvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("InvokeExpr", "InvokeExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final KeywordExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("KeywordExpr", "KeywordExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final KeywordInvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("KeywordInvokeExpr", "KeywordInvokeExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final LetExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("LetExpr", "LetExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final LetFnExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("LetFnExpr", "LetFnExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final ListExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("ListExpr", "ListExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final LiteralExpr expr) {
    return switch (expr) {
      case BooleanExpr e -> extractExprInfo(e);
      case ConstantExpr e -> extractExprInfo(e);
      case NilExpr e -> extractExprInfo(e);
      case NumberExpr e -> extractExprInfo(e);
      case StringExpr e -> extractExprInfo(e);
      default -> PersistentHashMap.EMPTY;
    };
  }

  public static IPersistentMap extractExprInfo(final LocalBindingExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("LocalBindingExpr", "LocalBindingExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final MapExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("MapExpr", "MapExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final MetaExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("MetaExpr", "MetaExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final MethodParamExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("MethodParamExpr", "MethodParamExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final MonitorEnterExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("MonitorEnterExpr", "MonitorEnterExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final MonitorExitExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("MonitorExitExpr", "MonitorExitExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final NewExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("NewExpr", "NewExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final NewInstanceExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("NewInstanceExpr", "NewInstanceExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final NilExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("NilExprExpr", "NilExprExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final NumberExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("NumberExpr", "NumberExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final ObjExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("ObjExpr", "ObjExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final QualifiedMethodExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("QualifiedMethodExpr", "QualifiedMethodExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final RecurExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("RecurExpr", "RecurExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final SetExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("SetExpr", "SetExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final StaticInvokeExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("StaticInvokeExpr", "StaticInvokeExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final StringExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("StringExpr", "StringExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final TheVarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("TheVarExpr", "TheVarExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final ThrowExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("ThrowExpr", "ThrowExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final TryExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("TryExpr", "TryExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final UnresolvedVarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("UnresolvedVarExpr", "UnresolvedVarExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final UntypedExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("UntypedExpr", "UntypedExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final VarExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("VarExpr", "VarExpr");
    return info.persistent();
  }

  public static IPersistentMap extractExprInfo(final VectorExpr expr) {
    final ITransientMap info = PersistentHashMap.EMPTY.asTransient()
        .assoc("VectorExpr", "VectorExpr");
    return info.persistent();
  }
}
