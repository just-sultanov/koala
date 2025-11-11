(ns koala.instrumentation
  (:require
   [clojure.tools.logging :as log])
  (:import
   (clojure.asm.commons
    GeneratorAdapter)
   (clojure.lang
    Compiler$C
    Compiler$AssignExpr
    Compiler$BodyExpr
    Compiler$CaseExpr
    Compiler$DefExpr
    Compiler$EmptyExpr
    Compiler$FnExpr
    Compiler$HostExpr
    Compiler$IfExpr
    Compiler$ImportExpr
    Compiler$InstanceOfExpr
    Compiler$InvokeExpr
    Compiler$KeywordInvokeExpr
    Compiler$LetExpr
    Compiler$LetFnExpr
    Compiler$ListExpr
    Compiler$LiteralExpr
    Compiler$LocalBindingExpr
    Compiler$MapExpr
    Compiler$MetaExpr
    Compiler$MethodParamExpr
    Compiler$MonitorEnterExpr
    Compiler$MonitorExitExpr
    Compiler$ObjExpr
    Compiler$TheVarExpr
    Compiler$ThrowExpr
    Compiler$UntypedExpr
    Compiler$VarExpr)))

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
;; AssignExpr
;;;;

(defmethod handler :assign-expr/enter
  [stage ^Compiler$AssignExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :assign-expr/exit
  [stage ^Compiler$AssignExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; BodyExpr
;;;;

(defmethod handler :body-expr/enter
  [stage ^Compiler$BodyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :body-expr/exit
  [stage ^Compiler$BodyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; CaseExpr
;;;;

(defmethod handler :case-expr/enter
  [stage ^Compiler$CaseExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :case-expr/exit
  [stage ^Compiler$CaseExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; DefExpr
;;;;

(defmethod handler :def-expr/enter
  [stage ^Compiler$DefExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :def-expr/exit
  [stage ^Compiler$DefExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; EmptyExpr
;;;;

(defmethod handler :empty-expr/enter
  [stage ^Compiler$EmptyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :empty-expr/exit
  [stage ^Compiler$EmptyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; FnExpr
;;;;

(defmethod handler :fn-expr/enter
  [stage ^Compiler$FnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :fn-expr/exit
  [stage ^Compiler$FnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; HostExpr
;;;;

(defmethod handler :host-expr/enter
  [stage ^Compiler$HostExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :host-expr/exit
  [stage ^Compiler$HostExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; IfExpr
;;;;

(defmethod handler :if-expr/enter
  [stage ^Compiler$IfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :if-expr/exit
  [stage ^Compiler$IfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; ImportExpr
;;;;

(defmethod handler :import-expr/enter
  [stage ^Compiler$ImportExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :import-expr/exit
  [stage ^Compiler$ImportExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; InstanceOfExpr
;;;;

(defmethod handler :instance-of-expr/enter
  [stage ^Compiler$InstanceOfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :instance-of-expr/exit
  [stage ^Compiler$InstanceOfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; InvokeExpr
;;;;

(defmethod handler :invoke-expr/enter
  [stage ^Compiler$InvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :invoke-expr/exit
  [stage ^Compiler$InvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; KeywordInvokeExpr
;;;;

(defmethod handler :keyword-invoke-expr/enter
  [stage ^Compiler$KeywordInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :keyword-invoke-expr/exit
  [stage ^Compiler$KeywordInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; LetExpr
;;;;

(defmethod handler :let-expr/enter
  [stage ^Compiler$LetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :let-expr/exit
  [stage ^Compiler$LetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; LetFnExpr
;;;;

(defmethod handler :let-fn-expr/enter
  [stage ^Compiler$LetFnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :let-fn-expr/exit
  [stage ^Compiler$LetFnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; ListExpr
;;;;

(defmethod handler :list-expr/enter
  [stage ^Compiler$ListExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :list-expr/exit
  [stage ^Compiler$ListExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; LiteralExpr
;;;;

(defmethod handler :literal-expr/enter
  [stage ^Compiler$LiteralExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :literal-expr/exit
  [stage ^Compiler$LiteralExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; LocalBindingExpr
;;;;

(defmethod handler :local-binding-expr/enter
  [stage ^Compiler$LocalBindingExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :local-binding-expr/exit
  [stage ^Compiler$LocalBindingExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; MapExpr
;;;;

(defmethod handler :map-expr/enter
  [stage ^Compiler$MapExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :map-expr/exit
  [stage ^Compiler$MapExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; MetaExpr
;;;;

(defmethod handler :meta-expr/enter
  [stage ^Compiler$MetaExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :meta-expr/exit
  [stage ^Compiler$MetaExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; MethodParamExpr
;;;;

(defmethod handler :method-param-expr/enter
  [stage ^Compiler$MethodParamExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :method-param-expr/exit
  [stage ^Compiler$MethodParamExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; MonitorEnterExpr
;;;;

(defmethod handler :monitor-enter-expr/enter
  [stage ^Compiler$MonitorEnterExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :monitor-enter-expr/exit
  [stage ^Compiler$MonitorEnterExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; MonitorExitExpr
;;;;

(defmethod handler :monitor-exit-expr/enter
  [stage ^Compiler$MonitorExitExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :monitor-exit-expr/exit
  [stage ^Compiler$MonitorExitExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; TheVarExpr
;;;;

(defmethod handler :the-var-expr/enter
  [stage ^Compiler$TheVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :the-var-expr/exit
  [stage ^Compiler$TheVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; ThrowExpr
;;;;

(defmethod handler :throw-expr/enter
  [stage ^Compiler$ThrowExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :throw-expr/exit
  [stage ^Compiler$ThrowExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; UntypedExpr
;;;;

(defmethod handler :untyped-expr/enter
  [stage ^Compiler$UntypedExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :untyped-expr/exit
  [stage ^Compiler$UntypedExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; VarExpr
;;;;

(defmethod handler :var-expr/enter
  [stage ^Compiler$VarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :var-expr/exit
  [stage ^Compiler$VarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))
