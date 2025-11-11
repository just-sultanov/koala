package koala;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.AssignExprAdvice;
import clojure.lang.BodyExprAdvice;
import clojure.lang.CaseExprAdvice;
import clojure.lang.DefExprAdvice;
import clojure.lang.EmptyExprAdvice;
import clojure.lang.FnExprAdvice;
import clojure.lang.HostExprAdvice;
import clojure.lang.IfExprAdvice;
import clojure.lang.ImportExprAdvice;
import clojure.lang.InstanceOfExprAdvice;
import clojure.lang.InvokeExprAdvice;
import clojure.lang.KeywordInvokeExprAdvice;
import clojure.lang.LetExprAdvice;
import clojure.lang.LetFnExprAdvice;
import clojure.lang.MonitorEnterExprAdvice;
import clojure.lang.MonitorExitExprAdvice;
import clojure.lang.TheVarExprAdvice;
import clojure.lang.ThrowExprAdvice;
import clojure.lang.UntypedExprAdvice;
import clojure.lang.VarExprAdvice;

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
    DefExprAdvice.instrument(strategy);
    FnExprAdvice.instrument(strategy);
    HostExprAdvice.instrument(strategy);
    IfExprAdvice.instrument(strategy);
    ImportExprAdvice.instrument(strategy);
    InstanceOfExprAdvice.instrument(strategy);
    InvokeExprAdvice.instrument(strategy);
    KeywordInvokeExprAdvice.instrument(strategy);
    LetExprAdvice.instrument(strategy);
    LetFnExprAdvice.instrument(strategy);
    MonitorEnterExprAdvice.instrument(strategy);
    MonitorExitExprAdvice.instrument(strategy);
    TheVarExprAdvice.instrument(strategy);
    ThrowExprAdvice.instrument(strategy);
    UntypedExprAdvice.instrument(strategy);
    VarExprAdvice.instrument(strategy);
  }

}
