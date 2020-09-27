(ns mobile.pages.search-entry
  (:require
   [RN.core :as rn]
   [RN.form :as form]
   [RN.log :as log]
   [RN.util :as util]
   [RN.webview :refer [web-view]]

   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(defn page [{:keys [route
                    navigation]}]

  (let [;; idx-feed (.. route -params -idx_feed)
        ;; idx-entry (.. route -params -idx_entry)

        idx-feed 0
        idx-entry 0

        path (conj const/path-remote-search idx-feed :entries idx-entry)

        _ (log/debug path)

        entry @(rf/subscribe [:get-in path])

        ;; entry {:entry/link "http://lenta.ru"
        ;;        :entry/content "test foo bar <b>aaa</b> aaaaa"}

        ]

    (log/debug entry )

    [web-view
     {:style {:padding 30}
      :dataDetectorTypes #js ["all"]
      :containerStyle {:flex 1}
      :originWhitelist #js ["*"]

      ;; :overScrollMode "content"
      ;; :automaticallyAdjustContentInsets false
      ;; :scrollEnabled false
      ;; :showsVerticalScrollIndicator false

      :source
      #js {:baseUrl (-> entry :entry/link)
           :html
           (str "<html><head>

<style>
    body { font-size: 150%; word-wrap: break-word; overflow-wrap: break-word; }
    img { width: 100% }
</style>


<meta name='viewport' content='width=device-width, initial-scale=1.0'></head><body>"

                (-> entry :entry/content)


                "</body></html>"

                )

           #_
           "<html><head><meta name='viewport' content='width=device-width, initial-scale=1.0'></head><body><p>This is a static HTML source!</p></body></html>"

           #_
           (-> entry :entry/content)}

      }]


    #_
    [rn/scroll-view
     {:contentContainerStyle
      {:padding 30
       :flex 1
       :flexDirection "column"
       :alignItems "stretch"}
      }

     #_
     {:style {:padding 30
              :flex 1
              :flexDirection "column"
              :alignItems "stretch"}}

     [rn/text {:style {:fontSize 24}}
      (-> entry :entry/title)]

     #_
     [web-view {:style {:padding 30}
                :dataDetectorTypes #js ["all"]
                :containerStyle {:flex 1}
                :originWhitelist #js ["*"]

                ;; :overScrollMode "content"
                ;; :automaticallyAdjustContentInsets false
                ;; :scrollEnabled false
                ;; :showsVerticalScrollIndicator false

                :source #js {:baseUrl (-> entry :entry/link)
                             :html
                             (str "<html><head>

<style>
    body { font-size: 150%; word-wrap: break-word; overflow-wrap: break-word; }
    img { width: 100% }
</style>


<meta name='viewport' content='width=device-width, initial-scale=1.0'></head><body>"

                                  (-> entry :entry/content)


                                  "</body></html>"

                                  )

                             #_
                             "<html><head><meta name='viewport' content='width=device-width, initial-scale=1.0'></head><body><p>This is a static HTML source!</p></body></html>"

                             #_
                             (-> entry :entry/content)}

                }]

     #_
     [rn/text {:style {:fontSize 18}}
      ]]))


(def Page (r/reactify-component page))
