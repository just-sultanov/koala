(ns koala.instrumentation
  (:require
   [clojure.tools.logging :as log]))

;;;;
;; Instrumentation entrypoint
;;;;

(def skip?
  (memoize
   (fn skip? [ns-patterns ns]
     (cond
       (nil? ns) true
       (empty? ns-patterns) true
       :else (not (some #(re-find (re-pattern %) (str (ns-name ns))) ns-patterns))))))

(defmulti handler
  (fn [{:keys [ns-patterns]} {:keys [meta method]}]
    (if (skip? ns-patterns (:ns meta))
      :skip
      method)))

(defmethod handler :skip
  [_config _data])

(defmethod handler :default
  [_config data]
  (log/debug (pr-str data)))
