(ns mobile.core
  (:require
   [RN.core :as rn]
   [RN.async-storage :as as]
   [RN.log :as log :include-macros true]

   mobile.subs
   mobile.events

   ;; mobile.pages.main
   mobile.pages.home
   mobile.pages.auth

   [reagent.core :as r]))


(def main-page mobile.pages.auth/page)


(defn App []
  (r/as-element [main-page]))


(defn init []
  (.registerComponent rn/app-registry
                      "FooBar"
                      (fn [] App)))


(defn ^:export -main [& args]
  (App))
