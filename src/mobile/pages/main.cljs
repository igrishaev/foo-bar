(ns mobile.pages.main
  (:require
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.const :as const]

   mobile.pages.auth
   mobile.pages.pin
   mobile.pages.home
   mobile.pages.search
   mobile.pages.search-feed
   mobile.pages.search-entry

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page []

  (let [{:keys [stack-navigator stack-screen]}
        (nav/create-stack-navigator)]

    [nav/safe-area-provider
     [nav/navigation-container
      [stack-navigator

       (let [token @(rf/subscribe [:get-in const/path-token])

             token "aaaaaaa"
             ]

         (log/debug {:token token})

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
              :options #js {:title "PIN code"}}]]))]]]))


(def Page (r/reactify-component page))
