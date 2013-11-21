(ns degel.mummy.client
  (:require-macros [hiccups.core :refer [html]])
  (:require [clojure.string :as str]
            [domina :as dom :refer [log]]
            [domina.events :as events]
            [hiccups.runtime] ;; Needed by hiccups.core macros
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [clojure.browser.repl]))

(defn ^:export init []
  (remote-callback
      :project-versions [[["mummy" "mummy"]
                          ["muxx" "muxx"]
                          ["degel-clojure-utils" "degel-clojure-utils"]
                          ["org.clojure" "clojurescript"]]]
    (fn [v]
      (dom/set-html! (dom/by-id "page")
        (html [:div#versions (str/join
                              "<br>"
                              (map (fn [[_ artifact version]] (str artifact ": " version)) v))])))))
