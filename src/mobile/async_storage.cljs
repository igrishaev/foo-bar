(ns mobile.async-storage
  (:require
   [cljs.reader :as reader]

   ["@react-native-community/async-storage"
    :refer [default]
    :rename {default AsyncStorage}]))


(def serialize pr-str)


(def deserialize reader/read-string)


(defn deserialize-safe [x]
  (try
    (deserialize x)
    (catch :default e
      x)))


(defn get-item [key]
  (.. AsyncStorage
      (getItem key)
      (then deserialize-safe)))


(defn set-item [key value]
  (.. AsyncStorage
      (setItem key (serialize value))))


(defn remove-item [key]
  (.. AsyncStorage
      (removeItem key)))


(defn multi-get [keys]
  (.. AsyncStorage
      (multiGet (clj->js keys))
      (then
       (fn [key-vals]
         (into {} (for [key-val (array-seq key-vals)]
                    [(-> key-val first)
                     (-> key-val second deserialize-safe)]))))))


(defn multi-set [key->val]
  (.. AsyncStorage
      (multiSet
       (clj->js
        (map (fn [pair]
               (update pair 1 serialize))
             key->val)))))


(defn multi-remove [keys]
  (.. AsyncStorage
      (multiRemove (clj->js keys))))


(defn get-all-keys []
  (.. AsyncStorage
      getAllKeys
      (then js->clj)))


(defn clear []
  (.. AsyncStorage
      clear))
