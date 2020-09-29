(ns mobile.views.subs
  (:require
   [RN.core :as rn]
   [RN.util :as util]
   [RN.nav :as nav]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(def SUBS
  [{:db/id 111111111
    :subscription/title "Subs Title 1"
    :subscription/feed
    {:db/id 111111112
     :feed/title "Feed Title 1"
     :feed/url "http://site.com/rss"
     :feed/link "http://site.com/"}}

   {:db/id 222222222
    :subscription/title "Subs Title 2"
    :subscription/feed
    {:db/id 22222223
     :feed/title "Feed Title 2"
     :feed/url "http://example.com/rss"
     :feed/link "http://example.com/"}}])



(defn subs-item [i navigation subs]

  (let [{subs-title :subscription/title
         feed :subscription/feed} subs

        {feed-title :feed/title
         feed-url :feed/url
         feed-link :feed/link} feed

        title (or subs-title feed-title)]

    [rn/view
     [rn/touchable-opacity
      {:on-press
       (fn []
         (.navigate navigation "feed"))}
      [rn/text {:style {:fontSize 18}} title]]]))


(defn screen [{:keys [navigation]}]

  (let [subs @(rf/subscribe
               [:get-in config/path-remote-subs])

        subs SUBS

        ]

    [rn/scroll-view
     {:contentContainerStyle
      {:padding 30
       :flex 1
       :flexDirection "column"
       :alignItems "stretch"}}

     (for [[i subs] (util/enumerate subs)
           :let [{subs-id :db/id} subs]]

       ^{:key subs-id}
       [subs-item i navigation subs])]))


(def Screen (r/reactify-component screen))
