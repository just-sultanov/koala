(ns example.core)

(defn square
  [x]
  (* x x))

(defn multiply
  [a b]
  (* a b))

(comment
  (multiply 2 3)
  (square 4)
  (square (multiply 2 3))
  []
  '()
  #{}
  {}
  (identity [])

  :monitor-enter-expr
  :monitor-exit-expr
  (let [lock (Object.)]
    (try
      (monitor-enter lock)
      (finally
        (monitor-exit lock))))

  :throw-expr
  (try
    (throw (ex-info "boom!" {}))
    (catch Exception _)))
