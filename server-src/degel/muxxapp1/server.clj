(ns degel.muxxapp1.server
  (:gen-class)
  (:require [shoreleave.middleware.rpc :as rpc]
            [degel.muxx.server :as muxx]
            [degel.cljutil.introspect] ;; Needed just include code for rpc from client
            [degel.cljutil.devutils :as dev]))


(defn app-properties
  "Descriptor of this web app, primarily for the sake of muxx."
  []
  (muxx/default-app-properties "muxxapp1"))


;; Standalone top-level. Either call this or wrap this app into a
;; larger muxx deployment.
(defn -main [& [port]]
  (muxx/run-servers :port port :apps [(app-properties)]))
