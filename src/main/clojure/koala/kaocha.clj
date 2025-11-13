(ns koala.kaocha
  (:require
   [koala.core :as kc]
   [clojure.tools.logging :as log]))

(defn pre-load
  [config]
  (log/debug "Instrumenting...")
  (kc/instrument)
  config)

(defn post-load
  [config]
  config)

(defn pre-run
  [config]
  (log/debug "Testing...")
  config)

(defn post-run
  [config]
  (log/debug "Reporting...")
  config)
