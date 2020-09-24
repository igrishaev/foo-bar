(ns mobile.dev
  (:require
   [RN.fetch :as fetch]
   [RN.log :as log]

   [mobile.rpc :as rpc]

   ))


#_
(.. (rpc/call {:method "auth/request-pin"
               :params {:email "ivan@grishaev.me"}})
    (then println)
    (catch println)
    )



#_
(.. (fetch/fetch "http://192.168.31.102:8088/rpc"
                 {:method "POST"
                  :data {:jsonrpc "2.0"
                         :method "auth/request-pin"
                         :params {:email "ivan@grishaev.me"}
                         :id 99}})
    (then println)
    )


#_
(.. (fetch/fetch "http://192.168.31.102:8088/rpc"
           {:method "POST"
            :data {:jsonrpc "2.0"
                   :method "auth/obtain-token"
                   :params {:pin "1075"}
                   :id 99}})
    (then println)
    )


#_
{:secret b87fb76e-24ff-401c-89cc-43bfab31ec0f}


#_
(-> (fetch/fetch "https://jsonplaceholder.typicode.com/users/1")

    (.then
     (fn [x]
       (log/debug x)))

    (.catch
     (fn [e]
       (log/debug e))))


#_
(.. (fetch/fetch "http://192.168.31.102:8088/rpc"
           {:method "POST"
            :headers
            {:authorization
             "Bearer b87fb76e-24ff-401c-89cc-43bfab31ec0f"}
            :data {:jsonrpc "2.0"
                   :method "auth/user-info"
                   :id 99}})
    (then println)
    )
