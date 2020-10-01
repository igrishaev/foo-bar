(ns RN.db
  (:require
   [re-frame.core :as rf]))


(rf/reg-event-db
 :rn/assoc-in
 (fn [db [_ path value]]
   (assoc-in db path value)))


(rf/reg-event-db
 :rn/update-in
 (fn [db [_ path func & args]]
   (apply update-in db path func args)))


(rf/reg-sub
 :rn/get-in
 (fn [db [_ path]]
   (get-in db path)))
