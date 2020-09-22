(ns mobile.style
  )


(def color-black
  "#000000")


(def font
  {:fontFamily "Helvetica Neue"
   :fontSize 16
   :color color-black})


(def container
  {
   ;; :marginTop 100
   :padding 10

   :flex 1
   :flexDirection "column"

   :justifyContent "center"
   :alignItems "center"

   :alignContent "center"


   })


(def input-margins
  {:paddingTop 5
   :paddingBottom 5})


(def input

  {:height 30
   :borderBottomWidth 2}

  #_
  [font
   input-margins
   {:padding 10
    :textAlign "center"}

   #_
   {:marginLeft 50
    :marginRight 50}
   #_
   {:height 30
    :borderBottomWidth 2}])
