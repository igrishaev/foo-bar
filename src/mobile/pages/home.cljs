(ns mobile.pages.home
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.log :as log]

   [mobile.datascript :as ds]
   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [navigation]}]

  (let [sub-ids @(rf/subscribe [:query ds/q-sub-ids])
        subs (ds/pull-many sub-ids ds/pattern-subs)]

    [rn/view {:style {:padding 30
                      :flex 1
                      :flexDirection "column"
                      :justifyContent "center"
                      :alignItems "stretch"}}

     (for [{:as sub
            sub-id :db/id} subs]

       (let [{subs-id :db/id
              subs-title :subscription/title
              feed :subscription/feed } sub

             {feed-title :feed/title} feed

             title (or subs-title feed-title)]

         ^{:key subs-id}
         [rn/view {:style {:padding 20}}

          [rn/text {:style {:padding 20}} feed-title]]))


     #_
     [rn/touchable-opacity
      {:style {:marginTop 40
               :height 50}
       :on-press (fn []
                   (rf/dispatch
                    [:foo-bar]))}

      [rn/text {:style {:fontSize 24
                        :textAlign "center"}}
       "ADD DATA"]]

     #_
     [rn/text {:style {:fontSize 24
                       :textAlign "center"}}
      "HOME SWEET HOME"]]))


(def Page (r/reactify-component page))
