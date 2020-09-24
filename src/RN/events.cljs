(ns RN.events
  (:require
   [re-frame.core :as rf]))


(rf/reg-event-db
 :assoc-in
 (fn [db [_ path value]]
   (assoc-in db path value)))


(rf/reg-event-db
 :update-in
 (fn [db [_ path func & args]]
   (apply update-in db path func args)))
