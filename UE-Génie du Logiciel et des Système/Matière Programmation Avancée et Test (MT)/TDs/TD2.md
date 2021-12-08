# Metaprogrammation et Tests - TD2
## Exercice 1
Une fonction prend comme paramètres trois flottants représentant les longueurs des
côtés d’un triangle. Elle renvoie ensuite une valeur entière indiquant s’il s’agit d’un triangle
scalène (valeur 0), isocèle (valeur 1) ou équilatéral (valeur 2)

1.1. Produire une suite de tests pour ce programme : cas de test (Test Case), données de test (Test
Data) et oracle.
```java
int natureTriangle (int a, int b, int c){
    // valeur 0 -> scalene
    // valeur 1 -> isocele
    // valeur 2 -> equilateral
    // valeur -1 -> donnees mal fermees
}
```
Cas de test : Que veut on verifier?

- C1: Verifier que nature Triangle determiner coredeuent que a,b,c formert un triangle : scalene  
  *任意三角形：三边各不相等 且 任意三边之和大于第三边 且 三边均大于0*
- C2: Isocele *等腰三角形* (a==b && a!=c && a>=c/2 ) || (b==c && a!=b) || (a==c && b!=c)
- C3: equilateral *等边三角形：三边相等 且 均大于0*
- C4: a<0, b<0, c<0
- C5: *任意两边之和 小于或等于 第三边*

1.2. Avez-vous fait seulement des tests fonctionnels ou aussi de robustesse ?

## Exercice 2

```java
public static int search(List<?> list, Object elements)
// list == null -> throws NullPointerException
// element == null -> throws NullPointerException
// element ∈ list -> return position
// element ∉ list -> return position - 1
```
2.1. Cette partition est-elle basée sur l’interface ou sur la sémantique de la fonction ?
- element debut de list
- element fin de list
- element aillean ni debut / ni fin

2.2. Montrer que cette partition n’est pas disjointe.
- element n'est pas preveut dans liste
  - list null
  - list vide
  - list non_vide
- element est preveut une fois dans liste
  - taille list == 1
  - taille list > 1
    - fin
    - debut
    - ni fin ni debut
- element est preveut plusieur fois dans liste
  - taille list == nb de precence
  - taille list > nb de precence

2.3. Montrer que cette partition n’est pas complète.

2.4. Proposer une nouvelle partition disjointe et complète.

## Exercice 4
```java
void myFun (int a, int b, int c, int n){
    if (b<c){
        int d = 2 * b;
        int f = 3 * c;
        if (n >= 0 && a >= 0){
            d = n;
            int e = c;
            if (d == ){
                f = f - e;
                if (d < a ){
                    d = a + 1;
                }
                else{
                    d = a - 1;
                }
                System.out.println(a);
            }
        }
    }
}
```
![alt figure1](/Users/dave/Desktop/留学文件/ENSEEIHT/2021-2022/SN2/S7_2A_SN_ENSEELHT/UE-Génie du Logiciel et des Système/Matière Programmation Avancée et Test (MT)/TDs/figure1.jpg) 

|CE1|CE2|D|
|:---:|:---:|:---:|
|f|f|f|
|f|v|f|
|v|f|f|
|v|v|v|

|variable|definition|c-use compute|p-use predicate
|:---:|:---:|:---:|:--:|
|a|1|11,12,15|5,10|
|b|1|3|2|
|c|1|4,7|2|
|n|1|6|5|
|d|3|||
|d|6||8,10|
|d|11|||
|d|13|||
|f|4|9||
|f|9|||
|e|7|9||

### Parie def-use
|variable||
|:---:|:--:|
|a|(1,5),(1,10),(1,11),(1,13),(1,15)|
|b|(1,2),(1,3)|
|c|(1,2),(1,3)|
|...|...|
|d|(6,8), (6,10)|