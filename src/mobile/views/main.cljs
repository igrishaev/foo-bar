(ns mobile.views.main
  (:require
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.auth
   mobile.views.pin

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
    [stack-navigator

     (let [token @(rf/subscribe [:get-in config/path-token])

           token "aaaaaaa"
           ]

       [:<>


        [stack-screen
         {:name "auth"
          :component mobile.views.auth/Screen
          :options #js {:title "Authenticate"}}]

        [stack-screen
         {:name "pin"
          :component mobile.views.pin/Screen
          :options #js {:title "PIN code"}}]]

       #_
       (if token

         [:<>
          [stack-screen
           {:name "home"
            :component mobile.pages.home/Page
            :options #js {:title "Home"}}]

          [stack-screen
           {:name "search"
            :component mobile.pages.search/Page
            :options #js {:title "Search"}}]

          [stack-screen
           {:name "search-feed"
            :component mobile.pages.search-feed/Page
            :options #js {:title "Feed preview"}}]

          [stack-screen
           {:name "search-entry"
            :component mobile.pages.search-entry/Page
            :options #js {:title "Entry preview"}}]]

         ;; --------

         [:<>
          [stack-screen
           {:name "auth"
            :component mobile.pages.auth/Page
            :options #js {:title "Your email"}}]

          [stack-screen
           {:name "pin"
            :component mobile.pages.pin/Page
            :options #js {:title "PIN code"}}]]))]]])


(def Screen (r/reactify-component screen))
