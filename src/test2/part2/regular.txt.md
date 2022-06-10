1、
符号栈：空
```mermaid
graph LR
A((A))
A --a--> B
B((B))
```
2、
符号栈：· 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
```
3、
符号栈：· · ( 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
E((E))
E --c--> F
F((F))
```
4、
符号栈：· · ( | 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
E((E))
E --c--> F
F((F))
G((G))
G --d--> H
H((H))
```
5、
符号栈：· · ( | · 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
E((E))
E --c--> F
F((F))
G((G))
G --d--> H
H((H))
I((I))
I --a--> J
J((J))
```
6、
符号栈：· · ( | 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
E((E))
E --c--> F
F((F))
G((G))
G --d--> H
H((H))
H --ε--> I
I((I))
I --a--> J
J((J))
```
7、
符号栈：· · ( 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
K((K))
K --ε--> E
K --ε--> G
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
I((I))
I --a--> J
J((J))
J --ε--> L
```
8、
符号栈：· · 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
```
9、
符号栈：· 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
```
10、
符号栈：空
```mermaid
graph LR
A((A))
A --a--> B
B((B))
B --ε--> C
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
```
11、
符号栈：| 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
B --ε--> C
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
O((O))
O --b--> P
P((P))
```
12、
符号栈：| · ( 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
B --ε--> C
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
O((O))
O --b--> P
P((P))
Q((Q))
Q --a--> R
R((R))
```
13、
符号栈：| · ( | 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
B --ε--> C
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
O((O))
O --b--> P
P((P))
Q((Q))
Q --a--> R
R((R))
S((S))
S --c--> T
T((T))
```
14、
符号栈：| · ( 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
B --ε--> C
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
O((O))
O --b--> P
P((P))
U((U))
U --ε--> Q
U --ε--> S
Q((Q))
Q --a--> R
S((S))
S --c--> T
R((R))
R --ε--> V
T((T))
T --ε--> V
V((V))
```
15、
符号栈：| 
```mermaid
graph LR
A((A))
A --a--> B
B((B))
B --ε--> C
C((C))
C --b--> D
D((D))
D --ε--> M
M((M))
M --ε--> K
M --ε--> N
K((K))
K --ε--> E
K --ε--> G
N((N))
E((E))
E --c--> F
G((G))
G --d--> H
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
O((O))
O --b--> P
P((P))
P --ε--> U
U((U))
U --ε--> Q
U --ε--> S
Q((Q))
Q --a--> R
S((S))
S --c--> T
R((R))
R --ε--> V
T((T))
T --ε--> V
V((V))
```
16、
符号栈：空
```mermaid
graph LR
W((W))
W --ε--> A
W --ε--> O
A((A))
A --a--> B
O((O))
O --b--> P
B((B))
B --ε--> C
P((P))
P --ε--> U
C((C))
C --b--> D
U((U))
U --ε--> Q
U --ε--> S
D((D))
D --ε--> M
Q((Q))
Q --a--> R
S((S))
S --c--> T
M((M))
M --ε--> K
M --ε--> N
R((R))
R --ε--> V
T((T))
T --ε--> V
K((K))
K --ε--> E
K --ε--> G
N((N))
N --ε--> X
V((V))
V --ε--> X
E((E))
E --c--> F
G((G))
G --d--> H
X((X))
F((F))
F --ε--> L
H((H))
H --ε--> I
L((L))
L --ε--> K
L --ε--> N
I((I))
I --a--> J
J((J))
J --ε--> L
```
```mermaid
graph LR
OA((OA))
OA --ε--> EA
OA --ε--> IA
EA((EA))
EA --ε--> FA
EA --ε--> CA
IA((IA))
IA --b--> JA
FA((FA))
FA --ε--> GA
CA((CA))
CA --ε--> AA
CA --ε--> Y
JA((JA))
JA --ε--> KA
GA((GA))
GA --a--> HA
AA((AA))
AA --b--> BA
Y((Y))
Y --a--> Z
KA((KA))
KA --c--> LA
HA((HA))
HA --ε--> PA
BA((BA))
BA --ε--> DA
Z((Z))
Z --ε--> DA
LA((LA))
LA --ε--> MA
PA((PA))
DA((DA))
DA --ε--> FA
DA --ε--> CA
MA((MA))
MA --d--> NA
NA((NA))
NA --ε--> PA
```
