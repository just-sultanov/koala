package koala;

import java.util.List;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.IPersistentMap;
import clojure.lang.Instrumentor;
import clojure.lang.Instrumentor.ExprKind;

public final class Instrumentation {

  static {
    ByteBuddyAgent.install();
  }

  public static void instrument(final IPersistentMap config) {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    instrument(config, strategy);
  }

  public static void instrument(final IPersistentMap config, final ClassReloadingStrategy strategy) {
    final List<ExprKind> kinds = List.of(
        ExprKind.DefExpr,
        ExprKind.InvokeExpr);
    Instrumentor.setConfig(config);
    Instrumentor.instrument(kinds, strategy);
  }

}
