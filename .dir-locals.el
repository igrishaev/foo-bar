((nil . ((cider-default-cljs-repl . custom)
         (cider-custom-cljs-repl-init-form . "(require '[clojure.java.io :as io]
         '[clojure.edn :as edn]
         '[cider.piggieback]
         '[krell.api :as krell]
         '[krell.repl])
(def config (edn/read-string (slurp (io/file \"build.edn\"))))
(krell/build config)
(apply cider.piggieback/cljs-repl (krell.repl/repl-env) (mapcat identity config))"))))
