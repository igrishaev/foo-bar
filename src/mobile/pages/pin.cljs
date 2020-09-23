(ns mobile.pages.pin
  (:require
   [RN.core :as rn]
   [RN.fetch :as fetch]
   [RN.log :as log :include-macros true]

   [mobile.style :as style]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

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
             :paddingRight 20

             }
     :autoCapitalize "none"
     :placeholder "PIN"
     :textAlign "center"
     :autoFocus true
     :keyboardType "number-pad"
     :on-change-text
     (fn [text]
       #_
       (rf/dispatch [:auth-input-email text]))}]

   [rn/touchable-opacity
    {:style {:marginTop 40
             :height 50}
     :on-press (fn []
                 #_
                 (rf/dispatch [:auth-submit navigation]))}

    [rn/text {:style {:fontSize 24
                      :textAlign "center"}}
     "Log in"]]])


(def Page (r/reactify-component page))
