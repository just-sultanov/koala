package koala;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.AssignExprAdvice;
import clojure.lang.BodyExprAdvice;
import clojure.lang.CaseExprAdvice;
import clojure.lang.DefExprAdvice;
import clojure.lang.EmptyExprAdvice;
import clojure.lang.FieldExprAdvice;
import clojure.lang.FnExprAdvice;
import clojure.lang.HostExprAdvice;
import clojure.lang.IfExprAdvice;
import clojure.lang.ImportExprAdvice;
import clojure.lang.InstanceFieldExprAdvice;
import clojure.lang.InstanceOfExprAdvice;
import clojure.lang.InvokeExprAdvice;
import clojure.lang.KeywordInvokeExprAdvice;
import clojure.lang.LetExprAdvice;
import clojure.lang.LetFnExprAdvice;
import clojure.lang.ListExprAdvice;
import clojure.lang.LiteralExprAdvice;
import clojure.lang.LocalBindingExprAdvice;
import clojure.lang.MapExprAdvice;
import clojure.lang.MetaExprAdvice;
import clojure.lang.MethodParamExprAdvice;
import clojure.lang.MonitorEnterExprAdvice;
import clojure.lang.MonitorExitExprAdvice;
import clojure.lang.NewExprAdvice;
import clojure.lang.NewInstanceExprAdvice;
import clojure.lang.ObjExprAdvice;
import clojure.lang.QualifiedMethodExprAdvice;
import clojure.lang.RecurExprAdvice;
import clojure.lang.SetExprAdvice;
import clojure.lang.StaticInvokeExprAdvice;
import clojure.lang.TheVarExprAdvice;
import clojure.lang.ThrowExprAdvice;
import clojure.lang.TryExprAdvice;
import clojure.lang.UnresolvedVarExprAdvice;
import clojure.lang.UntypedExprAdvice;
import clojure.lang.VarExprAdvice;
import clojure.lang.VectorExprAdvice;

public final class Instrumentation {

  static {
    ByteBuddyAgent.install();
  }

  public static void instrument() {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    AssignExprAdvice.instrument(strategy);
    BodyExprAdvice.instrument(strategy);
    CaseExprAdvice.instrument(strategy);
    EmptyExprAdvice.instrument(strategy);
    FieldExprAdvice.instrument(strategy);
    DefExprAdvice.instrument(strategy);
    FnExprAdvice.instrument(strategy);
    HostExprAdvice.instrument(strategy);
    IfExprAdvice.instrument(strategy);
    ImportExprAdvice.instrument(strategy);
    InstanceFieldExprAdvice.instrument(strategy);
    InstanceOfExprAdvice.instrument(strategy);
    InvokeExprAdvice.instrument(strategy);
    KeywordInvokeExprAdvice.instrument(strategy);
    LetExprAdvice.instrument(strategy);
    LetFnExprAdvice.instrument(strategy);
    ListExprAdvice.instrument(strategy);
    LiteralExprAdvice.instrument(strategy);
    LocalBindingExprAdvice.instrument(strategy);
    MapExprAdvice.instrument(strategy);
    MetaExprAdvice.instrument(strategy);
    MethodParamExprAdvice.instrument(strategy);
    MonitorEnterExprAdvice.instrument(strategy);
    MonitorExitExprAdvice.instrument(strategy);
    NewExprAdvice.instrument(strategy);
    NewInstanceExprAdvice.instrument(strategy);
    ObjExprAdvice.instrument(strategy);
    QualifiedMethodExprAdvice.instrument(strategy);
    RecurExprAdvice.instrument(strategy);
    SetExprAdvice.instrument(strategy);
    StaticInvokeExprAdvice.instrument(strategy);
    TheVarExprAdvice.instrument(strategy);
    ThrowExprAdvice.instrument(strategy);
    TryExprAdvice.instrument(strategy);
    UnresolvedVarExprAdvice.instrument(strategy);
    UntypedExprAdvice.instrument(strategy);
    VarExprAdvice.instrument(strategy);
    VectorExprAdvice.instrument(strategy);
  }

}
