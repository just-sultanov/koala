package koala;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.FnExprAdvice;
import clojure.lang.ImportExprAdvice;
import clojure.lang.InstanceOfExprAdvice;
import clojure.lang.InvokeExprAdvice;
import clojure.lang.LetExprAdvice;
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
    FnExprAdvice.instrument(strategy);
    ImportExprAdvice.instrument(strategy);
    InstanceOfExprAdvice.instrument(strategy);
    InvokeExprAdvice.instrument(strategy);
    LetExprAdvice.instrument(strategy);
    MonitorEnterExprAdvice.instrument(strategy);
    MonitorExitExprAdvice.instrument(strategy);
    TheVarExprAdvice.instrument(strategy);
    ThrowExprAdvice.instrument(strategy);
    UntypedExprAdvice.instrument(strategy);
    VarExprAdvice.instrument(strategy);
  }

}
