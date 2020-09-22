(ns mobile.subs
  (:require
   [RN.log :as log :include-macros true]

   [mobile.const :as c]

   [re-frame.core :as rf]
   [datascript.core :as d]))


(rf/reg-sub
 :auth-token
 (fn [db _]
   (get-in db c/path-token)))


(rf/reg-sub
 :db-after
 (fn [db _]
   (-> db
       (get-in c/path-tx-report)
       (get :db-after))))


(rf/reg-sub
 :pull
 :<- [:db-after]
 (fn [db-after [_ selector pattern]]
   (when db-after
     (d/pull db-after
             (or pattern c/pattern)
             selector))))

(rf/reg-sub
 :pull-many
 :<- [:db-after]
 (fn [db-after [_ selectors pattern]]
   (when db-after
     (d/pull-many db-after
                  (or pattern c/pattern)
                  selectors))))
