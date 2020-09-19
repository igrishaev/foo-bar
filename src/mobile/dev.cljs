(ns mobile.dev
  (:require
   [mobile.fetch :as fetch]
   [mobile.log :as log]))


#_
(-> (fetch/fetch "https://jsonplaceholder.typicode.com/users/1")

    (.then
     (fn [x]
       (log/debug x)))

    (.catch
     (fn [e]
       (log/debug e))))
