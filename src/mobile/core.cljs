(ns mobile.core
  (:require
   [RN.core :as rn]
   [RN.async-storage :as async-storage]

   RN.debug
   RN.effects
   RN.subs
   RN.events

   mobile.subs
   mobile.events
   mobile.effects

   [mobile.config :as config]

   mobile.views.main

   [reagent.core :as r]))


(def main-page mobile.views.main/screen)


(defn App []
  (r/as-element [main-page]))


(defn init []
  (.registerComponent rn/app-registry
                      config/app-name
                      (fn [] App)))


(defn ^:export -main [& args]
  (App))
