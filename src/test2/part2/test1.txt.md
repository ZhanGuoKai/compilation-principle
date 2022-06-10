1、
符号栈：( ( 
```mermaid
graph LR
A((A))
A --0--> B
B((B))
```
2、
符号栈：( ( | 
```mermaid
graph LR
A((A))
A --0--> B
B((B))
C((C))
C --1--> D
D((D))
```
3、
符号栈：( ( 
```mermaid
graph LR
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
```
4、
符号栈：( · ( 
```mermaid
graph LR
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
G((G))
G --0--> H
H((H))
```
5、
符号栈：( · ( | 
```mermaid
graph LR
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
G((G))
G --0--> H
H((H))
I((I))
I --1--> J
J((J))
```
6、
符号栈：( · ( 
```mermaid
graph LR
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
```
7、
符号栈：( 
```mermaid
graph LR
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
```
8、
符号栈：空
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
```
9、
符号栈：| ( ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
O((O))
O --0--> P
P((P))
```
10、
符号栈：| ( ( | 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
O((O))
O --0--> P
P((P))
Q((Q))
Q --1--> R
R((R))
```
11、
符号栈：| ( ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
```
12、
符号栈：| ( · ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
U((U))
U --0--> V
V((V))
```
13、
符号栈：| ( · ( | 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
U((U))
U --0--> V
V((V))
W((W))
W --1--> X
X((X))
```
14、
符号栈：| ( · ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
```
15、
符号栈：| ( · · ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
AA((AA))
AA --0--> AB
AB((AB))
```
16、
符号栈：| ( · · ( | 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
AA((AA))
AA --0--> AB
AB((AB))
AC((AC))
AC --1--> AD
AD((AD))
```
17、
符号栈：| ( · · ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
AE((AE))
AE --ε--> AC
AE --ε--> AA
AC((AC))
AC --1--> AD
AA((AA))
AA --0--> AB
AD((AD))
AD --ε--> AF
AB((AB))
AB --ε--> AF
AF((AF))
```
18、
符号栈：| ( · 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
Z --ε--> AE
AE((AE))
AE --ε--> AC
AE --ε--> AA
AC((AC))
AC --1--> AD
AA((AA))
AA --0--> AB
AD((AD))
AD --ε--> AF
AB((AB))
AB --ε--> AF
AF((AF))
```
19、
符号栈：| ( 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
S((S))
S --ε--> O
S --ε--> Q
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
T --ε--> Y
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
Z --ε--> AE
AE((AE))
AE --ε--> AC
AE --ε--> AA
AC((AC))
AC --1--> AD
AA((AA))
AA --0--> AB
AD((AD))
AD --ε--> AF
AB((AB))
AB --ε--> AF
AF((AF))
```
20、
符号栈：| 
```mermaid
graph LR
M((M))
M --ε--> N
M --ε--> E
N((N))
E((E))
E --ε--> A
E --ε--> C
A((A))
A --0--> B
C((C))
C --1--> D
B((B))
B --ε--> F
D((D))
D --ε--> F
F((F))
F --ε--> K
K((K))
K --ε--> G
K --ε--> I
G((G))
G --0--> H
I((I))
I --1--> J
H((H))
H --ε--> L
J((J))
J --ε--> L
L((L))
L --ε--> N
L --ε--> E
AG((AG))
AG --ε--> S
AG --ε--> AH
S((S))
S --ε--> O
S --ε--> Q
AH((AH))
O((O))
O --0--> P
Q((Q))
Q --1--> R
P((P))
P --ε--> T
R((R))
R --ε--> T
T((T))
T --ε--> Y
Y((Y))
Y --ε--> U
Y --ε--> W
U((U))
U --0--> V
W((W))
W --1--> X
V((V))
V --ε--> Z
X((X))
X --ε--> Z
Z((Z))
Z --ε--> AE
AE((AE))
AE --ε--> AC
AE --ε--> AA
AC((AC))
AC --1--> AD
AA((AA))
AA --0--> AB
AD((AD))
AD --ε--> AF
AB((AB))
AB --ε--> AF
AF((AF))
AF --ε--> S
AF --ε--> AH
```
21、
符号栈：空
```mermaid
graph LR
AI((AI))
AI --ε--> M
AI --ε--> AG
M((M))
M --ε--> N
M --ε--> E
AG((AG))
AG --ε--> S
AG --ε--> AH
N((N))
N --ε--> AJ
E((E))
E --ε--> A
E --ε--> C
S((S))
S --ε--> O
S --ε--> Q
AH((AH))
AH --ε--> AJ
AJ((AJ))
A((A))
A --0--> B
C((C))
C --1--> D
O((O))
O --0--> P
Q((Q))
Q --1--> R
B((B))
B --ε--> F
D((D))
D --ε--> F
P((P))
P --ε--> T
R((R))
R --ε--> T
F((F))
F --ε--> K
T((T))
T --ε--> Y
K((K))
K --ε--> G
K --ε--> I
Y((Y))
Y --ε--> U
Y --ε--> W
G((G))
G --0--> H
I((I))
I --1--> J
U((U))
U --0--> V
W((W))
W --1--> X
H((H))
H --ε--> L
J((J))
J --ε--> L
V((V))
V --ε--> Z
X((X))
X --ε--> Z
L((L))
L --ε--> N
L --ε--> E
Z((Z))
Z --ε--> AE
AE((AE))
AE --ε--> AC
AE --ε--> AA
AC((AC))
AC --1--> AD
AA((AA))
AA --0--> AB
AD((AD))
AD --ε--> AF
AB((AB))
AB --ε--> AF
AF((AF))
AF --ε--> S
AF --ε--> AH
```
