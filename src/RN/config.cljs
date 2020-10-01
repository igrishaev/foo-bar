(ns RN.config)

(def path-dimensions [:rn :dimensions])

(def path-dimensions-window-width
  (into path-dimensions [:window :width]))

(def path-dimensions-window-height
  (into path-dimensions [:window :height]))

(def path-dimensions-screen-width
  (into path-dimensions [:screen :width]))

(def path-dimensions-screen-height
  (into path-dimensions [:screen :height]))
