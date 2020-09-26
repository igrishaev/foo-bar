(ns RN.webview
  "
  https://github.com/react-native-community/react-native-webview/
  "
  (:require
   ["react-native-webview" :refer [WebView]]

   [reagent.core :as r]))


(def web-view
  (r/adapt-react-class WebView))
