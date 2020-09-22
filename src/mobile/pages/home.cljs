(ns mobile.pages.home
  (:require
   [RN.core :as rn]

   [re-frame.core :as rf]
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

    "HOME2"]])


(def Page (r/reactify-component page))
