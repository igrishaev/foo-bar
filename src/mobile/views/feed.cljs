(ns mobile.views.feed
  (:require
   [RN.core :as rn]
   [RN.util :as util]
   [RN.nav :as nav]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(def ENTRIES

  [{:db/id 111111
    :message/entry
    {:db/id 22222
     :entry/title "Entry Title 1"
     :entry/link "http://test.com/link1"}}

   {:db/id 33333
    :message/entry
    {:db/id 444444
     :entry/title "Entry Title 2"
     :entry/link "http://test.com/link2"}}])


(defn entry-item [message]
  (let [{entry :message/entry} message
        {entry-title :entry/title
         entry-link :entry/link} entry]

    [rn/view
     [rn/touchable-opacity
      {:on-press
       (fn []
         #_
         (-> (nav/use-navigation)
             (.navigate "feed")))}

      [rn/text {:style {:fontSize 18}}
       entry-title]]]))


(defn screen [{:keys [route
                      navigation]}]

  (let [ ;; subs @(rf/subscribe
        ;;        [:get-in config/path-remote-subs])

        ;; subs SUBS

        entries ENTRIES

        ]

    [rn/scroll-view
     {:contentContainerStyle
      {:padding 30
       :flex 1
       :flexDirection "column"
       :alignItems "stretch"}}

     (for [[i entry] (util/enumerate entries)
           :let [{entry-id :db/id} entry]]

       ^{:key entry-id}
       [entry-item (assoc entry :rf/index i)])]))


(def Screen (r/reactify-component screen))
