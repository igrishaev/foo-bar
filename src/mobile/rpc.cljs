(ns mobile.rpc
  (:require
   [mobile.fetch :as fetch]))


(def base-url "http://...")

(def max-id 1000)


(defn rpc-call

  [{:keys [token
           method
           params]}]

  (let [id (rand-id max-id)

        data (cond-> {:jsonrpc "2.0"
                      :method method
                      :id id}

               params
               (assoc :params params))

        opt (cond-> {:method :POST
                     :data data}

              token
              (assoc-in [:headers :authorization]
                        (format "Bearer %s" token)))]

    (fetch/fetch base-url opt)))
