package koala;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.MonitorEnterExprAdvice;
import clojure.lang.MonitorExitExprAdvice;
import clojure.lang.ThrowExprAdvice;
import clojure.lang.UntypedExprAdvice;

public final class Instrumentation {

  static {
    ByteBuddyAgent.install();
  }

  public static void instrument() {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    UntypedExprAdvice.instrument(strategy);
    MonitorEnterExprAdvice.instrument(strategy);
    MonitorExitExprAdvice.instrument(strategy);
    ThrowExprAdvice.instrument(strategy);
  }

}
