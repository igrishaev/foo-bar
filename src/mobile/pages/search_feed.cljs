(ns mobile.pages.search-feed
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.log :as log]

   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [route
                    navigation]}]

  (let [search-index (.. route -params -search_index)
        path (conj const/path-remote-search search-index)

        _ (log/debug route)
        _ (log/debug path)

        item @(rf/subscribe [:get-in path])

        {:keys [feed entries]} item]

    [rn/view {:style {:padding 30
                      :flex 1
                      :flexDirection "column"
                      :alignItems "stretch"}}

     (for [entry entries]
       ^{:key (-> entry :db/id)}

       [rn/view {:style {:padding 30}}
        [rn/text {:style {:fontSize 24}}
         (-> entry :entry/title)]])]))


(def Page (r/reactify-component page))
