(ns mobile.pages.home
  (:require
   [RN.core :as rn]
   [RN.form :as form]

   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

  [rn/view {:style {:padding 30
                    :flex 1
                    :flexDirection "column"
                    :justifyContent "center"
                    :alignItems "stretch"}}

   [rn/text {:style {:fontSize 24
                      :textAlign "center"}}
     "HOME SWEET HOME"]])


(def Page (r/reactify-component page))
