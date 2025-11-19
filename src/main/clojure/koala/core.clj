(ns koala.core
  (:import
   (koala
    Instrumentation)))

(defn instrument
  [config]
  (Instrumentation/instrument config))

(comment
  (instrument {}))
