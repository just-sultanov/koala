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
    Compiler$BooleanExpr
    Compiler$CaseExpr
    Compiler$ConstantExpr
    Compiler$DefExpr
    Compiler$EmptyExpr
    Compiler$FieldExpr
    Compiler$FnExpr
    Compiler$HostExpr
    Compiler$IfExpr
    Compiler$ImportExpr
    Compiler$InstanceFieldExpr
    Compiler$InstanceOfExpr
    Compiler$InvokeExpr
    Compiler$KeywordExpr
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
    Compiler$NewExpr
    Compiler$NewInstanceExpr
    Compiler$NilExpr
    Compiler$NumberExpr
    Compiler$ObjExpr
    Compiler$QualifiedMethodExpr
    Compiler$RecurExpr
    Compiler$SetExpr
    Compiler$StaticInvokeExpr
    Compiler$TheVarExpr
    Compiler$ThrowExpr
    Compiler$TryExpr
    Compiler$UnresolvedVarExpr
    Compiler$UntypedExpr
    Compiler$VarExpr
    Compiler$VectorExpr)))

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
;; BooleanExpr
;;;;

(defmethod handler :boolean-expr/enter
  [stage ^Compiler$BooleanExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :boolean-expr/exit
  [stage ^Compiler$BooleanExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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
;; ConstantExpr
;;;;

(defmethod handler :constant-expr/enter
  [stage ^Compiler$ConstantExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :constant-expr/exit
  [stage ^Compiler$ConstantExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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
;; FieldExpr
;;;;

(defmethod handler :field-expr/enter
  [stage ^Compiler$FieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :field-expr/exit
  [stage ^Compiler$FieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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
;; InstanceFieldExpr
;;;;

(defmethod handler :instance-field-expr/enter
  [stage ^Compiler$InstanceFieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :instance-field-expr/exit
  [stage ^Compiler$InstanceFieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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
;; KeywordExpr
;;;;

(defmethod handler :keyword-expr/enter
  [stage ^Compiler$KeywordExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :keyword-expr/exit
  [stage ^Compiler$KeywordExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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
;; NewExpr
;;;;

(defmethod handler :new-expr/enter
  [stage ^Compiler$NewExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :new-expr/exit
  [stage ^Compiler$NewExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; NewInstanceExpr
;;;;

(defmethod handler :new-instance-expr/enter
  [stage ^Compiler$NewInstanceExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :new-instance-expr/exit
  [stage ^Compiler$NewInstanceExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; NilExpr
;;;;

(defmethod handler :nil-expr/enter
  [stage ^Compiler$NilExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :nil-expr/exit
  [stage ^Compiler$NilExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; NumberExpr
;;;;

(defmethod handler :number-expr/enter
  [stage ^Compiler$NumberExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :number-expr/exit
  [stage ^Compiler$NumberExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; ObjExpr
;;;;

(defmethod handler :obj-expr/enter
  [stage ^Compiler$ObjExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :obj-expr/exit
  [stage ^Compiler$ObjExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; QualifiedMethodExpr
;;;;

(defmethod handler :qualified-method-expr/enter
  [stage ^Compiler$QualifiedMethodExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :qualified-method-expr/exit
  [stage ^Compiler$QualifiedMethodExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; RecurExpr
;;;;

(defmethod handler :recur-expr/enter
  [stage ^Compiler$RecurExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :recur-expr/exit
  [stage ^Compiler$RecurExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; SetExpr
;;;;

(defmethod handler :set-expr/enter
  [stage ^Compiler$SetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :set-expr/exit
  [stage ^Compiler$SetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; StaticInvokeExpr
;;;;

(defmethod handler :static-invoke-expr/enter
  [stage ^Compiler$StaticInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :static-invoke-expr/exit
  [stage ^Compiler$StaticInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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
;; TryExpr
;;;;

(defmethod handler :try-expr/enter
  [stage ^Compiler$TryExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :try-expr/exit
  [stage ^Compiler$TryExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

;;;;
;; UnresolvedVarExpr
;;;;

(defmethod handler :unresolved-var-expr/enter
  [stage ^Compiler$UnresolvedVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :unresolved-var-expr/exit
  [stage ^Compiler$UnresolvedVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
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

;;;;
;; VectorExpr
;;;;

(defmethod handler :vector-expr/enter
  [stage ^Compiler$VectorExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))

(defmethod handler :vector-expr/exit
  [stage ^Compiler$VectorExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug stage (get-coords objx)))
