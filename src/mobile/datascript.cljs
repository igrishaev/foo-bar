(ns mobile.datascript
  (:require
   [mobile.const :as const]

   [re-frame.core :as rf]
   [datascript.core :as d]))


(def schema
  {
   :subscription/feed
   {:db/type        :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   :user/email
   {:db/type        :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   :user/fullname
   {:db/type        :db.type/string
    :db/cardinality :db.cardinality/many}

   })


(def conn (d/create-conn schema))


(defn on-db-change
  [tx-report]
  (rf/dispatch [:assoc-in const/path-tx-report
                (dissoc tx-report :db-before)]))


(d/listen! conn on-db-change)


(defn q [query & args]
  (apply d/q query @conn args))


(defn pull-many [ids pattern]
  (d/pull-many @conn pattern ids))



(def pattern-subs
  [:db/id
   {:subscription/feed [:db/id
                        :feed/title]}])

;;
;; Quereis
;;

(def q-sub-ids
  '[:find [?e ...]
    :in $
    :where
    [?e :subscription/feed]])





#_
(def q-subs
  '[:find [(pull ?e [* {:subscription/feed [*]}]) ...]
    :in $
    :where
    [?e :subscription/feed]])
