(ns mobile.pages.home
  (:require
   [mobile.rn :as rn]
   [mobile.fetch :as fetch]

   ;; [mobile.log :as log]

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
    "aaa333"]
   [rn/touchable-highlight
    {:style
     {:background-color "#999"
      :padding 10
      :border-radius 5}
     :on-press (fn []
                 #_
                 (-> (fetch/fetch "hsdfsfssdfsdfsdf")
                     (.then (fn [resp]
                              (log/debug resp)))
                     (.catch (fn [e]
                               (log/debug e)))
                     )

                 #_
                 (.navigate navigation "BBBB"))}
    [rn/text {:style {:color "white"
                      :text-align "center"
                      :font-weight "bold"}}
     "aaa"]]])


(def Page (r/reactify-component page))
