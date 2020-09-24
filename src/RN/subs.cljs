(ns RN.subs
  (:require
   [re-frame.core :as rf]))


(rf/reg-sub
 :get-in
 (fn [db [_ path]]
   (get-in db path)))
