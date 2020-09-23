(ns mobile.spec
  (:require
   [clojure.string :as str]
   [clojure.spec.alpha :as s]))


(defn re-email? [string]
  (re-matches #"(.+)@(.+)" string))


(s/def ::email
  (s/and string?
         (s/conformer
          (comp str/lower-case str/trim))
         re-email?))


(s/def ::form-auth
  (s/keys :req-un [::email]))
