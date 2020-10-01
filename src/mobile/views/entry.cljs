(ns mobile.views.entry
  "
  A single entry/message screen.
  "
  (:require
   [RN.core :as rn]
   [RN.util :as util]

   [RN.webview :refer [web-view]]

   [mobile.style :as style]
   [mobile.config :as config]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn screen [i message]

  [rn/view

   [rn/text {:style {:fontSize 18}}

    (str (str i) (str message))
    ;; "AAAAAAA"
    ]]

  #_
  (let [{entry :message/entry} message
        {entry-title :entry/title
         entry-link :entry/link} entry]

    [rn/view
     [rn/touchable-opacity
      {:on-press
       (fn [])}

      [rn/text {:style {:fontSize 18}}
       entry-title]]]))


(def Screen (r/reactify-component screen))
