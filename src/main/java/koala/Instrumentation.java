package koala;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import clojure.lang.IPersistentMap;
import clojure.lang.Instrument;

public final class Instrumentation {

  static {
    ByteBuddyAgent.install();
  }

  public static void instrument(final IPersistentMap config) {
    final ClassReloadingStrategy strategy = ClassReloadingStrategy.fromInstalledAgent();
    Instrument.setConfig(config);
    Instrument.instrument(strategy);
  }

  public static void instrument(final IPersistentMap config, final ClassReloadingStrategy strategy) {
    Instrument.setConfig(config);
    Instrument.instrument(strategy);
  }

}
