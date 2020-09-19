(ns mobile.fetch
  (:require
   [clojure.string :as str]))


(defn response-json? [headers]
  (some-> headers
          :content-type
          (.includes "application/json")))


(defn response->headers [^js/Response response]
  (-> response
      .-headers
      .-map
      (js->clj :keywordize-keys true)))


(defn clj->json [data]
  (some-> data clj->js js/JSON.stringify))


(defn fetch [url & [opt]]

  (let [{:keys [method data]} opt

        opt (cond-> opt

              (nil? method)
              (assoc :method "GET")

              (some? data)
              (->
               (update :data clj->json)
               (assoc-in [:headers :content-type]
                         "application/json")))]

    (-> (js/fetch url (clj->js opt))

        (.then
         (fn [^js/Response response]

           (let [headers (response->headers response)

                 json? (response-json? headers)

                 result {:ok? (-> response .-ok)
                         :json? json?
                         :status (-> response .-status)
                         :headers headers}]

             (-> (if json?
                   (.json response)
                   (.text response))

                 (.then
                  (fn [payload]
                    (if json?
                      (assoc result :data
                             (js->clj payload
                                      :keywordize-keys true))
                      (assoc result :text payload)))))))))))
