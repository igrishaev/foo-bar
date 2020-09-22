(ns mobile.db
  (:require
   [datascript.core :as d]))


(def schema {:car/maker {:db/type :db.type/ref}
             :car/colors {:db/cardinality :db.cardinality/many}})

(def conn (d/create-conn schema))


#_
(d/transact! conn [{:maker/name true
                    :maker/country 42}])


#_
(d/transact! conn
             [{:db/id -1
               :maker/name "BMW"
               :maker/country "Germany"}
              {:car/maker -1
               :car/name "i525"
               :car/colors ["red" "green" "blue"]}])
