(ns mobile.views.auth
  (:require
   [RN.core :as rn]
   [RN.form :as form]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn screen [{:keys [navigation]}]

  [rn/view {:style {:padding 30
                    :flex 1
                    :flexDirection "column"
                    :justifyContent "center"
                    :alignItems "stretch"}}

   [rn/text-input
    {:style {:fontFamily "Helvetica Neue"
             :fontSize 24
             :color "black"
             :height 50
             :borderBottomWidth 2
             :borderBottomColor "black"
             :paddingLeft 20
             :paddingRight 20}
     :autoCapitalize "none"
     :placeholder "Email address"
     :keyboardType "email-address"
     :on-change-text
     (form/setter config/path-form-auth :email)}]

   [rn/touchable-opacity
    {:style {:marginTop 40
             :height 50}
     :on-press
     (fn []
       (rf/dispatch [:auth-submit navigation]))}

    [rn/text {:style {:fontSize 24
                      :textAlign "center"}}
     "Get PIN code"]]])


(def Screen (r/reactify-component screen))
