(ns awesome-project.core
  (:require
   ["react-native-gesture-handler"]

   ["@react-navigation/native" :refer [NavigationContainer]]
   ["@react-navigation/stack" :refer [createStackNavigator]]

   [reagent.core :as r]
   [reagent.react-native :as rn]
   ))


;; import 'react-native-gesture-handler';
;; import * as React from 'react';
;; import { NavigationContainer } from '@react-navigation/native';

;; export default function App() {
;;   return (
;;     <NavigationContainer>{/* Rest of your app code */}</NavigationContainer>
;;   );
;; }

(js/console.log NavigationContainer)


(def navigation-container
  (r/adapt-react-class NavigationContainer))


(def Stack (createStackNavigator))

;; function App() {
;;   return (
;;     <NavigationContainer>
;;       <Stack.Navigator>
;;         <Stack.Screen name="Home" component={HomeScreen} />
;;       </Stack.Navigator>
;;     </NavigationContainer>
;;   );
;; }


(defn home-page []
  [rn/view {:style {:flex 1 :align-items "center" :justify-content "center"}}
   [some-text]])

(defn some-text []
  [rn/text {:style {:font-size 50}} "aaa"])

(defn hello []
  [home-page]
  #_
  [navigation-container
   [:> Stack/Navigator
    [:> Stack/Screen {:name "test" :component home-page}]]])

(defn ^:export -main [& args]
  (r/as-element [hello]))
