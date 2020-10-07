(ns mobile.views.stack-search
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.feed-search
   mobile.views.feed-preview

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
     {:name "feed-search"
      :component mobile.views.feed-search/Screen
      :options #js {:title "Feed search"}}]

    [stack-screen
     {:name "feed-preview"
      :component mobile.views.feed-preview/Screen
      :options #js {:title "Feed preview"}}]]])


(def Screen (r/reactify-component screen))
