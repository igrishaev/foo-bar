(ns mobile.views.main
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.main-auth

   ;; mobile.views.auth
   ;; mobile.views.pin
   ;; mobile.views.subs
   ;; mobile.views.feed
   ;; mobile.views.swiper

   [re-frame.core :as rf]
   [reagent.core :as r]))


(let [{:keys [stack
              stack-screen
              stack-navigator]}
      (nav/create-stack-navigator)]

  (def stack stack)
  (def stack-screen stack-screen)
  (def stack-navigator stack-navigator))


(defn screen []

  [nav/safe-area-provider
   [nav/navigation-container

    #_
    [rn/text "AAAAAAAAAa"]

    [mobile.views.main-auth/screen]]



   #_
   [nav/navigation-container
    [stack-navigator

     (let [token @(rf/subscribe
                   [:get-in config/path-token])

           token "aaaaaaa"
           ]



       #_
       (if token

         [:<>
          [stack-screen
           {:name "subs"
            :component mobile.views.subs/Screen
            :options #js {:title "Subscriptions"}}]

          [stack-screen
           {:name "feed"
            :component mobile.views.feed/Screen
            :options #js {:title "Feed"}}]

          [stack-screen
           {:name "swiper"
            :component mobile.views.swiper/Screen
            :options #js {:title "Swiper"}}]]

         ;; --------

         [:<>

          [stack-screen
           {:name "auth"
            :component mobile.views.auth/Screen
            :options #js {:title "Authenticate"}}]

          [stack-screen
           {:name "pin"
            :component mobile.views.pin/Screen
            :options #js {:title "PIN code"}}]]

         ))]]])


(def Screen (r/reactify-component screen))
