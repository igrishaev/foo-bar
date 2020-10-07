(ns RN.linking
  (:require
   [react-native :as rn]
   [re-frame.core :as rf]

   [reagent.core :as r]))


(defn open-url [url]
  (.. rn/Linking (openURL url)))


(defn can-open-url? [url]
  (.. rn/Linking (canOpenURL url)))
