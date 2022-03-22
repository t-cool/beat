(ns beat.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as r] ; 追加
   [beat.subs :as subs]
   ))

(def click-count (r/atom 0))
(defn counting-component []
  [:div
   [:h3  "お馴染みのカウント"]
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! click-count inc)}]])

(defn main-panel []
  (let[] 
   ;[name (re-frame/subscribe [::subs/name])]
   (counting-component)))


