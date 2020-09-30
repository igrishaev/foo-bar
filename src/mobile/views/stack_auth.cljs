(ns mobile.views.stack-auth
  (:require
   [RN.core :as rn]
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

  [stack-navigator

   [:<>
    [stack-screen
     {:name "auth"
      :component mobile.views.auth/Screen
      :options #js {:title "Authentication"}}]

    [stack-screen
     {:name "pin"
      :component mobile.views.pin/Screen
      :options #js {:title "Pin code"}}]]])


(def Screen (r/reactify-component screen))
