(ns degel.mummy.server
  (:gen-class)
  (:require [shoreleave.middleware.rpc :as rpc]
            [degel.muxx.server :as muxx]
            [degel.cljutil.introspect] ;; Needed just include code for rpc from client
            [degel.cljutil.devutils :as dev]))


(defn app-properties
  "Descriptor of this web app, primarily for the sake of muxx."
  []
  {:name          "mummy"
   :base-page     "/mummy.html"
   :production-js "js/mummy.js"
   :dev-js        "js/mummy-dev.js"})


;; Standalone top-level. Either call this or wrap this app into a
;; larger muxx deployment.
(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 3000))]
    (muxx/run-servers :port port
                      :apps [(app-properties)])))
