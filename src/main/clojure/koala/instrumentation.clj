(ns koala.instrumentation
  (:require
   [clojure.tools.logging :as log])
  (:import
   (org.slf4j MDC)))

(defmacro with-mdc
  [m & body]
  `(try
     (run!
      (fn [[k# v#]]
        (MDC/put (str k#) (pr-str (or v# "N/A")))) ~m)
     ~@body
     (finally
       (MDC/clear))))

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
  (fn [{:keys [ns-patterns]} {:keys [current-ns meta method]}]
    (if (skip? ns-patterns (or (:ns meta) current-ns))
      :skip
      method)))

(defmethod handler :skip
  [_config _data])

(defmethod handler :default
  [_config {:as data :keys [method meta]}]
  (when (= "emitExit" (name method))
    (with-mdc {:name  (format "%s/%s" (:ns meta) (:name meta))
               :method method
               :coord [(:line meta) (:column meta)]}
      (log/debug (pr-str data)))))
