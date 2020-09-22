(ns mobile.pages.nav
  (:require
   [RN.nav :as nav]

   mobile.pages.home
   mobile.pages.about

   [reagent.core :as r]))


(defn page []

  (let [{:keys [stack-navigator stack-screen]}
        (nav/create-stack-navigator)]

    [nav/safe-area-provider
     [nav/navigation-container
      [stack-navigator
       [stack-screen
        {:name "AAAA"
         :component mobile.pages.home/Page}]
       [stack-screen
        {:name "BBBB"
         :component mobile.pages.about/Page}]]]]))


(def Page (r/reactify-component page))
