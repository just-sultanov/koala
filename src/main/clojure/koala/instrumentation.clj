(ns koala.instrumentation
  (:require
   [clojure.tools.logging :as log])
  (:import
   (clojure.asm.commons
    GeneratorAdapter)
   (clojure.lang
    Compiler$C
    Compiler$MonitorEnterExpr
    Compiler$MonitorExitExpr
    Compiler$ObjExpr
    Compiler$ThrowExpr
    Compiler$UntypedExpr)))

;;;;
;; Instrumentation entrypoint
;;;;

(defmulti handler
  (fn [stage _expr _context _objx _gen]
    stage))

;;;;
;; Helpers
;;;;

(defn get-coords
  [^Compiler$ObjExpr objx]
  {:line (.line objx), :column (.column objx)})

;;;;
;; UntypedExpr
;;;;

(defmethod handler :untyped-expr/enter
  [stage ^Compiler$UntypedExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :untyped-expr/exit
  [stage ^Compiler$UntypedExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :monitor-enter-expr/enter
  [stage ^Compiler$MonitorEnterExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :monitor-enter-expr/exit
  [stage ^Compiler$MonitorEnterExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :monitor-exit-expr/enter
  [stage ^Compiler$MonitorExitExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :monitor-exit-expr/exit
  [stage ^Compiler$MonitorExitExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :throw-expr/enter
  [stage ^Compiler$ThrowExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :throw-expr/exit
  [stage ^Compiler$ThrowExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))
