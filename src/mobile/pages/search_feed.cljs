(ns mobile.pages.search-feed
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.log :as log]
   [RN.util :as util]

   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [route
                    navigation]}]

  (let [idx-feed (.. route -params -search_index)
        path (conj const/path-remote-search idx-feed)

        _ (log/debug route)
        _ (log/debug path)

        item @(rf/subscribe [:get-in path])

        {:keys [feed entries]} item

        idx-entries (util/enumerate entries)]

    [rn/view {:style {:padding 30
                      :flex 1
                      :flexDirection "column"
                      :alignItems "stretch"}}

     (for [[idx-entry entry] idx-entries]
       ^{:key (-> entry :db/id)}

       [rn/view {:style {:padding 30}}

        [rn/touchable-without-feedback
         {:on-press (fn []
                      (.navigate navigation "search-entry"
                                 #js {:idx_feed idx-feed
                                      :idx_entry idx-entry}))}

         [rn/text {:style {:fontSize 24}}
          (-> entry :entry/title)]]])]))


(def Page (r/reactify-component page))
