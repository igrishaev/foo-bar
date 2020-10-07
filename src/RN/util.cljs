(ns RN.util
  (:require
   [goog.string.format]
   [goog.string :as gstring]))


(def format gstring/format)


(def enumerate
  (partial map-indexed vector))


(defn ->clj [x]
  (js->clj x :keywordize-keys true))


(defn ->json [x]
  (some-> x clj->js js/JSON.stringify))
