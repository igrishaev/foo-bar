(ns mobile.pages.search
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.log :as log]

   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

  (let [search @(rf/subscribe [:get-in const/path-remote-search])]

    (log/debug search)

    [rn/view {:style {:padding 30
                      :flex 1
                      :flexDirection "column"
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
       :placeholder "search feed"
       :on-change-text
       (form/setter const/path-form-search :q)}]

     [rn/touchable-opacity
      {:style {:marginTop 40
               :height 50}
       :on-press (fn []
                   (rf/dispatch [:search-submit]))}

      [rn/text {:style {:fontSize 24
                        :textAlign "center"}}
       "Search"]]

     (for [item search]
       ^{:key (-> item :feed :db/id)}

       [rn/view {:style {:padding 30}}

        [rn/touchable-opacity
         {:style {:marginTop 40
                  :height 50}
          :on-press
          (fn []
            (.navigate navigation "search-feed"
                       #js {:search_index 0}))}

         [rn/text {:style {:fontSize 24}}

          (-> item :feed :feed/url)]]])]))


(def Page (r/reactify-component page))
