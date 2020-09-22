(ns mobile.pages.auth
  (:require
   [RN.core :as rn]
   [RN.fetch :as fetch]
   [RN.log :as log :include-macros true]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

  [rn/view {:style
            {:flex-direction "column"
             :margin 40
             :align-items "center"}}

   [rn/text-input
    {:style {:height 160
             :width 500}
     :on-change-text
     (fn [text]
       (rf/dispatch [:auth-input-email text]))}]

   [rn/button
    {:title "Get PIN"
     :on-press (fn []
                 (rf/dispatch [:auth-submit]))}]])


(def Page (r/reactify-component page))
