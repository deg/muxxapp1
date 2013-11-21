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


(defproject degel/mummy "0.1.0"
  :description "A simple muxx project which I will use mostly to test new ideas"
  :url "https://github.com/deg/mummy"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2014g"]

                 ;; Degel's Clojure utility library
                 [degel-clojure-utils "0.1.15"]

                 ;; Degel's website multiplexer
                 [muxx "0.1.2"]

                 ;; Needed, I think, in order to build a runnable uberjar with compojure
                 [ring/ring-jetty-adapter "1.1.8"]

                 ;; Routing library for Ring web application library
                 [compojure "1.1.5" :exclusions [ring/ring-core org.clojure/tools.macro]]

                 ;; DOM manipulation library for ClojureScript
                 [domina "1.0.2"]

                 ;; HTML templating.
                 ;; [TODO] {FogBugz:139} Compare with Hiccup. Maybe choose just one
                 [enlive "1.1.1"]

                 ;; HTML generation for Clojurescript (Ported from Clojure Hiccup)
                 [hiccups "0.2.0"]

                 ;; Ring/Compojure RPC
                 [shoreleave/shoreleave-remote-ring "0.3.0"]
                 [shoreleave/shoreleave-remote "0.3.0"]

                 ;; Clojure/ClojureScript validation
                 [com.cemerick/valip "0.3.2"]]

  :plugins [[lein-cljsbuild "0.3.3"]
            [lein-ring "0.8.3" :exclusions [org.clojure/clojure]]
            [com.cemerick/austin "0.1.3"]]

  :min-lein-version "2.0.0"

  :main degel.mummy.server ;; For standalone deployment

  :source-paths ["server-src"]
  :test-paths ["test"]

  :cljsbuild {:crossovers [valip.core
                           valip.predicates
                           degel.cljutil.utils]
              ;; NOTE {FogBugz:134}
              ;; Can't do "lein cljsbuild auto" of both builds together. Instead, need
              ;; to say "lein cljsbuild once" or "lein cljsbuild auto dev" or
              ;; "lein cljsbuild auto production".
              ;; This is because of a problem with using :libs[""]. See
              ;; https://github.com/emezeske/lein-cljsbuild/issues/219
              :builds {:dev
                       {:source-paths ["client-src"]
                        :compiler {:libs [""] ;; See https://github.com/cemerick/pprng/
                                   :output-to "resources/public/js/mummy-dev.js"
                                   :optimizations :simple ;; or :whitespace
                                   :pretty-print true
                                   ;; :source-map "receipts-dev.js.map"
                                   }
                        :jar false},
                       :production
                       {:source-paths ["client-src"]
                        :compiler {:libs [""] ;; See https://github.com/cemerick/pprng/
                                   :output-to "resources/public/js/mummy.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   ;; :source-map "receipts.js.map"
                                   }
                        :jar true}}
              :test-commands {"unit-tests" ["runners/phantomjs.js" "resources/public/js/receipts-dev.js"]}})
