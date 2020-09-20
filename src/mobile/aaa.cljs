(ns mobile.aaa
  (:require
   [re-frame.core :as rf]
   [datascript.core :as d]))


(def conn
  (d/create-conn
   {:foo/name
    {:db.valueType :db.type/string
     :db.cardinality :db.cardinality/one}}))


(rf/reg-event-db
 :save-db
 (fn [db [_ ds-database]]
   (assoc-in db [:ds-database] ds-database)))


(rf/reg-event-db
 :save-simple
 (fn [db [_ ]]
   (js/console.log (pr-str :save-simple))
   (assoc-in db [:foo] (rand-int 9999))))


(defn on-db-change
  [{:keys [db-before
           db-after
           tx-data
           tempids
           tx-meta]}]
  (rf/dispatch [:save-db db-after]))


(d/listen! conn on-db-change)


(rf/reg-sub
 :test-query
 (fn [{:as db
       :keys [ds-database]} [_]]

   (js/console.log "--------------")
   (js/console.log (pr-str ds-database))

   (d/q '[:find ?e ?name
          :in $
          :where
          [?e :foo/name ?name]]
        ds-database)))


(rf/reg-event-fx
 :add-data
 (fn [ctx [_ ]]
   (d/transact! conn [{:foo/name (str (rand-int 999))}])
   nil))
