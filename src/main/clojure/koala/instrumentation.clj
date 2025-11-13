(ns koala.instrumentation
  (:require
   [clojure.tools.logging :as log]))

;;;;
;; Instrumentation entrypoint
;;;;

(defmulti handler :target)

(defmethod handler :default
  [opts]
  (log/debug (pr-str opts)))
