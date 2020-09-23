(ns mobile.events
  (:require
   [RN.log :as log :include-macros true]
   [RN.alert :as alert]

   [mobile.spec :as spec]
   [mobile.const :as c]
   [mobile.datascript :as ds]

   [re-frame.core :as rf]
   [datascript.core :as d]

   [clojure.spec.alpha :as s]))


(rf/reg-event-db
 :tx-report
 (fn [db [_ tx-report]]
   (assoc-in db c/path-tx-report tx-report)))


(rf/reg-fx
  :tx-data
  (fn [tx-data]
    (d/transact! ds/conn tx-data)))


(rf/reg-event-db
 :auth-input-email
 (fn [db [_ text]]
   (assoc-in db c/path-auth-input-email text)))


(rf/reg-event-fx
 :auth-submit
 (fn [{:keys [db]} [_ navigation]]
   (let [email (get-in db c/path-auth-input-email)]
     (if (s/valid? ::spec/form-auth {:email email})
       (.navigate navigation "pin")
       (alert/alert
        "Wrong email"
        "We could not recognize an email address in your input.")))))
