(ns RN.nav
  (:require
   ["react-native-gesture-handler"]

   ["react-native-safe-area-context"
    :refer [SafeAreaView SafeAreaProvider]]

   ["@react-navigation/native"
    :refer [NavigationContainer useNavigation]]

   ["@react-navigation/stack"
    :refer [createStackNavigator]]

   [reagent.core :as r]))


(def navigation-container
  (r/adapt-react-class NavigationContainer))


(def safe-area-provider
  (r/adapt-react-class SafeAreaProvider))


(def safe-area-view
  (r/adapt-react-class SafeAreaView))


(defn user-navigation []
  (useNavigation))


(defn create-stack-navigator []
  (let [^js stack (createStackNavigator)]
    {:stack stack
     :stack-navigator (r/adapt-react-class (.-Navigator stack))
     :stack-screen (r/adapt-react-class (.-Screen stack))}))
