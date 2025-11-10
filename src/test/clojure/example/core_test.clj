(ns example.core-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [example.core :as sut]))

(deftest square-test
  (testing "dummy test"
    (is (= 4 (sut/square 2)))))
