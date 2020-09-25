(ns RN.debug
  (:require
   [RN.log :as log]

   [re-frame.core :as rf]

   [cljs.pprint :as pprint]))


(def log (-> ::_ namespace log/get-logger))


(def event-debugger
  (rf/->interceptor
   :id ::event-debugger
   :before
   (fn [context]
     (log :debug "Event: %s"
          (-> context :coeffects :event pr-str))
     context)))


(defn reg-re-frame-event-debugger []
  (rf/reg-global-interceptor event-debugger))


(defn init []
  (log/set-console-capturing true)
  (reg-re-frame-event-debugger))


(rf/reg-event-fx
 ::dump-db
 (fn [{:keys [db]} [_]]
   {:debug db}))


(defn dump-db []
  (rf/dispatch [::dump-db]))


#_
(RN.debug/dump-db)

(init)
