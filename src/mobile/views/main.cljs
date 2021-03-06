(ns mobile.views.main
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [RN.flash-message :refer [flash-message]]

   [mobile.config :as config]

   mobile.views.tab-auth
   mobile.views.tab-non-auth

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn screen []

  [nav/safe-area-provider
   [nav/navigation-container

    (let [token @(rf/subscribe
                  [:rn/get-in config/path-token])

          token "aaaaaaa"

          ]

      (if token
        [mobile.views.tab-auth/screen]
        [mobile.views.tab-non-auth/screen]))]

   [flash-message {:position "top"}]])


(def Screen (r/reactify-component screen))
