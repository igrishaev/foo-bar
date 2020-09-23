(ns RN.form
  (:require
   [re-frame.core :as rf]))


(defn setter [path field]
  (fn [value]
    (rf/dispatch [:form-input
                  (conj path field)
                  value])))


(rf/reg-event-db
 :form-input
 (fn [db [_ full-path value]]
   (assoc-in db full-path value)))
