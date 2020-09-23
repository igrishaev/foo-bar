(ns mobile.rpc
  (:require
   [RN.fetch :as fetch]))

#_
(def base-url "http://192.168.31.102:8088/rpc")

(def base-url "http://192.168.1.101:8088/rpc")


(def max-id 1000)


(defn call

  [{:keys [token
           method
           params]}]

  (let [id (rand-int max-id)

        data (cond-> {:jsonrpc "2.0"
                      :method method
                      :id id}

               params
               (assoc :params params))

        opt (cond-> {:method "POST"
                     :data data}

              token
              (assoc-in [:headers :authorization]
                        (str "Bearer " token)))]

    (fetch/fetch base-url opt)))
