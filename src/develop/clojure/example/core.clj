(ns example.core)

(defn square
  [x]
  (* x x))

(defn multiply
  [a b]
  (* a b))

(comment
  :let-expr
  :monitor-enter-expr
  :monitor-exit-expr
  (let [lock (Object.)]
    (try
      (monitor-enter lock)
      (finally
        (monitor-exit lock))))

  :the-var-expr
  (var multiply)

  :import-expr
  (import 'clojure.lang.Keyword)

  :throw-expr
  (try
    (throw (ex-info "boom!" {}))
    (catch Exception _))

  :var-expr
  :invoke-expr
  (multiply 2 3)
  (square 4)
  (square (multiply 2 3)))
