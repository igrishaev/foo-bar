(ns mobile.datascript
  (:require
   [mobile.const :as const]

   [re-frame.core :as rf]
   [datascript.core :as d]))


(def schema
  {:user/email
   {:db/type        :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   :user/fullname
   {:db/type        :db.type/string
    :db/cardinality :db.cardinality/many}

   })


(def conn (d/create-conn schema))


(defn on-db-change
  [tx-report
   {:as tx-report
    :keys [db-before
           db-after
           tx-data
           tempids
           tx-meta]}]
  (rf/dispatch [:assoc-in const/path-tx-report
                (dissoc tx-report :db-before)]))


(d/listen! conn on-db-change)
