(ns mobile.events
  (:require
   [mobile.spec :as spec]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [clojure.spec.alpha :as s]))


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
 :sync-subscriptions
 (fn [_ [_]]
   {:rpc {:method "feed/sync-feeds"
          :event-ok [:feeds-synced-ok]}}))


(rf/reg-event-fx
 :auth-submit
 (fn [{:keys [db]} [_ scope]]
   (let [form (get-in db const/path-form-auth)
         {:keys [email]} form]
     (if (s/valid? ::spec/form-auth form)
       {:dispatch [:auth-submit-ok scope email]}
       {:alert
        ["Wrong email"
         "We could not recognize an email address in your input."]}))))


(rf/reg-event-fx
 :auth-submit-ok
 (fn [{:keys [db]} [_ scope email]]
   {:rpc
    {:method "auth/request-pin"
     :params {:email email}
     :event-ok [:pin-has-been-sent scope]}}))


(rf/reg-event-fx
 :pin-submit
 (fn [{:keys [db]} [_]]
   (let [form (get-in db const/path-form-pin)
         {:keys [pin]} form]
     (if (s/valid? ::spec/form-pin form)
       {:dispatch [:pin-submit-ok pin]}
       {:alert
        ["Wrong PIN"
         "We could not recognize a PIN code in your input."]}))))


(rf/reg-event-fx
 :pin-submit-ok
 (fn [_ [_ pin]]
   {:rpc
    {:method "auth/obtain-token"
     :params {:pin pin}
     :event-ok [:token-obtained]}}))


(rf/reg-event-db
 :token-obtained
 (fn [db [_ response]]
   (assoc-in db const/path-token
             (-> response :data :result :secret))))


(rf/reg-event-fx
 :search-submit
 (fn [{:keys [db]} [_]]
   (let [form (get-in db const/path-form-search)
         {:keys [q]} form]
     (if (s/valid? ::spec/form-search form)
       {:dispatch [:search-submit-ok q]}
       {:alert
        ["Wrong query"
         "We could not recognize a query in your input."]}))))


(defn ctx->token [ctx]
  "7f45f266-c6f7-4221-b39e-89728b38e279"

  #_
  (-> ctx :db (get-in const/path-token)))


(rf/reg-event-fx
 :search-submit-ok
 (fn [ctx [_ q]]
   {:rpc
    {:token (ctx->token ctx)
     :method "feed/discover"
     :params {:q q}
     :event-ok [:got-search-result]}}))


(rf/reg-event-db
 :got-search-result
 (fn [db [_ response]]
   (assoc-in db const/path-remote-search
             (-> response :data :result :data))))
