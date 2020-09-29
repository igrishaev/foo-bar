(ns RN.nav
  (:require
   ["react-native-gesture-handler"]

   ["react-native-safe-area-context"
    :refer [SafeAreaView SafeAreaProvider]]

   ["@react-navigation/native"
    :refer [NavigationContainer]]

   ["@react-navigation/material-top-tabs"
    :refer [createMaterialTopTabNavigator]]

   ["@react-navigation/stack"
    :refer [createStackNavigator]]

   ["@react-navigation/bottom-tabs"
    :refer [createBottomTabNavigator]]

   [reagent.core :as r]))


(def navigation-container
  (r/adapt-react-class NavigationContainer))


(def safe-area-provider
  (r/adapt-react-class SafeAreaProvider))


(def safe-area-view
  (r/adapt-react-class SafeAreaView))


(defn create-stack-navigator []
  (let [^js stack (createStackNavigator)]
    {:stack stack
     :stack-navigator (r/adapt-react-class (.-Navigator stack))
     :stack-screen (r/adapt-react-class (.-Screen stack))}))


(defn create-material-top-tab-navigator []
  (let [^js tab (createMaterialTopTabNavigator)]
    {:tab tab
     :tab-navigator (r/adapt-react-class (.-Navigator tab))
     :tab-screen (r/adapt-react-class (.-Screen tab))}))


(defn create-bottom-tab-navigator []
  (let [^js tab (createBottomTabNavigator)]
    {:tab tab
     :tab-navigator (r/adapt-react-class (.-Navigator tab))
     :tab-screen (r/adapt-react-class (.-Screen tab))}))
