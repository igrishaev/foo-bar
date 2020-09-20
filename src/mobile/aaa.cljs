(ns mobile.aaa
  (:require
   [re-frame.core :as rf]
   [datascript.core :as d]))


(def path-tx-report [:tx-report])


(def conn
  (d/create-conn
   {:foo/name
    {:db.valueType :db.type/string
     :db.cardinality :db.cardinality/one}}))


(rf/reg-event-db
 ::tx-report
 (fn [db [_ tx-report]]
   (assoc-in db path-tx-report tx-report)))


(rf/reg-event-db
 :save-simple
 (fn [db [_ ]]
   (js/console.log (pr-str :save-simple))
   (assoc-in db [:foo] (rand-int 9999))))


(defn on-db-change
  [{:as tx-report
    :keys [db-before
           db-after
           tx-data
           tempids
           tx-meta]}]
  (rf/dispatch [::tx-report tx-report]))


(d/listen! conn on-db-change)

#_
(rf/reg-cofx
 :ds
 (fn [coeffects _]
   (assoc coeffects :ds @conn)))


#_
(rf/reg-sub
 ::datomic
 (fn [db _]
   @conn))


(rf/reg-sub
 :test-query

#_
 (fn [datomic]
   (js/console.log (pr-str datomic))

   (d/q '[:find ?e ?name
          :in $
          :where
          [?e :foo/name ?name]]
        datomic))

 (fn [db _]

   (let [tx-report (get-in db path-tx-report)
         {:keys [db-after]} tx-report]

     (d/q '[:find ?e ?name
            :in $
            :where
            [?e :foo/name ?name]]
          db-after))))


(rf/reg-event-fx
 :add-data
 (fn [ctx [_ ]]
   (d/transact! conn [{:foo/name (str (rand-int 999))}])
   nil))
