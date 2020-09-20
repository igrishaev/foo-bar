(ns mobile.log
  (:import [goog.log Level LogRecord Logger]
           [goog.debug Console])

  (:require

   [goog.debug.Logger :as Logger]
   [goog.debug.Logger.Level :as Level]
   [goog.debug.Console :as Console]

   [goog.debug :as debug]
   [goog.log :as log]
   [goog.debug.LogManager :as LogManager]

   [cljs.pprint :as pprint]))




(defn ->google-level
  [^Keyword level]
  (case level

    :all
    Level.ALL

    (:error :severe)
    Level.SEVERE

    :warning
    Level.WARNING

    :info
    Level.INFO

    ;; default
    Level.INFO))




(defn get-logger
  [name]

  (let [^Logger logger (log/getLogger name)]
    (fn [^Keyword level ^js/String template & args]
      (let [^Level level (->google-level level)]
        (log/log logger level template


                 #_
                 (apply format template args))))))



(defn debug [data]
  (js/console.log
   (with-out-str
     (println)
     (println "--------------")
     (pprint/pprint data)
     (println "--------------"))))
