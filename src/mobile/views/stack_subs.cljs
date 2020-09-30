(ns mobile.views.stack-subs
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.subs
   mobile.views.feed
   mobile.views.swiper

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

  [stack-navigator

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
      :options #js {:title "Swiper"}}]]])


(def Screen (r/reactify-component screen))
