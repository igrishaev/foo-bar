(ns mobile.pages.main
  (:require
   [RN.nav :as nav]

   mobile.pages.home
   mobile.pages.auth
   mobile.pages.pin

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page []

  (let [{:keys [stack-navigator stack-screen]}
        (nav/create-stack-navigator)]

    [nav/safe-area-provider
     [nav/navigation-container
      [stack-navigator

       (let [token @(rf/subscribe [:auth-token])]

         (if (some? token)

           [:<>
            [stack-screen
             {:name "Home"
              :component mobile.pages.home/Page}]]

           [:<>
            [stack-screen
             {:name "Auth"
              :component mobile.pages.auth/Page}]
            [stack-screen
             {:name "Pin"
              :component mobile.pages.pin/Page}]]))]]]))


(def Page (r/reactify-component page))
