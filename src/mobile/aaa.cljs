(ns mobile.aaa
  (:require

   [mobile.log :as log]


   [re-frame.core :as rf]
   [datascript.core :as d]))


(def log (-> ::_ namespace log/get-logger))


(def path-tx-report [:tx-report])


(def conn
  (d/create-conn
   {:foo/name
    {:db.valueType :db.type/string
     :db.cardinality :db.cardinality/one}}))


(rf/reg-event-db
 :tx-report
 (fn [db [_ tx-report]]
   (assoc-in db path-tx-report tx-report)))


(rf/reg-event-db
 :save-simple
 (fn [db [_ ]]
   (assoc-in db [:foo] (rand-int 9999))))


(defn on-db-change
  [{:as tx-report
    :keys [db-before
           db-after
           tx-data
           tempids
           tx-meta]}]
  (rf/dispatch [:tx-report tx-report]))


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
 :db-after
 (fn [db _]
   (-> db
       (get-in path-tx-report)
       (get :db-after))))


(rf/reg-sub
 :test-query
 :<- [:db-after]
 (fn [db-after [_ ]]
   (d/q '[:find ?e ?name
          :in $
          :where
          [?e :foo/name ?name]]
        db-after)))


(rf/reg-sub
 :pull
 :<- [:db-after]
 (fn [db-after [_ selector pattern]]
   (when db-after
     (d/pull db-after (or pattern '[*]) selector))))


(rf/reg-sub
 :pull-many
 :<- [:db-after]
 (fn [db-after [_ selectors pattern]]
   (when db-after
     (d/pull-many db-after (or pattern '[*]) selectors))))


(rf/reg-event-fx
 :add-data
 (fn [ctx [_]]
   {:tx-data [{:foo/name (str (rand-int 9))}]}))


(rf/reg-fx
  :tx-data
  (fn [tx-data]
    (d/transact! conn tx-data)))

#_

(def event-collector
  (re-frame.core/->interceptor
   :id      :event-collector
   :before  (fn [context]
              (swap! event-store keep-last-20 (re-frame.core/get-coeffect context :event))
              context)))

;; register this global interceptor early in program's boot process,
;; using re-frame's API
#_
(re-frame.core/reg-global-interceptor event-collector)

(rf/reg-global-interceptor
 (rf/->interceptor
  :id :event-tracker
  :before (fn [context]
            (log :debug "Re-frame event: %s"
                 (-> context :coeffects :event pr-str))
            context)))
