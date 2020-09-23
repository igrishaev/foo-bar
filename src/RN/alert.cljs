(ns RN.alert
  (:require
   [react-native :as rn]

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
