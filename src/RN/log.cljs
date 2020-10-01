(ns RN.log
  "
  Logging facilites.
  https://lambdaisland.com/blog/2019-06-10-goog-log
  "
  (:import [goog.log Level Logger]
           [goog.debug Console])

  (:require
   [RN.util :refer [format]]

   [goog.debug.Logger :as Logger]
   [goog.debug.Logger.Level :as Level]
   [goog.debug.Console :as Console]

   [goog.debug :as debug]
   [goog.log :as log]))


(defonce console (new Console))


(defn set-console-capturing [flag]
  (.setCapturing console flag))


(defn ->google-level
  [^Keyword level]
  (case level

    :all
    Level.ALL

    (:err :error :severe)
    Level.SEVERE

    (:warn :warning)
    Level.WARNING

    :info
    Level.INFO

    ;; default
    Level.INFO))


(defn get-logger
  [name]
  (let [logger (log/getLogger name)]
    (fn [level template & args]
      (log/log logger
               (->google-level level)
               (apply format template args)))))


(defn init []
  (set-console-capturing true))


;;
;; Usage
;;

#_
(comment

  ;; get a logger for the current namespace
  (def log (-> ::_ namespace log/get-logger))

  (log :info "Some event, item: %s, count: %s"
       user-id (count data))

  (log :info :err|error :warn|warning ...)

  )
