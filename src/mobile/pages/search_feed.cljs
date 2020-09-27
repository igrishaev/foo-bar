(ns mobile.pages.search-feed
  (:require

   ["react-native-swiper"
    :refer [default]
    :rename {default Swiper}]

   [RN.core :as rn]
   [RN.form :as form]
   [RN.log :as log]
   [RN.util :as util]
   [RN.nav :as nav]

   mobile.pages.auth
   mobile.pages.pin
   mobile.pages.search-entry

   [mobile.style :as style]
   [mobile.const :as const]

   [re-frame.core :as rf]
   [reagent.core :as r]))


(let [{:keys [tab
              tab-navigator
              tab-screen]}
      (nav/create-material-top-tab-navigator)]

  (def tab tab)
  (def tab-navigator tab-navigator)
  (def tab-screen tab-screen))


(def swiper
  (r/adapt-react-class Swiper))


(defn ccc []
  [rn/view])


(def Ccc (r/reactify-component ccc))


(defn tabbed-view []
  [tab-navigator

   {:lazy true
    :lazyPreloadDistance 0
    :tabBarComponent Ccc
    :tabBarPosition "bottom"
    :tabBarOptions #js {:scrollEnabled true}

    }

   (for [n (range 1 60)]
     ^{:key n}
     [tab-screen
      {:name (str "page-" n)
       :component mobile.pages.auth/Page
       :options #js {:title (str "Page #" n)}}
      ]
     )



])



(defn page [{:keys [route
                    navigation]}]

  [swiper

   {:horizontal true
    :loop false
    :showsButtons false
    :autoplay false
    ;; :onIndexChanged

    :loadMinimal true
    :loadMinimalSize 1
    :bounces true

    }

   (for [n (range 1 30)]
     ^{:key n}

     [mobile.pages.search-entry/page]

     #_
     [rn/view
      [rn/text
       "AAAAA"]
      ]

     #_
     [mobile.pages.auth])

   ]


  #_
  (let [idx-feed (.. route -params -search_index)
        path (conj const/path-remote-search idx-feed)

        _ (log/debug (str "mobile.pages.search-feed " idx-feed))

        ;; _ (log/debug route)
        ;; _ (log/debug path)

        ;; item @(rf/subscribe [:get-in path])

        ;; {:keys [feed entries]} item

        ;; idx-entries (util/enumerate entries)

        ;; {:keys [stack-navigator stack-screen]}
        ;; (nav/create-stack-navigator)
        ]

    [tabbed-view]


    #_
    [nav/navigation-container

     ]

    #_

    [rn/view {:style {:padding 30
                      :flex 1
                      :flexDirection "column"
                      :alignItems "stretch"}}

     (for [[idx-entry entry] idx-entries]
       ^{:key (-> entry :db/id)}

       [rn/view {:style {:padding 30}}

        [rn/touchable-without-feedback
         {:on-press (fn []
                      (.navigate navigation "search-entry"
                                 #js {:idx_feed idx-feed
                                      :idx_entry idx-entry}))}

         [rn/text {:style {:fontSize 24}}
          (-> entry :entry/title)]]])]))


(def Page (r/reactify-component page))
