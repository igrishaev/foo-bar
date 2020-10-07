(ns mobile.views.feed-preview
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.util :as util]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn render-item [input]

  (let [
        ;; width (dim/get-window-width)

        entry (.. input -item)
        index (.. input -index)

        {:keys [title]} entry

        ]

    (r/as-element
     [rn/view {:style {:width 300
                       ;; :height 100
                       }}
      [rn/text title]])))


(defn screen [{:keys [route
                      navigation]}]

  (let [i (.. route -params -i)
        feed-id (.. route -params -feed_id)

        path (into config/path-remote-search [i :entries])

        entries @(rf/subscribe [:rn/get-in path])]

    (letfn [(get-item-count []
              (count entries))

            (get-item [_ index]
              (get entries index))

            (key-extractor [item index]
              (util/format "entry-%s" (:id item)))]

      [rn/virtualized-list

       {:data #js []
        :onScrollEndDrag
        (fn []
          (println "onScrollEndDrag"))

        :removeClippedSubviews true
        :initialNumToRender 2
        :maxToRenderPerBatch 1
        :updateCellsBatchingPeriod 100
        :windowSize 2

        :horizontal true
        :pagingEnabled true

        :keyExtractor key-extractor
        :getItemCount get-item-count
        :getItem get-item
        ;; :onEndReached on-end-reached
        :renderItem render-item}])))


(def Screen (r/reactify-component screen))
