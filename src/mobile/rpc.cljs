(ns mobile.rpc
  (:require
   [RN.fetch :as fetch]

   [mobile.config :as config]))


(defn call

  [{:keys [token
           method
           params]}]

  (let [id (rand-int config/rpc-max-id)

        data (cond-> {:jsonrpc "2.0"
                      :method method
                      :id id}

               params
               (assoc :params params))

        opt (cond-> {:method "POST"
                     :data data}

              token
              (assoc-in [:headers :authorization]
                        (str "Bearer " token)))

        url (str config/base-url "/rpc")]

    (fetch/fetch url opt)))
