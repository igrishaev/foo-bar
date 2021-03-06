(defproject reader "0.1.0-SNAPSHOT"

  :plugins [[lein-cljsbuild "1.1.8"]]

  :cljsbuild
  {:builds [{:id "aaa"
             :source-paths ["src"]
             :compiler {:main "mobile.core"

                        :output-dir "target/ui"
                        :output-to "target/ui.js"

                        :static-fns    true
                        :optimize-constants true


                        ;; :target :bundle
                        ;; :target :nodejs

                        :optimizations :none
                        ;; :optimizations :whitespace
                        ;; :optimizations :simple

                        ;; :language-out :es6
                        ;; :optimizations :advanced

                        :pseudo-names true
                        :pretty-print true

                        :infer-externs true

                        ;; :externs ["react/react.ext.js"
                        ;;           "react/react.native.ext.js"]

                        ;; :externs ["react/react.js"]

                        ;; :static-fns    true
                        ;; :optimize-constants true

                        ;; :install-deps true

                        ;; :npm-deps {:react "15.6.1"
                        ;;            :react-dom "15.6.1"
                        ;;            :create-react-class "15.6.0"}

                        }}]}

  ;; :jvm-opts ["--add-modules" "java.xml.bind"]

  :main reader.main

  :dependencies
  [[org.clojure/clojure "1.10.1"]

   [react-native-externs "0.2.0"]

   [cider/piggieback "0.5.1"]

   [com.google.guava/guava "29.0-jre"]
   [org.clojure/clojurescript "1.10.758"]

   [re-frame "1.1.1"]

   [reagent "1.0.0-alpha2"]]

  :profiles
  {:dev {
         :source-paths ["test"
                        "profiles/dev/src"]

         :resource-paths ["resources"
                          "profiles/dev/resources"]

         :repl-options {:init-ns reader.main}
         :global-vars {*warn-on-reflection* true
                       *assert* true}}})
