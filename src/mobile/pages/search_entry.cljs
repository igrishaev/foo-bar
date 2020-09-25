(ns mobile.pages.search-entry
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

  (let [idx-feed (.. route -params -idx_feed)
        idx-entry (.. route -params -idx_entry)

        path (conj const/path-remote-search idx-feed :entries idx-entry)

        _ (log/debug path)

        entry @(rf/subscribe [:get-in path])]

    (log/debug entry)

    [rn/scroll-view
     {:contentContainerStyle
      {:padding 30
       :flex 1
       :flexDirection "column"
       :alignItems "stretch"}
      }

     #_
     {:style {:padding 30
              :flex 1
              :flexDirection "column"
              :alignItems "stretch"}}

     [rn/text {:style {:fontSize 24}}
      (-> entry :entry/title)]

     [rn/text {:style {:fontSize 18}}
      (-> entry :entry/content)]]))


(def Page (r/reactify-component page))
