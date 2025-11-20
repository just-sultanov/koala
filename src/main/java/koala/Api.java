package koala;

import clojure.java.api.Clojure;
import clojure.lang.*;

public final class Api {
  public static IFn require = Clojure.var("clojure.core", "require");
  public static IFn merge = Clojure.var("clojure.core", "merge");
  public static IFn handler;

  static {
    require.invoke(Clojure.read("koala.instrumentation"));
    handler = Clojure.var("koala.instrumentation", "handler");
  }

  public static void invoke(final IPersistentMap config, final IPersistentMap data) {
    if (handler != null) {
      handler.invoke(config, data);
    }
  }

  public static IPersistentMap merge(final IPersistentMap m1, final IPersistentMap m2) {
    return (IPersistentMap) merge.invoke(m1, m2);
  }

}
