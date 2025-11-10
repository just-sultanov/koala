(ns koala.core
  (:import
   (koala
    Instrumentation)))

(defn instrument
  []
  (Instrumentation/instrument))

(comment
  (instrument))
