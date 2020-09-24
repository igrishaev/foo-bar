(ns mobile.core
  (:require
   [RN.core :as rn]
   [RN.async-storage :as as]

   RN.debug
   RN.effects
   RN.subs
   RN.events

   mobile.subs
   mobile.events
   mobile.effects

   mobile.pages.main
   mobile.pages.auth
   mobile.pages.pin

   [reagent.core :as r]))


(def main-page mobile.pages.main/page)


(defn App []
  (r/as-element [main-page]))


(defn init []
  (.registerComponent rn/app-registry
                      "FooBar"
                      (fn [] App)))


(defn ^:export -main [& args]
  (App))
