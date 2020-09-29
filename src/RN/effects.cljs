(ns RN.effects
  (:require
   [RN.nav :as core]
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


(rf/reg-fx
 :navigate
 (fn [screen-name params]
   (some-> (use-navigation)
           (.navigate screen-name (clj->js params)))))
