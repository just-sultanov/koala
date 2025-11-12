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
    Compiler$StringExpr
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
  (fn [kind _method _expr _context _objx _gen]
    kind))

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
  [kind method ^Compiler$AssignExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :assign-expr/exit
  [kind method ^Compiler$AssignExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; BodyExpr
;;;;

(defmethod handler :body-expr/enter
  [kind method ^Compiler$BodyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :body-expr/exit
  [kind method ^Compiler$BodyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; BooleanExpr
;;;;

(defmethod handler :boolean-expr/enter
  [kind method ^Compiler$BooleanExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :boolean-expr/exit
  [kind method ^Compiler$BooleanExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; CaseExpr
;;;;

(defmethod handler :case-expr/enter
  [kind method ^Compiler$CaseExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :case-expr/exit
  [kind method ^Compiler$CaseExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; ConstantExpr
;;;;

(defmethod handler :constant-expr/enter
  [kind method ^Compiler$ConstantExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :constant-expr/exit
  [kind method ^Compiler$ConstantExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; DefExpr
;;;;

(defmethod handler :def-expr/enter
  [kind method ^Compiler$DefExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :def-expr/exit
  [kind method ^Compiler$DefExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; EmptyExpr
;;;;

(defmethod handler :empty-expr/enter
  [kind method ^Compiler$EmptyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :empty-expr/exit
  [kind method ^Compiler$EmptyExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; FieldExpr
;;;;

(defmethod handler :field-expr/enter
  [kind method ^Compiler$FieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :field-expr/exit
  [kind method ^Compiler$FieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; FnExpr
;;;;

(defmethod handler :fn-expr/enter
  [kind method ^Compiler$FnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :fn-expr/exit
  [kind method ^Compiler$FnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; HostExpr
;;;;

(defmethod handler :host-expr/enter
  [kind method ^Compiler$HostExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :host-expr/exit
  [kind method ^Compiler$HostExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; IfExpr
;;;;

(defmethod handler :if-expr/enter
  [kind method ^Compiler$IfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :if-expr/exit
  [kind method ^Compiler$IfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; ImportExpr
;;;;

(defmethod handler :import-expr/enter
  [kind method ^Compiler$ImportExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :import-expr/exit
  [kind method ^Compiler$ImportExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; InstanceFieldExpr
;;;;

(defmethod handler :instance-field-expr/enter
  [kind method ^Compiler$InstanceFieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :instance-field-expr/exit
  [kind method ^Compiler$InstanceFieldExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; InstanceOfExpr
;;;;

(defmethod handler :instance-of-expr/enter
  [kind method ^Compiler$InstanceOfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :instance-of-expr/exit
  [kind method ^Compiler$InstanceOfExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; InvokeExpr
;;;;

(defmethod handler :invoke-expr/enter
  [kind method ^Compiler$InvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :invoke-expr/exit
  [kind method ^Compiler$InvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; KeywordExpr
;;;;

(defmethod handler :keyword-expr/enter
  [kind method ^Compiler$KeywordExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :keyword-expr/exit
  [kind method ^Compiler$KeywordExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; KeywordInvokeExpr
;;;;

(defmethod handler :keyword-invoke-expr/enter
  [kind method ^Compiler$KeywordInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :keyword-invoke-expr/exit
  [kind method ^Compiler$KeywordInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; LetExpr
;;;;

(defmethod handler :let-expr/enter
  [kind method ^Compiler$LetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :let-expr/exit
  [kind method ^Compiler$LetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; LetFnExpr
;;;;

(defmethod handler :let-fn-expr/enter
  [kind method ^Compiler$LetFnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :let-fn-expr/exit
  [kind method ^Compiler$LetFnExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; ListExpr
;;;;

(defmethod handler :list-expr/enter
  [kind method ^Compiler$ListExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :list-expr/exit
  [kind method ^Compiler$ListExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; LiteralExpr
;;;;

(defmethod handler :literal-expr/enter
  [kind method ^Compiler$LiteralExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :literal-expr/exit
  [kind method ^Compiler$LiteralExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; LocalBindingExpr
;;;;

(defmethod handler :local-binding-expr/enter
  [kind method ^Compiler$LocalBindingExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :local-binding-expr/exit
  [kind method ^Compiler$LocalBindingExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; MapExpr
;;;;

(defmethod handler :map-expr/enter
  [kind method ^Compiler$MapExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :map-expr/exit
  [kind method ^Compiler$MapExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; MetaExpr
;;;;

(defmethod handler :meta-expr/enter
  [kind method ^Compiler$MetaExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :meta-expr/exit
  [kind method ^Compiler$MetaExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; MethodParamExpr
;;;;

(defmethod handler :method-param-expr/enter
  [kind method ^Compiler$MethodParamExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :method-param-expr/exit
  [kind method ^Compiler$MethodParamExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; MonitorEnterExpr
;;;;

(defmethod handler :monitor-enter-expr/enter
  [kind method ^Compiler$MonitorEnterExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :monitor-enter-expr/exit
  [kind method ^Compiler$MonitorEnterExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; MonitorExitExpr
;;;;

(defmethod handler :monitor-exit-expr/enter
  [kind method ^Compiler$MonitorExitExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :monitor-exit-expr/exit
  [kind method ^Compiler$MonitorExitExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; NewExpr
;;;;

(defmethod handler :new-expr/enter
  [kind method ^Compiler$NewExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :new-expr/exit
  [kind method ^Compiler$NewExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; NewInstanceExpr
;;;;

(defmethod handler :new-instance-expr/enter
  [kind method ^Compiler$NewInstanceExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :new-instance-expr/exit
  [kind method ^Compiler$NewInstanceExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; NilExpr
;;;;

(defmethod handler :nil-expr/enter
  [kind method ^Compiler$NilExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :nil-expr/exit
  [kind method ^Compiler$NilExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; NumberExpr
;;;;

(defmethod handler :number-expr/enter
  [kind method ^Compiler$NumberExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :number-expr/exit
  [kind method ^Compiler$NumberExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; ObjExpr
;;;;

(defmethod handler :obj-expr/enter
  [kind method ^Compiler$ObjExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :obj-expr/exit
  [kind method ^Compiler$ObjExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; QualifiedMethodExpr
;;;;

(defmethod handler :qualified-method-expr/enter
  [kind method ^Compiler$QualifiedMethodExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :qualified-method-expr/exit
  [kind method ^Compiler$QualifiedMethodExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; RecurExpr
;;;;

(defmethod handler :recur-expr/enter
  [kind method ^Compiler$RecurExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :recur-expr/exit
  [kind method ^Compiler$RecurExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; SetExpr
;;;;

(defmethod handler :set-expr/enter
  [kind method ^Compiler$SetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :set-expr/exit
  [kind method ^Compiler$SetExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; StaticInvokeExpr
;;;;

(defmethod handler :static-invoke-expr/enter
  [kind method ^Compiler$StaticInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :static-invoke-expr/exit
  [kind method ^Compiler$StaticInvokeExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; StringExpr
;;;;

(defmethod handler :string-expr/enter
  [kind method ^Compiler$StringExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :string-expr/exit
  [kind method ^Compiler$StringExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; TheVarExpr
;;;;

(defmethod handler :the-var-expr/enter
  [kind method ^Compiler$TheVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :the-var-expr/exit
  [kind method ^Compiler$TheVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; ThrowExpr
;;;;

(defmethod handler :throw-expr/enter
  [kind method ^Compiler$ThrowExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :throw-expr/exit
  [kind method ^Compiler$ThrowExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; TryExpr
;;;;

(defmethod handler :try-expr/enter
  [kind method ^Compiler$TryExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :try-expr/exit
  [kind method ^Compiler$TryExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; UnresolvedVarExpr
;;;;

(defmethod handler :unresolved-var-expr/enter
  [kind method ^Compiler$UnresolvedVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :unresolved-var-expr/exit
  [kind method ^Compiler$UnresolvedVarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; UntypedExpr
;;;;

(defmethod handler :untyped-expr/enter
  [kind method ^Compiler$UntypedExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :untyped-expr/exit
  [kind method ^Compiler$UntypedExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; VarExpr
;;;;

(defmethod handler :var-expr/enter
  [kind method ^Compiler$VarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :var-expr/exit
  [kind method ^Compiler$VarExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

;;;;
;; VectorExpr
;;;;

(defmethod handler :vector-expr/enter
  [kind method ^Compiler$VectorExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))

(defmethod handler :vector-expr/exit
  [kind method ^Compiler$VectorExpr _expr ^Compiler$C _context ^Compiler$ObjExpr objx ^GeneratorAdapter _gen]
  (log/debug kind method (get-coords objx)))
