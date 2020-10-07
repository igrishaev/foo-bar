(ns mobile.views.feed-search
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.util :as util]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn search-item [i feed navigation]

  (let [{:keys [id
                title
                description]} feed]

    [rn/view

     [rn/touchable-opacity
      {:style {:margin 30}
       :on-press
       (fn []
         (rf/dispatch [::preview i id navigation]))}

      [rn/text title]
      [rn/text description]]]))


(defn search-result [navigation]

  (let [feeds @(rf/subscribe
                [:rn/get-in config/path-remote-search])]

    [rn/view

     (for [[i feed] (util/enumerate feeds)]

       ^{:key (:id feed)}
       [search-item i feed navigation])]))


(defn btn-search []
  [rn/touchable-opacity
   {:style {:marginTop 40
            :height 50}
    :on-press
    (fn []
      (rf/dispatch [::submit]))}

   [rn/text {:style {:fontSize 24
                     :textAlign "center"}}
    "Search"]])


(defn btn-clear []
  [rn/touchable-opacity
   {:style {:marginTop 40
            :height 50}
    :on-press
    (fn []
      (rf/dispatch [::clear]))}

   [rn/text {:style {:fontSize 24
                     :textAlign "center"}}
    "Clear"]])


(defn screen [{:keys [navigation]}]

  (let [feeds @(rf/subscribe
                [:rn/get-in config/path-remote-search])]

    [rn/view {:style {:padding 30
                      :flex 1
                      :flexDirection "column"
                      :justifyContent "center"
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
       :placeholder "URL, domain, or a query term"
       :on-change-text
       (form/setter config/path-form-search :query)}]

     [btn-search]
     [btn-clear]

     [search-result navigation]]))


(def Screen (r/reactify-component screen))


(rf/reg-event-db
 ::clear
 (fn [db [_]]
   (-> db
       (assoc-in config/path-remote-search nil)
       (assoc-in config/path-form-search nil))))


(rf/reg-event-fx
 ::submit
 (fn [{:keys [db]} _]

   ;; TODO: check if empty

   (let [term (-> db
                  (get-in config/path-form-search)
                  (get :query))]

     {:rpc
      {:token "abcdef0123456789"
       :method "feed/discover"
       :params {:q term}
       :event-ok [::rpc-search-ok]}})))


(rf/reg-event-db
 ::rpc-search-ok
 (fn [db [_ response]]
   (assoc-in db
             config/path-remote-search
             (-> response :data :result :feeds))))


(rf/reg-event-fx
 ::preview
 (fn [_ [_ i feed-id navigation]]
   {:rpc
    {:token "abcdef0123456789"
     :method "feed/preview"
     :params {:feed-id feed-id}
     :event-ok [::rpc-preview-ok i feed-id navigation]}}))


(rf/reg-event-fx
 ::rpc-preview-ok
 (fn [{:keys [db]} [_ i feed-id navigation response]]

   (.navigate navigation "feed-preview"
              #js {:i i
                   :feed_id feed-id})

   {:db (assoc-in db
                  (into config/path-remote-search [i :entries])
                  (-> response :data :result :entries))}))
