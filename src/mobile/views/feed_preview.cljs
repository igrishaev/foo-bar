(ns mobile.views.feed-preview
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.util :as util]
   [RN.dimensions :as dim]
   [RN.webview :refer [web-view]]
   [RN.linking :as linking]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn on-message [entry]

  (let [{:keys [link]} entry]

    (fn [event]

      (let [data (.. event -nativeEvent -data)]
        (case data

          ("h:title" "btn:visit_page")
          (linking/open-url link)

          ;; TODO: better logging
          ;; else
          (println data))))))


(defn entry-item [entry]

  ;; todo: width
  (let [width (dim/get-window-width)

        {entry-id :id} entry

        url (str config/base-url "/preview?entry-id="entry-id)

        ]

    [rn/view {:style {:width width}}

     [web-view

      {:onMessage (on-message entry)
       :originWhitelist #js ["*"]
       :source {:uri url}}]]))


(defn render-item [input]

  (let [entry (.. input -item)
        index (.. input -index)]

    (r/as-element
     [entry-item entry])))


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
