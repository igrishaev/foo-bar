(ns RN.log
  (:import [goog.log Level Logger]
           [goog.debug Console])

  (:require
   [RN.util :refer [format]]

   [goog.debug.Logger :as Logger]
   [goog.debug.Logger.Level :as Level]
   [goog.debug.Console :as Console]

   [goog.debug :as debug]
   [goog.log :as log]
   [goog.debug.LogManager :as LogManager]

   [cljs.pprint :as pprint]))


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

  (let [^Logger logger (log/getLogger name)]
    (fn [^Keyword level
         ^js/String template
         & args]
      (let [^Level level (->google-level level)]
        (log/log logger level
                 (apply format template args))))))


(defn debug [data]
  (js/console.log
   (with-out-str
     (println)
     (println "--------------")
     (pprint/pprint data)
     (println "--------------"))))
