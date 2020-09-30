(ns mobile.views.swiper
  (:require
   [RN.core :as rn]
   [RN.util :as util]
   [RN.nav :as nav]
   [RN.swiper :refer [swiper]]

   [mobile.style :as style]
   [mobile.config :as config]

   mobile.views.entry

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


(defn entry-item [i message]

  (let [{entry :message/entry} message
        {entry-title :entry/title
         entry-link :entry/link} entry]

    [rn/view
     [rn/touchable-opacity
      {:on-press
       (fn [])}

      [rn/text {:style {:fontSize 18}}
       entry-title]]]))


(defn screen [{:keys [route
                      navigation]}]

  (let [index (or (.. route -params -index) 0)

        ;; subs @(rf/subscribe
        ;;        [:get-in config/path-remote-subs])

        ;; subs SUBS

        entries ENTRIES

        ]

    [swiper

     {:horizontal true
      :loop false
      ;; :showsButtons false
      ;; :showsPagination false
      :autoplay false
      :onIndexChanged (fn [index]
                        (println index))

      :index index

      :loadMinimal true
      :loadMinimalSize 1
      :bounces true

      }

     (for [[i entry] (util/enumerate entries)
           :let [{entry-id :db/id} entry]]

       ^{:key entry-id}
       [mobile.views.entry/screen i entry])]))


(def Screen (r/reactify-component screen))
