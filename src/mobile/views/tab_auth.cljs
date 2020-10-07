(ns mobile.views.tab-auth
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.stack-subs

   mobile.views.search
   mobile.views.user
   mobile.views.about

   mobile.views.swiper

   [re-frame.core :as rf]
   [reagent.core :as r]))


(let [{:keys [tab
              tab-screen
              tab-navigator]}
      (nav/create-bottom-tab-navigator)]

  (def tab tab)
  (def tab-screen tab-screen)
  (def tab-navigator tab-navigator))


(defn screen []

  [tab-navigator

   [:<>

    [tab-screen
     {:name "tab-subs"
      :component mobile.views.stack-subs/Screen
      :options #js {:title "Subscriptions"}}]

    [tab-screen
     {:name "tab-search"
      :component mobile.views.search/Screen
      :options #js {:title "Search"}}]

    [tab-screen
     {:name "tab-user"
      :component mobile.views.user/Screen
      :options #js {:title "User"}}]

    [tab-screen
     {:name "tab-about"
      :component mobile.views.about/Screen
      :options #js {:title "About"}}]]])


(def Screen (r/reactify-component screen))
