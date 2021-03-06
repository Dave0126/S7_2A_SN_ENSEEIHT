@doc doc"""
Approximation de la solution du sous-problème ``q_k(s) = s^{t}g + (1/2)s^{t}Hs`` 
        avec ``s=-t g_k,t > 0,||s||< \delta_k ``


# Syntaxe
```julia
s1, e1 = Pas_De_Cauchy(gradient,Hessienne,delta)
```

# Entrées
 * **gradfk** : (Array{Float,1}) le gradient de la fonction f appliqué au point ``x_k``
 * **hessfk** : (Array{Float,2}) la Hessienne de la fonction f appliqué au point ``x_k``
 * **delta**  : (Float) le rayon de la région de confiance

# Sorties
 * **s** : (Array{Float,1}) une approximation de la  solution du sous-problème
 * **e** : (Integer) indice indiquant l'état de sortie:
        si g != 0
            si on ne sature pas la boule
              e <- 1
            sinon
              e <- -1
        sinon
            e <- 0

# Exemple d'appel
```julia
g1 = [0; 0]
H1 = [7 0 ; 0 2]
delta1 = 1
s1, e1 = Pas_De_Cauchy(g1,H1,delta1)
```
"""
function Pas_De_Cauchy(g,H,delta)
    ### Initialization of parameters
    e = 0 # the state of exit
    n = length(g)
    s = zeros(n) # solution of sub-problem
    
    # if the Gradient is not null
    if norm(g) > 0
      t = norm(g)^2 / (transpose(g) * H * g)
      s = -t * g
      e = 1

      #si on ne sature pas la boule
      if norm(s) > delta || transpose(g) * H * g < 0
        s = - (delta/norm(g)) * g
        e = -1
      end # end if

    # if the Gradient is null
    else
      t = 0
      s = t * g
      e = 0
    end # end if

    return s, e
end # end fonc
