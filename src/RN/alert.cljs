(ns RN.alert
  "
  https://reactnative.dev/docs/alert
  "
  (:require
   [react-native :as rn]

   [re-frame.core :as rf]

   [clojure.set :as set]))


(defn remap-button [button]
  (set/rename-keys button {:on-press :onPress}))


(defn remap-buttons [buttons]
  (mapv remap-button buttons))


(defn alert [title & [message buttons opt]]
  (rn/Alert.alert
   title message
   (some-> buttons remap-buttons clj->js)
   (some-> opt clj->js)))


(rf/reg-fx
 :rn/alert
 (fn [[title & args]]
   (apply alert title args)))
