(ns RN.debug
  (:require
   [re-frame.core :as rf]

   [cljs.pprint :as pprint]))


(defn debug [data]
  (js/console.log
   (with-out-str
     (println)
     (println "--------------")
     (pprint/pprint data)
     (println "--------------"))))


(def rf-event-debugger
  (rf/->interceptor
   :id ::event-debugger
   :before
   (fn [context]
     (debug (-> context :coeffects :event))
     context)))


(defn set-re-frame-event-debugger []
  (rf/reg-global-interceptor rf-event-debugger))


(defn init []
  (set-re-frame-event-debugger))


(defn dump-db []
  (rf/dispatch [:rn/dump-db]))


(rf/reg-fx
 :rn/debug
 (fn [data]
   (debug data)))


(rf/reg-event-fx
 :rn/dump-db
 (fn [{:keys [db]} _]
   {:rn/debug db}))
