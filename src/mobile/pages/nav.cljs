(ns mobile.pages.nav
  (:require
   [mobile.rn :as rn]

   mobile.pages.home
   mobile.pages.about

   [reagent.core :as r]))


(defn page []

  (let [[stack-navigator stack-screen]
        (rn/create-stack-navigator)]

    [rn/safe-area-provider
     [rn/navigation-container
      [stack-navigator
       [stack-screen
        {:name "AAAA"
         :component mobile.pages.home/Page}]
       [stack-screen
        {:name "BBBB"
         :component mobile.pages.about/Page}]]]]))


(def Page (r/reactify-component page))
