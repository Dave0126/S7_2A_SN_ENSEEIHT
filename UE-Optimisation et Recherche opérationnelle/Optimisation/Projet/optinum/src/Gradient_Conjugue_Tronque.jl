@doc doc"""
Minimise le problème : ``min_{||s||< \delta_{k}} q_k(s) = s^{t}g + (1/2)s^{t}Hs``
                        pour la ``k^{ème}`` itération de l'algorithme des régions de confiance

# Syntaxe
```julia
sk = Gradient_Conjugue_Tronque(fk,gradfk,hessfk,option)
```

# Entrées :   
   * **gradfk**           : (Array{Float,1}) le gradient de la fonction f appliqué au point xk
   * **hessfk**           : (Array{Float,2}) la Hessienne de la fonction f appliqué au point xk
   * **options**          : (Array{Float,1})
      - **delta**    : le rayon de la région de confiance
      - **max_iter** : le nombre maximal d'iterations
      - **tol**      : la tolérance pour la condition d'arrêt sur le gradient

      
# Sorties:
   * **s** : (Array{Float,1}) le pas s qui approche la solution du problème : ``min_{||s||< \delta_{k}} q(s)``

# Exemple d'appel:
```julia
gradf(x)=[-400*x[1]*(x[2]-x[1]^2)-2*(1-x[1]) ; 200*(x[2]-x[1]^2)]
hessf(x)=[-400*(x[2]-3*x[1]^2)+2  -400*x[1];-400*x[1]  200]
xk = [1; 0]
options = []
s = Gradient_Conjugue_Tronque(gradf(xk),hessf(xk),options)
```
"""
function Gradient_Conjugue_Tronque(gradfk,hessfk,options)

    "# Si option est vide on initialise les 3 paramètres par défaut"
    if options == []
        deltak = 2
        max_iter = 100
        tol = 1e-6
    else
        deltak = options[1]
        max_iter = options[2]
        tol = options[3]
    end

   n = length(gradfk)
   s = zeros(n) # s_0
   g = gradfk # g_0
   p = - gradfk # p_0
   nb_iters = 0 
   q(s) = transpose(gradfk) * s + 1/2 * transpose(s) * hessfk * s
   canExit = false # flag parameter

   while(true)
        # κ: kappa
        kappa = transpose(p) * hessfk * p
        if (kappa < 0)
            # TODO
            # σ: sigma
            # a*σ^2 + b*σ + c = 0
            a = norm(p,2)^2
            b = 2 * dot(s,p)
            c = norm(s,2)^2 - deltak^2
            # 这里差两个判断条件 也许是a==0和b==0
            if ( a == 0)
                if (b == 0)
                    # a==0, b==0, no roots
                    """
                    canExit = true
                    """
                    break

                else
                    # TODO
                    # a==0, b!=0, 1 root
                    sigma = (-c) / b
                    s = s + sigma * p
                    """
                    canExit = true
                    """
                    break
                end # end if
            else
                # a!=0, b!=0, 2 roots
                # 求根公式：delta
                discriminant = b^2 - 4 * a * c
                sigma1 = (-b + sqrt(discriminant)) / (2 * a)
                sigma2 = (-b - sqrt(discriminant)) / (2 * a)
                if ( q(s + sigma1 * p) < q(s + sigma2 * p) )
                    s = s + sigma1 * p
                else
                    s = s + sigma2 * p
                end # end if
                break # break loop
            end# end if
        else
            if kappa == 0 
                break # break loop
            else
                alpha = transpose(g) * g / kappa
                if (norm(s + alpha * p,2) >= deltak)
                    # TODO
                    # σ: sigma
                    a = norm(p,2)^2
                    b = 2 * dot(s,p)
                    c = norm(s,2)^2 - deltak^2
                    # 这里差两个判断条件 也许是a或b
                    if ( a == 0)
                        if (b == 0)
                            # a==0, b==0, no roots
                            break 
                        else
                            # a==0, b!=0, 1 root
                            sigma = (-c) / b
                            s = s + sigma * p
                            break
                        end # end if
                    else
                        # a!=0, b!=0, 2 roots
                        # 求根公式：delta
                        discriminant = b^2 - 4 * a * c
                        sigma1 = (-b + sqrt(discriminant)) / (2 * a)
                        sigma2 = (-b - sqrt(discriminant)) / (2 * a)
                        if ( q(s + sigma1 * p) < q(s + sigma2 * p) )
                            s = s + sigma1 * p
                        else
                            s = s + sigma2 * p
                        end # end if
                        break
                    end # end if
                end # end if  
            
            end # end if
        end # end if

        # Update the parameters
        s = s + alpha * p
        g_previous = g
        g = g_previous + alpha * hessfk * p
        beta = transpose(g) * g / (transpose(g_previous) * g_previous)
        p = -g + beta * p
        nb_iters = nb_iters + 1

        # la condition de sortie
        if ( norm(g,2) <= (tol * norm(gradfk)) || nb_iters >= max_iter)
            """
            canExit = true
            """
            break
        end # end if

    end # end while
   return s
end
