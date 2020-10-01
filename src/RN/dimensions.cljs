(ns RN.dimensions
  (:require
   [RN.config :as config]
   [RN.util :as util]

   [react-native :as rn]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(def dimensions rn/Dimensions)


(defn get-window-height []
  (.. dimensions (get "window") -height))


(defn get-window-width []
  (.. dimensions (get "window") -width))


(defn get-window-size []
  [(get-window-width)
   (get-window-height)])


(defn get-screen-height []
  (.. dimensions (get "screen") -height))


(defn get-screen-width []
  (.. dimensions (get "screen") -width))


(defn get-screen-size []
  [(get-screen-width)
   (get-screen-height)])


(defn add-db-listener []
  (.addEventListener
   dimensions
   "change"
   (fn [result]
     (rf/dispatch
      [:rn/assoc-in
       config/path-dimensions
       (util/->clj result)]))))


(defn trigger-db []
  (rf/dispatch-sync
   [:rn/assoc-in
    config/path-dimensions

    {:window (-> dimensions
                 (.get "window")
                 util/->clj)

     :screen (-> dimensions
                 (.get "screen")
                 util/->clj)}]))
