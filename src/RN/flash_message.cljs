(ns RN.flash-message
  "
  Wrapper for flash messages.
  https://github.com/lucasferreira/react-native-flash-message
  "
  (:require
   ["react-native-flash-message"
    :refer [default
            showMessage
            hideMessage]
    :rename {default FlashMessage}]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(def flash-message
  (r/adapt-react-class FlashMessage))


(defn show-message
  [{:keys [message
           description
           type]
    :as object}]

  (showMessage (clj->js object)))


(defn hideMessage []
  (hideMessage))


(rf/reg-fx
 :rn/show-message
 (fn [message]
   (show-message message)))


(rf/reg-fx
 :rn/hide-message
 (fn [message]
   (hide-message)))
