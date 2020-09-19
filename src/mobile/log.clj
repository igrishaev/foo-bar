(ns mobile.log)


(defmacro log [level message]
  `(let [logger# (-> ::_ namespace goog.log/getLogger)]
     (goog.log/log logger# ~level ~message)))


(defmacro info [message]
  `(log "info" ~message))
