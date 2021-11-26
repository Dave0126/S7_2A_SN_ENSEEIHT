TD-Sychronisation par la communication(CSP,Go,Ada)
- envoi  (bloquant ou pas) d'un message sur un canal
- recepiton bloquant
- alternation en reception (ou en recetion / emission)

Voie unique:
Code d'un train:
boucle
    // demander a entrer

    // parcours sur la voie unique
    // indique qu'on sort
finboucle

Canoux necessaire:
- entrerEO, entrerOE, sortir (initile de disstinguer ,sortirEO, sortirOE )
- entre (msg EO ou OE), sortir
- requete (msg: entrerEO, entrerOE, sortir)

Selection sur le contenu des message : NON

Acticite de synchro:
boucle
    recevoir un msg (parmi un ensemble de canaux)
    le traiter
finboucle

Code d‘un train
    boucle
        entrerEO !_
        ...
        sortir !_
        ...
    fin boucle

Notation Go
    for {
        entrerEO <- true;
        ...
        sortir <- true;
        ...
    }

Activite de sychro
variable
    sens : ( OE, OE, libre) = Libre
    nb : int =0

boucle 
    select
        sens = EO V sens = Libre
            ->  entrerEO ?_ ;
                sens = EO;
                nb++;
        sens = OE V sens Libre
            ->  entrerOE ?_ ;
                sens = OE;
                nb++;
        sortir ?_ ; nb--;
            if (nb = 0) 
                then sens = Libre; 
            end if
    end select
end boucle

Version GO:
const{
    sensEO = 1;
    sensOE = 2;
}
func when (b bool, c char bool) char bool{
    if b
        return c
    else
        return nil // nil = canal ou aucune action n'est faisable
}
func voieUnique (entrerEO char bool, entrerOE char bool, sortir char bool)
这里需要补充

Approche par automate
1 etat = 1 ensemble specifique de canaux acceptes en reception

Voie unique, capacite = 2 :
(|---|)<-entrerEO-
(|2EO|)
(|---|)

Activite de sychro
variable
    etat (Vide, 1EO, 2EO, 1OE, 2OE) = Vide

boucle 
    if etat = Vide then
        select
            entrerEO ?_ ;
            etat = 1EO;

            entrerOE ?_ ;
            etat = 1OE;
        end select

    elseif etat = 1EO then 
        select
            entrerEO ?_ ;
            etat = 2EO;

            entrerOE ?_ ;
            etat = 2OE;
        end select
    
    elseif etat = 2EO then 
        ...
    end if
end boucle

Ada:
Tache ~ activites
accepteut des RDV
client                       server
|         (demande de RDV)      |
|------------------------------>|
                                |   (acceptation/traite du RDV )
|<------------------------------|
            (retour)

- demende bloquante
- acceptation bloquante
- alternation sur l'acceptation
- Cas particulier : sychro pure
    -> pas de parameter en entree, ni en sortie

task VU:
    entry entrerEO;
    entry entrerOE;
    entry sortir;
end

task body Train is
    begin
        loop
            VU.entrerEO; //demande de RDV
            ...
            VU.sortir;
            ...
        endloop
end Train

task body VU is
    type etat is (SnesEO, SensOE, Libre);
        sens: Etat = Libre;
        nb: Natrral = 0;
    
    begin
        loop
            select
                when sens = Libre or sens + SensOE
                ->  accept EntrerOE;
                    sens := SnesOE;
                    nb := nb+1;
                or
                when sens = Libre or sens + SensEO
                ->  accept EntrerEO;
                    sens := SnesEO;
                    nb := nb+1;
                or
                when accept Sortir;
                nb := nb-1;
                if nb=0 then 
                    sens := Libre;
                end if
            end select
        end loop
    end VU

Tournoi de belotte
Salle agent en nb quelconque de table,
il faut garantir que lonter les tables occupees sont complites
-> garantir que le nb de personnes a l'interieur est un multipale de 4

<INERFACE>
    2 canaux : entrer, sortir
    ou
    2 RDV : entrer, sortir

<Regle>
    4 entrees ou 4 sorties ou 1 echange

task Gardien is
    entry entrer;
    entry sortir;
    // RDV'conut = nb de tache en attente de RDV
end

task body is
    begin
        loop
            select
               when entrer'count >= 4
                ->  accpet entrer;
                    accpet entrer;
                    accpet entrer;
                    accpet entrer;
                or
                when sortir'count >= 4
                ->  accpet sortir; 
                    accpet sortir; 
                    accpet sortir; 
                    accpet sortir; 
                or
                when entrer'count >= 1 and sortir'count >= 1
                ->  accpet entrer;
                    accpet sortir; 
            end select
        end
    end

Questions:
<1> Que se parse -tl si aucune garde n'est vraie?
<2> Pas d'evaluation dynamique des garder?

task Gardien
    preparerEntere;
    preparerSortir;
    entrer;
    sortir;
end

Client
    begin
        Gardien.preparerEntrer;
        Gardien.Entrer;
        ...
    end

task body Gardien is
    demE : Natural =0;
    demS : Natural =0;
    (nbTable : int =0)

    begin
        loop
            select
                accept preparerEntrer; demE++;
                or
                accept preparerSorir; demS++;
                or
                when demE >= 4 (and nbTable < N)
                ->  accpet entrer;
                    accpet entrer;
                    accpet entrer;
                    accpet entrer;
                    (nbTable ++;)
                or
                when demS >= 4
                ->  accpet sortir;
                    accpet sortir;
                    accpet sortir;
                    accpet sortir;
                    (nbTbale--;)
                or
                when demE >= 1 and demS >= 1
                ->  accpet entrer;
                    accpet sortir;
            end
        end
    end

On ne prut choisir d'accpter un RDV ou recevoir un message selon le contenu.
->  2 messages
        1 avec les valeurs, non bloquents
        1 avec "faire l'action"

Approche automate:
(图略，后期补)



