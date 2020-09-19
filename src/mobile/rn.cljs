(ns mobile.rn
  (:require
   ["react-native-gesture-handler"]
   ["react-native-safe-area-context"
    :refer [SafeAreaView SafeAreaProvider]]
   ["@react-navigation/native"
    :refer [NavigationContainer]]
   ["@react-navigation/stack"
    :refer [createStackNavigator]]

   [react-native :as rn]
   [reagent.core :as r]))


(def button
  (r/adapt-react-class rn/Button))

(def flat-list
  (r/adapt-react-class rn/FlatList))

(def image
  (r/adapt-react-class rn/Image))

(def image-background
  (r/adapt-react-class rn/ImageBackground))

(def keyboard-avoiding-view
  (r/adapt-react-class rn/KeyboardAvoidingView))

(def modal
  (r/adapt-react-class rn/Modal))

(def refresh-control
  (r/adapt-react-class rn/RefreshControl))

(def scroll-view
  (r/adapt-react-class rn/ScrollView))

(def section-list
  (r/adapt-react-class rn/SectionList))

(def status-bar
  (r/adapt-react-class rn/StatusBar))

(def text
  (r/adapt-react-class rn/Text))

(def text-input
  (r/adapt-react-class rn/TextInput))

(def touchable-highlight
  (r/adapt-react-class rn/TouchableHighlight))

(def touchable-without-feedback
  (r/adapt-react-class rn/TouchableWithoutFeedback))

(def view
  (r/adapt-react-class rn/View))

(def virtualized-list
  (r/adapt-react-class rn/VirtualizedList))

(def app-registry
  rn/AppRegistry)

(def navigation-container
  (r/adapt-react-class NavigationContainer))

(def safe-area-provider
  (r/adapt-react-class SafeAreaProvider))

(def safe-area-view
   (r/adapt-react-class SafeAreaView))

(defn create-stack-navigator []
  (let [^js stack (createStackNavigator)]
    [(r/adapt-react-class (.-Navigator stack))
     (r/adapt-react-class (.-Screen stack))]))
