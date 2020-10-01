(ns mobile.events
  (:require
   [mobile.spec :as spec]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [clojure.spec.alpha :as s]))


;;
;; RPC
;;

(rf/reg-event-fx
 :rpc-http-error
 (fn [_ [_ input response]]
   {:rn/debug
    {:message "RPC response was non-200"
     :input input
     :response response}}))


(rf/reg-event-fx
 :rpc-failure
 (fn [_ [_ input error]]
   {:rn/debug
    {:message "RPC response failed"
     :input input
     :err error}}))


;;
;; Auth
;;

(rf/reg-event-fx
 :auth-submit
 (fn [{:keys [db]} [_ navigation]]
   (let [form (get-in db config/path-form-auth)
         {:keys [email]} form]
     (if (s/valid? ::spec/form-auth form)
       {:dispatch [:auth-submit-ok navigation email]}
       {:rn/show-message
        {:type "danger"
         :message "Wrong email"
         :description "We could not recognize an email address in your input."}}))))


(rf/reg-event-fx
 :auth-submit-ok
 (fn [{:keys [db]} [_ navigation email]]
   {:rpc
    {:method "auth/request-pin"
     :params {:email email}
     :event-ok [:pin-has-been-sent navigation]}}))


(rf/reg-event-fx
 :pin-has-been-sent
 (fn [_ [_ navigation response]]
   (.navigate navigation "pin")))


;;
;; PIN
;;

(rf/reg-event-fx
 :pin-submit
 (fn [{:keys [db]} [_]]
   (let [form (get-in db config/path-form-pin)
         {:keys [pin]} form]
     (if (s/valid? ::spec/form-pin form)
       {:dispatch [:pin-submit-ok pin]}
       {:rn/show-message
        {:type "danger"
         :message "Wrong PIN"
         :description "We could not recognize a PIN code in your input."}}))))


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
   (assoc-in db config/path-token
             (-> response :data :result :secret))))


;;
;;
;;

(rf/reg-event-fx
 :search-submit
 (fn [{:keys [db]} [_]]
   (let [form (get-in db config/path-form-search)
         {:keys [q]} form]
     (if (s/valid? ::spec/form-search form)
       {:dispatch [:search-submit-ok q]}
       {:rn/alert
        ["Wrong query"
         "We could not recognize a query in your input."]}))))


(defn ctx->token [ctx]
  "18b0299c-9f99-4ea0-801e-4db0874bebe4"

  #_
  (-> ctx :db (get-in config/path-token)))


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
   (assoc-in db config/path-remote-search
             (-> response :data :result :data))))


(rf/reg-event-fx
 :sync-subscriptions
 (fn [_ [_]]
   {:rpc {:method "feed/sync-feeds"
          :event-ok [:feeds-synced-ok]}}))
