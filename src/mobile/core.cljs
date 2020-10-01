(ns mobile.core
  (:require
   [RN.core :as rn]

   RN.async-storage
   RN.alert
   RN.log
   RN.db
   RN.debug
   RN.dimensions
   RN.flash-message

   mobile.events
   mobile.effects

   [mobile.config :as config]

   mobile.views.main

   [reagent.core :as r]))


(RN.log/init)
(RN.debug/init)


(def main-page mobile.views.main/screen)


(defn App []
  (r/as-element [main-page]))


(defn ^:export init []
  (.registerComponent rn/app-registry
                      config/app-name
                      (fn [] App)))


(defn ^:export -main [& args]
  (App))
