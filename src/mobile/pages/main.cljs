(ns mobile.pages.main
  (:require
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.const :as const]

   mobile.pages.auth
   mobile.pages.pin
   mobile.pages.home

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page []

  (let [{:keys [stack-navigator stack-screen]}
        (nav/create-stack-navigator)]

    [nav/safe-area-provider
     [nav/navigation-container
      [stack-navigator

       (let [token @(rf/subscribe [:get-in const/path-token])]

         (log/debug {:token token})

         (if token

           [:<>
            [stack-screen
             {:name "Home"
              :component mobile.pages.home/Page
              :options #js {:title "Home"}}]]

           [:<>
            [stack-screen
             {:name "auth"
              :component mobile.pages.auth/Page
              :options #js {:title "Your email"}}]

            [stack-screen
             {:name "pin"
              :component mobile.pages.pin/Page
              :options #js {:title "PIN code"}}]]))]]]))


(def Page (r/reactify-component page))
