(ns mobile.views.main-auth
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.subs
   mobile.views.search
   mobile.views.user
   mobile.views.about

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
     {:name "subs"
      :component mobile.views.subs/Screen}]

    [tab-screen
     {:name "search"
      :component mobile.views.search/Screen}]

    [tab-screen
     {:name "user"
      :component mobile.views.user/Screen}]

    [tab-screen
     {:name "about"
      :component mobile.views.about/Screen}]]])


(def Screen (r/reactify-component screen))
