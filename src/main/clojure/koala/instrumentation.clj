(ns koala.instrumentation
  (:require
   [clojure.tools.logging :as log]))

;;;;
;; Instrumentation entrypoint
;;;;

(defmulti handler
  (fn [_config data]
    (:target data)))

(defmethod handler :default
  [config data]
  (log/debug :config (pr-str config) :data (pr-str data)))
