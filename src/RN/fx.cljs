(ns RN.fx
  (:require
   [RN.log :as log]
   [RN.alert :as alert]

   [re-frame.core :as rf]))


(rf/reg-fx
 :debug
 (fn [data]
   (log/debug data)))


(rf/reg-fx
 :alert
 (fn [[title & args]]
   (apply alert/alert title args)))
