(ns mobile.core
  (:require
   [mobile.rn :as rn]
   [mobile.async-storage :as as]

   [mobile.log :as log :include-macros true]

   mobile.aaa
   mobile.pages.nav

   [reagent.core :as r]))


(defn App []
  (r/as-element [mobile.pages.nav/page]))


(defn init []
  (.registerComponent rn/app-registry
                      "FooBar"
                      (fn [] App)))


#_
(init)


(defn ^:export -main [& args]
  (App))
