(ns mobile.datascript
  (:require
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
