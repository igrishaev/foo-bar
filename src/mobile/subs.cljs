(ns mobile.subs
  (:require
   [mobile.const :as const]

   [re-frame.core :as rf]
   [datascript.core :as d]))


(rf/reg-sub
 :db-after
 (fn [db _]
   (-> db
       (get-in const/path-tx-report)
       (get :db-after))))


(rf/reg-sub
 :pull
 :<- [:db-after]
 (fn [db-after [_ selector pattern]]
   (when db-after
     (d/pull db-after
             (or pattern const/pattern)
             selector))))

(rf/reg-sub
 :pull-many
 :<- [:db-after]
 (fn [db-after [_ selectors pattern]]
   (when db-after
     (d/pull-many db-after
                  (or pattern const/pattern)
                  selectors))))


(rf/reg-sub
 :query
 :<- [:db-after]
 (fn [db-after [_ query & args]]
   (when db-after
     (apply d/q query db-after args))))
