(ns mobile.effects
  (:require
   [mobile.rpc :as rpc]
   [mobile.datascript :as ds]

   [re-frame.core :as rf]
   [datascript.core :as d]))


(rf/reg-fx
 :tx-data
 (fn [tx-data]
   (d/transact! ds/conn tx-data)))


(rf/reg-fx
 :rpc
 (fn [{:as input
       :keys [token
              method
              params
              event-ok
              event-err
              event-catch]}]

   (.. (rpc/call {:token token
                  :method method
                  :params params})

       (then
        (fn [response]
          (if (:ok? response)

            (-> event-ok
                (conj response)
                (rf/dispatch))

            (-> event-err
                (or [:rpc-http-error])
                (conj input response)
                (rf/dispatch)))))

       (catch
           (fn [err]
             (-> event-catch
                 (or [:rpc-failure])
                 (conj input err)
                 (rf/dispatch)))))))
