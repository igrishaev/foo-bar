(ns mobile.events
  (:require
   [RN.log :as log :include-macros true]

   [mobile.const :as c]
   [mobile.datascript :as ds]

   [re-frame.core :as rf]
   [datascript.core :as d]))


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
 (fn [{:keys [db]} [_ text]]
   (let [email (get-in db c/path-auth-input-email)]
     (js/console.log email))))
