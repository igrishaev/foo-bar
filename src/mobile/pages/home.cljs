(ns mobile.pages.home
  (:require
   [RN.core :as rn]
   [RN.fetch :as fetch]
   [RN.log :as log :include-macros true]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

  (let [aaa @(rf/subscribe [:test-query :some-query])

        foo @(rf/subscribe [:pull 8])]

    #_(js/console.log (pr-str aaa))

    [rn/view {:style
              {:flex-direction "column"
               :margin 40
               :align-items "center"}}
     [rn/text {:style
               {:font-size 30
                :font-weight "100"
                :margin-bottom 20
                :text-align "center"}}

      (-> foo pr-str)

      ]
     [rn/touchable-highlight
      {:style
       {:background-color "#999"
        :padding 10
        :border-radius 5}
       :on-press
       (fn []
         (rf/dispatch [:add-data])
         #_
         (.navigate navigation "BBBB"))}
      [rn/text {:style {:color "white"
                        :text-align "center"
                        :font-weight "bold"}}
       "AAA"]]


     [rn/touchable-highlight
      {:style
       {:background-color "#999"
        :padding 10
        :border-radius 5}
       :on-press
       (fn []
         (rf/dispatch [:save-simple]))}
      [rn/text {:style {:color "white"
                        :text-align "center"
                        :font-weight "bold"}}
       "BBB"]]

     ]))


(def Page (r/reactify-component page))
