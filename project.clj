;;; Copyright (c) 2012-2013 David Goldfarb. All rights reserved.
;;; Contact info: deg@degel.com
;;;
;;; The use and distribution terms for this software are covered by the Eclipse
;;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can
;;; be found in the file epl-v10.html at the root of this distribution.
;;; By using this software in any fashion, you are agreeing to be bound by the
;;; terms of this license.
;;;
;;; You must not remove this notice, or any other, from this software.


(defproject degel/muxxapp1 "0.1.4"
  :description "A simple muxx project which I will use mostly to test new ideas"
  :url "https://github.com/deg/muxxapp1"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2069"]

                 ;; Degel's Clojure utility library
                 [degel/degel-clojure-utils "0.1.21"]

                 ;; Degel's website multiplexer
                 [degel/muxx "0.1.7"]

                 ;; Needed, I think, in order to build a runnable uberjar with compojure
                 [ring/ring-jetty-adapter "1.2.1"]

                 ;; Routing library for Ring web application library
                 [compojure "1.1.6" :exclusions [org.clojure/tools.macro]]

                 ;; DOM manipulation library for ClojureScript
                 [domina "1.0.2"]

                 ;; HTML templating.
                 ;; [TODO] {FogBugz:139} Compare with Hiccup. Maybe choose just one
                 [enlive "1.1.1"]

                 ;; HTML generation for Clojurescript (Ported from Clojure Hiccup)
                 [hiccups "0.3.0"]

                 ;; Ring/Compojure RPC
                 [shoreleave/shoreleave-remote-ring "0.3.0"]
                 [shoreleave/shoreleave-remote "0.3.0"]

                 ;; Clojure/ClojureScript validation
                 [com.cemerick/valip "0.3.2"]]

  :hooks [leiningen.cljsbuild]

  :plugins [[lein-cljsbuild "1.0.1"]
            [lein-ring "0.8.3" :exclusions [org.clojure/clojure]]
            [com.cemerick/austin "0.1.3"]

            ;; Testing from ClojureScript
            [com.cemerick/clojurescript.test "0.2.2"]]

  :min-lein-version "2.0.0"
  :pedantic? :abort

  :main degel.muxxapp1.server ;; For standalone deployment

  :source-paths ["server-src"]
  :test-paths ["test"]

  :cljsbuild {:crossovers [valip.core
                           valip.predicates
                           degel.cljutil.utils]
              :builds {:dev
                       {:source-paths ["client-src"]
                        :compiler {:libs [""] ;; See https://github.com/cemerick/pprng/
                                   ;; :source-map "receipts-dev.js.map"
                                   :output-to "resources/public/js/muxxapp1-dev.js"
                                   :optimizations :simple ;; or :whitespace
                                   :pretty-print true}},
                       :production
                       {:source-paths ["client-src"]
                        :compiler {:libs [""] ;; See https://github.com/cemerick/pprng/
                                   :output-to "resources/public/js/muxxapp1.js"
                                   ;; :source-map "receipts.js.map"
                                   :optimizations :advanced
                                   :pretty-print false}
                        :jar true}}
              :test-commands {"unit-tests" ["runners/phantomjs.js" "resources/public/js/receipts-dev.js"]}})
