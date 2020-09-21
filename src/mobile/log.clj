(ns mobile.log)


(defmacro log [level message]
  `(goog.log/log
    (-> '_ namespace goog.log/getLogger)
    ~level
    ~message))


(defmacro info [message]
  `(log goog.debug.Logger.Level.INFO ~message))


(defmacro warn [message]
  `(log goog.debug.Logger.Level.WARNING ~message))


(defmacro error [message]
  `(log goog.debug.Logger.Level.SEVERE ~message))
