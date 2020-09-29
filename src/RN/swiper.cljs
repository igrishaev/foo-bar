(ns RN.swiper
  "
  https://github.com/leecade/react-native-swiper
  "
  (:require
   ["react-native-swiper"
    :refer [default]
    :rename {default Swiper}]))


(def swiper
  (r/adapt-react-class Swiper))


;;
;; Usage
;;


#_
[swiper

 {:horizontal true
  :loop false
  :showsButtons false
  :showsPagination false
  :autoplay false
  :onIndexChanged (fn [index])
  :loadMinimal true
  :loadMinimalSize 1
  :bounces true}

 (for [n (range 1 30)]
   ^{:key n}
   [some/view])]
