(ns mobile.pages.search-feed
  (:require
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


(defn page [{:keys [route
                    navigation]}]

  (let [idx-feed (.. route -params -search_index)
        path (conj const/path-remote-search idx-feed)

        _ (log/debug (str "mobile.pages.search-feed " idx-feed))

        ;; _ (log/debug route)
        ;; _ (log/debug path)

        item @(rf/subscribe [:get-in path])

        {:keys [feed entries]} item

        idx-entries (util/enumerate entries)

        {:keys [tab-navigator
                tab-screen]}
        (nav/create-material-top-tab-navigator)

        ;; {:keys [stack-navigator stack-screen]}
        ;; (nav/create-stack-navigator)
        ]

    #_
    [stack-navigator
     {:screenOptions
      #js {:headerShown false
           :gesturesEnabled true
           :gestureDirection "horizontal"
           :cardOverlayEnabled true
           }}

     [stack-screen
      {:name "aa1"
       :component mobile.pages.auth/Page}]

     [stack-screen
      {:name "bb2"
       :component mobile.pages.pin/Page}]
     ]

    [tab-navigator

     {:lazy true
      :lazyPreloadDistance 1
      :tabBarComponent nil
      :tabBarPosition "top"
      :tabBarOptions #js {:scrollEnabled true}

      }

     [tab-screen
      {:name "aaa"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}
       }
      ]

     [tab-screen
      {:name "bbb"
       :component mobile.pages.pin/Page
       :options #js {:title "ASfsdf asDFs dfs"}}
      ]

     [tab-screen
      {:name "aaa1"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa2"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa3"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa4"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa5"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa6"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa7"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa8"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa9"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa10"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     [tab-screen
      {:name "aaa11"
       :component mobile.pages.search-entry/Page
       :options #js {:title "ASfsdf asDFs dfs"}}]

     ]

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
