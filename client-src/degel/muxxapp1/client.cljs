(ns degel.muxxapp1.client
  (:require-macros [hiccups.core :refer [html]])
  (:require [clojure.string :as str]
            [domina :as dom :refer [log]]
            [domina.events :as events]
            [hiccups.runtime] ;; Needed by hiccups.core macros
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [clojure.browser.repl]))

(defn ^:export init []
  (remote-callback
      :project-versions [[["degel" "muxxapp1"]
                          ["degel" "muxx"]
                          ["degel" "degel-clojure-utils"]
                          ["org.clojure" "clojurescript"]]]
    (fn [v]
      (dom/set-html! (dom/by-id "page")
        (html [:div#versions (str/join
                              "<br>"
                              (map (fn [[group-id artifact version]]
                                     (str (if (= group-id artifact)
                                            artifact
                                            (str group-id "/" artifact))
                                          ": " version)) v))])))))
