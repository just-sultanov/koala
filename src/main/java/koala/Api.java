package koala;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.IPersistentMap;
import clojure.lang.Keyword;

public final class Api {
  public static IFn require = Clojure.var("clojure.core", "require");
  public static IFn handler;

  static {
    require.invoke(Clojure.read("koala.instrumentation"));
    handler = Clojure.var("koala.instrumentation", "handler");
  }

  public static void invoke(final IPersistentMap opts) {
    if (handler != null) {
      handler.invoke(opts);
    }
  }

  public static Keyword asKeyword(final String name) {
    return Keyword.intern(name);
  }

  public static Keyword asKeyword(final String ns, final String name) {
    return Keyword.intern(ns, name);
  }
}
