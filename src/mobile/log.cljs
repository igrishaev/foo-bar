(ns mobile.log
  (:import [goog.log Level LogRecord Logger]
           [goog.debug Console])

  (:require

   [re-frame.loggers :as rf.log]

   [goog.string.format]
   [goog.string :as gstring]

   [goog.debug.Logger :as Logger]
   [goog.debug.Logger.Level :as Level]
   [goog.debug.Console :as Console]

   [goog.debug :as debug]
   [goog.log :as log]
   [goog.debug.LogManager :as LogManager]

   [cljs.pprint :as pprint]))


(def format gstring/format)


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


(rf.log/set-loggers!
 {:error
  (fn [& args]
    (debug (cons :error args)))

  :log
  (fn [& args]
    (debug (cons :log args)))

  :warn
  (fn [& args]
    (debug (cons :warn args)))})


(.setCapturing (Console.) true)
