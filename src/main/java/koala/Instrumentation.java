package koala;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.Instrument;

public final class Instrumentation {

  static {
    ByteBuddyAgent.install();
  }

  public static void instrument() {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    Instrument.instrument(strategy);
  }

  public static void instrument(ClassReloadingStrategy strategy) {
    Instrument.instrument(strategy);
  }

}
