(ns mobile.views.main
  (:require
   [RN.core :as rn]
   [RN.nav :as nav]
   [RN.log :as log]

   [mobile.config :as config]

   mobile.views.tab-auth
   mobile.views.tab-non-auth

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn screen []

  [nav/safe-area-provider
   [nav/navigation-container

    (let [token @(rf/subscribe
                  [:get-in config/path-token])]

      (if token
        [mobile.views.tab-auth/screen]
        [mobile.views.tab-non-auth/screen]))]])


(def Screen (r/reactify-component screen))
