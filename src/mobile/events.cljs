(ns mobile.events
  (:require
   [RN.log :as log :include-macros true]
   [RN.alert :as alert]

   [mobile.spec :as spec]
   [mobile.const :as c]

   [re-frame.core :as rf]
   [datascript.core :as d]

   [clojure.spec.alpha :as s]))


(rf/reg-event-db
 :tx-report
 (fn [db [_ tx-report]]
   (assoc-in db c/path-tx-report tx-report)))


(rf/reg-event-db
 :auth-input-email
 (fn [db [_ text]]
   (assoc-in db c/path-auth-input-email text)))


(rf/reg-event-fx
 :pin-has-been-sent
 (fn [_ [_
         scope
         response]]
   (some-> scope
           (get :navigation)
           (.navigate "pin"))))


(rf/reg-event-fx
 :rpc-http-error
 (fn [_ [_ input response]]
   {:debug {:message "RPC response was non-200"
            :input input
            :response response}}))


(rf/reg-event-fx
 :rpc-failure
 (fn [_ [_ input error]]
   {:debug {:message "RPC response failed"
            :input input
            :err error}}))


(rf/reg-event-fx
 :feeds-synced-ok
 (fn [_ [_ response]]
   {:tx-data (-> response :data :subs)}))


(rf/reg-event-fx
 :auth-submit-ok
 (fn [{:keys [db]} [_ scope]]
   (let [email (-> db
                   (get-in c/path-form-auth)
                   (get :email))]
     {:rpc
      {:method "auth/request-pin"
       :params {:email email}
       :event-ok [:pin-has-been-sent scope]}})))


(rf/reg-event-fx
 :sync-subscriptions
 (fn [_ [_]]
   {:rpc {:method "feed/sync-feeds"
          :event-ok [:feeds-synced-ok]}}))


(rf/reg-event-fx
 :auth-submit
 (fn [{:keys [db]} [_ scope]]
   (let [data (get-in db c/path-form-auth)]
     (if (s/valid? ::spec/form-auth data)
       {:dispatch [:auth-submit-ok scope]}
       {:alert
        ["Wrong email"
         "We could not recognize an email address in your input."]}))))
