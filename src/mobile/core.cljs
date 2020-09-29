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

   mobile.views.main

   ;; mobile.pages.search
   ;; mobile.pages.home
   ;; mobile.pages.main
   ;; mobile.pages.auth
   ;; mobile.pages.pin

   [reagent.core :as r]))


#_
(defn main-page []

  [rn/text "HELLO"]

  #_
  mobile.pages.main/page)


(def main-page mobile.views.main/screen)


(defn App []
  (r/as-element [main-page]))


(defn init []
  (.registerComponent rn/app-registry
                      "FooBar"
                      (fn [] App)))


(defn ^:export -main [& args]
  (App))
