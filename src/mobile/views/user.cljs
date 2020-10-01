(ns mobile.views.user
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

   [rn/text {:style {:fontSize 24
                     :textAlign "center"}}
    "User"]

   [rn/touchable-opacity
    {:on-press
     (fn []
       (rf/dispatch [:rn/assoc-in config/path-token nil]))}

    [rn/text {:style {:fontSize 24
                      :textAlign "center"}}
     "Logout"]]])


(def Screen (r/reactify-component screen))
