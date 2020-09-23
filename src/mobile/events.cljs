(ns mobile.events
  (:require
   [RN.log :as log :include-macros true]
   [RN.alert :as alert]

   [mobile.spec :as spec]
   [mobile.const :as c]

   [re-frame.core :as rf]
   [datascript.core :as d]

   [clojure.spec.alpha :as s]))


(rf/reg-event-db
 :tx-report
 (fn [db [_ tx-report]]
   (assoc-in db c/path-tx-report tx-report)))


(rf/reg-event-db
 :auth-input-email
 (fn [db [_ text]]
   (assoc-in db c/path-auth-input-email text)))


(rf/ref-event-fx
 :pin-has-been-sent
 (fn [_ [_ ]]
   #_
   (.navigate navigation "pin")


   )
 )


(rf/reg-event-fx
 :auth-submit-ok
 (fn [_ [_ scope]]
   {:rpc {:method "auth/request-pin"
          :params {:email "ivan@grishaev.me"}
          :event-ok [:pin-has-been-sent scope]
          :event-err [:rpc-error]
          :event-catch [:rpc-failed]}}))


(rf/reg-event-fx
 :auth-submit
 (fn [{:keys [db]} [_ scope]]
   (let [email (get-in db c/path-auth-input-email)]
     (if (s/valid? ::spec/form-auth {:email email})

       {:dispatch [:auth-submit-ok scope]}


       #_
       (.navigate navigation "pin")

       (alert/alert
        "Wrong email"
        "We could not recognize an email address in your input.")))))
