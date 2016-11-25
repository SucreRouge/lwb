; lwb Logic WorkBench -- Natural deduction

; Copyright (c) 2016 Burkhardt Renz, THM. All rights reserved.
; The use and distribution terms for this software are covered by the
; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php).
; By using this software in any fashion, you are agreeing to be bound by
; the terms of this license.

(ns lwb.nd.examples.ltl-classical
  (:require [lwb.nd.repl :refer :all]))

(load-logic :ltl)

; interactive checking in the repl for nd

; -----------------------------------------------------------------------------------------
; tnd

(proof '(at [i] (or P (not P))))
(step-b :raa 2)
(swap '?1 'i)
(step-b :not-e 3 1)
(step-b :or-i2 3)
(step-b :not-i 3)
(swap '?2 'i)
(step-b :not-e 4 1)
(step-b :or-i1 4)

; (export "resources/nd/theorems-ltl.edn" :tnd)

; -----------------------------------------------------------------------------------------
; always-tnd

(proof '(at [i] (always (or P (not P)))))
(step-b :always-i 2)
(swap '?1 'j)
(step-f :tnd)
(swap '?2 'j)
(swap '?3 'P)

; (export "resources/nd/theorems-ltl.edn" :always-tnd)

; -----------------------------------------------------------------------------------------
; contrap

(proof '(at [i] (impl A B)) '(at [i] (impl (not B) (not A))))
(step-b :impl-i 3)
(step-b :not-i 4)
(swap '?1 'i)
(step-f :impl-e 1 3)
(step-b :not-e 6 2)

; (export "resources/nd/theorems-ltl.edn" :contrap)

; -----------------------------------------------------------------------------------------
; always->finally

(proof '(at [i] (always A)) '(at [i] (finally A)))
(step-f :serial)
(swap '?1 'i)
(swap '?2 'j)
(step-f :always-e 1 2)
(step-b :finally-i 5 3)

; (export "resources/nd/theorems-ltl.edn" :always->finally)

; or 
(proof '(at [i] (always A)) '(at [i] (finally A)))
(step-f :reflexiv)
(swap '?1 'i)
(step-f :always-e 1 2)
(step-b :finally-i 5 3)

; -----------------------------------------------------------------------------------------
; always-impl->impl-always

(proof '(at [i] (always (impl A B))) '(at [i] (impl (always A) (always B))))
(step-b :impl-i 3)
(step-b :always-i 4)
(swap '?1 'j)
(step-f :always-e 1 3)
(step-f :always-e 2 3)
(step-f :impl-e 4 5)

;(export "resources/nd/theorems-ltl.edn" :always-impl->impl-always)

; -----------------------------------------------------------------------------------------
; atnext-not->not-atnext

(proof '(at [i] (atnext (not A))) '(at [i] (not (atnext A))))
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-f :atnext-e 1 2)
(step-b :not-i 5)
(swap '?3 'j) 
(step-f :atnext-e 4 2)
(step-f :not-e 3 5)
(swap '?4 'j)

; (export "resources/nd/theorems-ltl.edn" :atnext-not->not-atnext)

; -----------------------------------------------------------------------------------------
; not-atnext->atnext-not

(proof '(at [i] (not (atnext A))) '(at [i] (atnext (not A))))
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-b :atnext-i 4 :? 2)
(step-b :not-i 4)
(swap '?3 'j)
(step-f :atnext-i 3 2)
(step-f :not-e 1 4)
(swap '?4 'j)

; (export "resources/nd/theorems-ltl.edn" :not-atnext->atnext-not)

; -----------------------------------------------------------------------------------------
; not-finally->always-not

(proof '(at [i] (not (finally A))) '(at [i] (always (not A))))
(step-b :always-i 3)
(swap '?1 'j)
(step-b :not-i 4)
(step-f :finally-i 3 2)
(swap '?2 'i)
(step-b :not-e 6 1)

; (export "resources/nd/theorems-ltl.edn" :not-finally->always-not)

; -----------------------------------------------------------------------------------------
; always-not->not-finally

(proof '(at [i] (always (not A))) '(at [i] (not (finally A))))
(step-b :not-i 3)
(swap '?1 'j)
(step-f :finally-e 2)
(swap '?2 'j)
(swap '?3 '(at [j] contradiction))
(step-f :always-e 1 3)
(step-f :not-e 5 4)
(swap '?4 'j)

; (export "resources/nd/theorems-ltl.edn" :always-not->not-finally)

; -----------------------------------------------------------------------------------------
; finally-not->not-always

(proof '(at [i] (finally (not A))) '(at [i] (not (always A))))
(step-b :not-i 3)
(swap '?1 'j)
(step-f :finally-e 1)
(swap '?2 'j)
(swap '?3 '(at [j] contradiction))
(step-f :always-e 2 3)
(step-b :not-e 7 4)

; (export "resources/nd/theorems-ltl.edn" :finally-not->not-always)

; -----------------------------------------------------------------------------------------
; notnot-e

(proof '(at [i] (not (not A))) '(at [i] A))
(step-b :raa 3)
(swap '?1 'i)
(step-f :not-e 1 2)
(swap '?2 'i)

; (export "resources/nd/theorems-ltl.edn" :notnot-e)

; -----------------------------------------------------------------------------------------
; always-notnot-e

(proof '(at [i] (always (not (not A)))) '(at [i] (always A)))
(step-b :always-i 3)
(swap '?1 'j)
(step-f :always-e 1 2)
(step-f :notnot-e 3)

; (export "resources/nd/theorems-ltl.edn" :always-notnot-e)

; -----------------------------------------------------------------------------------------
; not-always->finally-not

(proof '(at [i] (not (always A))) '(at [i] (finally (not A))))
(step-b :raa 3)
(swap '?1 'i)
(step-f :not-finally->always-not 2)
(step-f :always-notnot-e 3)
(step-f :not-e 1 4)
(swap '?2 'i)

; (export "resources/nd/theorems-ltl.edn" :not-always->finally-not)

; -----------------------------------------------------------------------------------------
; atnext-impl->impl-atnext

(proof '(at [i] (atnext (impl A B))) '(at [i] (impl (atnext A) (atnext B))))
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-b :impl-i 4)
(step-f :atnext-e 1 2)
(step-b :atnext-i 6 :? 2)
(step-f :atnext-e 3 2)
(step-f :impl-e 4 5)

; (export "resources/nd/theorems-ltl.edn" :atnext-impl->impl-atnext)

; -----------------------------------------------------------------------------------------
; always-serial

(proof '(at [i] (always A)) '(at [i] (and A (atnext (always A)))))
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-f :reflexiv)
(swap '?3 'i)
(step-b :and-i 5)
(step-f :always-e 1 3)
(step-b :atnext-i 6 :? 2)
(step-b :always-i 6)
(swap '?4 'k)
(step-f :succ/<= 2)
(step-f :transitiv 6 5)
(step-f :always-e 1 7)

; (export "resources/nd/theorems-ltl.edn" :always-serial)

; Bolotov 4
(proof '(at [i] (impl (and true A) A)))
(step-b :impl-i 2)
(step-f :and-e2 1)


; Bolotov 5
(proof '(at [i] (impl (atnext (not A)) (not (atnext A)))))
(step-b :impl-i 2)
(step-f :atnext-not->not-atnext 1)

; Bolotov 6
(proof '(at [i] (impl (not (finally A)) (always (not A)))))
(step-b :impl-i 2)
(step-f :not-finally->always-not 1)

; Bolotov 7
(proof '(at [i] (impl (not (always A)) (finally (not A)))))
(step-b :impl-i 2)
(step-f :not-always->finally-not 1)

; Bolotov 8
(proof '(at [i] (impl (and A (atnext contradiction)) contradiction)))
(step-b :impl-i 2)
(step-f :and-e2 1)
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-f :succ/<= 3)
(step-f :atnext-e 2 3)
(step-b :not-e 7)
(swap '?3 'j)
(swap '?4 'contradiction)
(step-b :not-i 7)
(swap '?5 'j)

(load-theorem :b-8.1)

(proof '(at [i] (always (impl (and A (atnext contradiction)) contradiction))))
(step-b :always-i 2)
(swap '?1 'j)
(step-f :b-8.1)
(swap '?2 'j)
(swap '?3 'A)

(load-theorem :b-8.2)

(proof '(at [i] (always (impl contradiction contradiction))))
(step-b :always-i 2)
(swap '?1 'j)
(step-b :impl-i 3)

(load-theorem  :b-8.3)

(proof '(at [i] (impl (until A contradiction) contradiction)))
(step-f :b-8.2)
(swap '?1 'i)
(swap '?2 'A)
(step-f :b-8.3)
(swap '?3 'i)
(step-f :until-e 2 1)

; Bolotov 9

;; wie geht das????
(proof '(at [i] (until (not A) (and (not A) contradiction))) '(at [i] (until (not A) contradiction)))
;; braucht man hier step-i (step-inside??)

(proof '(at [i] (impl (finally A) (until true A))))
(step-b :impl-i 2)
(step-f :finally-e 1)
(swap '?1 'j)
(swap '?2 '(at [j] A))
(step-b :raa 7)
(step-f :not-until 6)
(swap '?3 'i)
(step-f :or-e 7 9)
(step-f :always-e 8)
(swap '?4 'j)


; -----------------------------------------------------------------------------------------
; atnext-serial = induction principle
; Bolotov 13 
; Das induktionsprinzip steckt in der Regel :until-e

; TODO induction stucks!!

(proof '(at [i] (always (impl A (atnext A)))) '(at [i] (impl A (always A))))
(step-b :impl-i 3)
(step-b :always-i 4)
(swap '?1 'j)
(step-f :succ)
(swap '?2 'k)
(swap '?3 'j)
(step-f :always-e 1 3)
(step-b :atnext-e 7 :? 4)
(step-b :atnext-i 7 :? 4)

; induction 

(proof '(at [i] (and A (always (impl A (atnext A))))) '(at [i] (always A)))
(step-f :and-e1 1)
(step-f :and-e2 1)
(step-b :raa 5)
(swap '?1 'i)
(step-f :not-always->finally-not 4)
(step-b :not-e 7 4)
(step-b :always-i 7)
(swap '?2 'j)
(step-f :succ)
(swap '?3 'k)
(swap '?4 'j) 
(step-f :always-e 3 6)
(step-b :atnext-e 10 :? 7)
(step-b :atnext-i 10 :? 7)
; wie weiter?
(step-f :impl-e 8)


; -----------------------------------------------------------------------------------------
; until-finally

; TODO
(proof '(at [i] (until A B)) '(at [i] (finally B)))
(step-b :finally-i 3)
(swap '?1 'j)

; -----------------------------------------------------------------------------------------
; (9) in Fisher et al

; TODO
(proof '(at [i] (until A B)) '(at [i] (or B (and A (atnext (until A B))))))

; -----------------------------------------------------------------------------------------
; (10) in Fisher et al -- the other  direction of (9)

; TODO
(proof '(at [i] (or B (and A (atnext (until A B))))) '(at [i] (until A B)))

; -----------------------------------------------------------------------------------------
; atnext-and->and-atnext

(proof '(at [i] (atnext (and A B))) '(at [i] (and (atnext A) (atnext B))))
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-f :atnext-e 1 2)
(step-f :and-e1 3)
(step-f :and-e2 3)
(step-f :atnext-i 4 2)
(step-f :atnext-i 5 2)
(step-b :and-i 9)

; (export "resources/nd/theorems-ltl.edn" :atnext-and->and-atnext)

; -----------------------------------------------------------------------------------------
; and-atnext->atnext-and

(proof '(at [i] (and (atnext A) (atnext B))) '(at [i] (atnext (and A B))))
(step-f :and-e1 1)
(step-f :and-e2 1)
(step-f :succ)
(swap '?1 'i)
(swap '?2 'j)
(step-f :atnext-e 2 4)
(step-f :atnext-e 3 4)
(step-f :and-i 5 6)
(step-b :atnext-i 9 7)

; (export "resources/nd/theorems-ltl.edn" :and-atnext->atnext-and)


; -----------------------------------------------------------------------------------------
; always->always-always

(proof '(at [i] (always A)) '(at [i] (always (always A))))
(step-b :always-i 3)
(swap '?1 'j)
(step-b :always-i 4)
(swap '?2 'k)
(step-f :transitiv 2 3)
(step-f :always-e 1 4)

; (export "resources/nd/theorems-ltl.edn" :always->always-always)
