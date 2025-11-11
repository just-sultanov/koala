(ns example.core)

(defn square
  [x]
  (* x x))

(defn multiply
  [a b]
  (* a b))

(comment
  :assign-expr
  :body-expr
  (let [object (java.awt.Point. 1 2)]
    (set! (. object  -y) 8)
    (bean object))

  :case-expr
  (case :foo
    :foo :foo)

  :empty-expr
  '()
  []
  #{}
  {}

  :if-expr
  (or false true)

  :keyword-invoke-expr
  (:foo {:foo 42})

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

  :instance-of-expr
  (instance? String "foo")

  :throw-expr
  (try
    (throw (ex-info "boom!" {}))
    (catch Exception _))

  :var-expr
  :invoke-expr
  (multiply 2 3)
  (square 4)
  (square (multiply 2 3)))
