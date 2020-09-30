(ns mobile.views.tab-non-auth
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.stack-auth
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
     {:name "tab-auth"
      :component mobile.views.stack-auth/Screen
      :options #js {:title "Authentication"}}]

    [tab-screen
     {:name "tab-about"
      :component mobile.views.about/Screen
      :options #js {:title "About"}}]]])


(def Screen (r/reactify-component screen))
