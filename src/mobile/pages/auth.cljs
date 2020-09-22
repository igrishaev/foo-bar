(ns mobile.pages.auth
  (:require
   [RN.core :as rn]
   [RN.fetch :as fetch]
   [RN.log :as log :include-macros true]

   [mobile.style :as style]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

  [rn/view {:style style/container}


   #_
   [rn/view {:style {:flex 1
                     :flexDirection "row"
                     :alignItems "stretch"}}]


   [rn/text-input
    {:style style/input
     :placeholder "Email address"
     :keyboardType "email-address"
     :on-change-text
     (fn [text]
       (rf/dispatch [:auth-input-email text]))}]

   [rn/button
    {:title "Get PIN"
     :on-press (fn []
                 (rf/dispatch [:auth-submit]))}]])


(def Page (r/reactify-component page))
