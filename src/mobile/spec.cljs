(ns mobile.spec
  (:require
   [clojure.string :as str]
   [clojure.spec.alpha :as s]))


(defn re-email? [string]
  (re-matches #"^(.+)@(.+)$" string))


(defn re-pin? [string]
  (re-matches #"^\d{4}$" string))


(s/def ::email
  (s/and string?
         (s/conformer
          (comp str/lower-case str/trim))
         re-email?))


(s/def ::pin
  (s/and string?
         (s/conformer
          (comp str/lower-case str/trim))
         re-pin?))


(s/def ::ne-string
  (s/and string? (complement str/blank?)))


(s/def ::q ::ne-string)


(s/def ::form-auth
  (s/keys :req-un [::email]))


(s/def ::form-pin
  (s/keys :req-un [::pin]))


(s/def ::form-search
  (s/keys :req-un [::q]))
