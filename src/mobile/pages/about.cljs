(ns mobile.pages.about
  (:require
   [RN.core :as rn]

   [reagent.core :as r]))


(defn page [{:keys [navigation]}]
  [rn/view {:style
            {:flex-direction "column"
             :margin 40
             :align-items "center"}}
   [rn/text {:style
             {:font-size 30
              :font-weight "100"
              :margin-bottom 20
              :text-align "center"}}
    "ABOUT"]
   [rn/touchable-highlight
    {:style
     {:background-color "#999"
      :padding 10
      :border-radius 5}
     :on-press (fn []
                 (.navigate navigation "BBBB"))}
    [rn/text {:style {:color "white"
                      :text-align "center"
                      :font-weight "bold"}}
     "aaa"]]])


(def Page (r/reactify-component page))
