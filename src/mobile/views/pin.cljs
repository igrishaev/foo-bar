(ns mobile.views.pin
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
             :fontSize 32
             :color "black"
             :height 64
             :borderBottomWidth 2
             :borderBottomColor "black"
             :paddingLeft 20
             :paddingRight 20}
     :autoCapitalize "none"
     :placeholder "PIN"
     :textAlign "center"
     :autoFocus true
     :keyboardType "number-pad"
     :on-change-text
     (form/setter config/path-form-pin :pin)}]

   [rn/touchable-opacity
    {:style {:marginTop 40
             :height 50}
     :on-press
     (fn []

       (rf/dispatch [:rn/assoc-in config/path-token "AAA"])

       #_
       (rf/dispatch [:pin-submit]))}

    [rn/text {:style {:fontSize 24
                      :textAlign "center"}}
     "Log in"]]])


(def Screen (r/reactify-component screen))
