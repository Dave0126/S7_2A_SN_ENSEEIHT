@doc doc"""
Résolution des problèmes de minimisation sous contraintes d'égalités

# Syntaxe
```julia
Lagrangien_Augmente(algo,fonc,contrainte,gradfonc,hessfonc,grad_contrainte,
			hess_contrainte,x0,options)
```

# Entrées
  * **algo** 		   : (String) l'algorithme sans contraintes à utiliser:
    - **"newton"**  : pour l'algorithme de Newton
    - **"cauchy"**  : pour le pas de Cauchy
    - **"gct"**     : pour le gradient conjugué tronqué
  * **fonc** 		   : (Function) la fonction à minimiser
  * **contrainte**	   : (Function) la contrainte [x est dans le domaine des contraintes ssi ``c(x)=0``]
  * **gradfonc**       : (Function) le gradient de la fonction
  * **hessfonc** 	   : (Function) la hessienne de la fonction
  * **grad_contrainte** : (Function) le gradient de la contrainte
  * **hess_contrainte** : (Function) la hessienne de la contrainte
  * **x0** 			   : (Array{Float,1}) la première composante du point de départ du Lagrangien
  * **options**		   : (Array{Float,1})
    1. **epsilon** 	   : utilisé dans les critères d'arrêt
    2. **tol**         : la tolérance utilisée dans les critères d'arrêt
    3. **itermax** 	   : nombre maximal d'itération dans la boucle principale
    4. **lambda0**	   : la deuxième composante du point de départ du Lagrangien
    5. **mu0,tho** 	   : valeurs initiales des variables de l'algorithme

# Sorties
* **xmin**		   : (Array{Float,1}) une approximation de la solution du problème avec contraintes
* **fxmin** 	   : (Float) ``f(x_{min})``
* **flag**		   : (Integer) indicateur du déroulement de l'algorithme
   - **0**    : convergence
   - **1**    : nombre maximal d'itération atteint
   - **(-1)** : une erreur s'est produite
* **niters** 	   : (Integer) nombre d'itérations réalisées

# Exemple d'appel
```julia
using LinearAlgebra
f(x)=100*(x[2]-x[1]^2)^2+(1-x[1])^2
gradf(x)=[-400*x[1]*(x[2]-x[1]^2)-2*(1-x[1]) ; 200*(x[2]-x[1]^2)]
hessf(x)=[-400*(x[2]-3*x[1]^2)+2  -400*x[1];-400*x[1]  200]
algo = "gct" # ou newton|gct
x0 = [1; 0]
options = []
contrainte(x) =  (x[1]^2) + (x[2]^2) -1.5
grad_contrainte(x) = [2*x[1] ;2*x[2]]
hess_contrainte(x) = [2 0;0 2]
output = Lagrangien_Augmente(algo,f,contrainte,gradf,hessf,grad_contrainte,hess_contrainte,x0,options)
```
"""
function Lagrangien_Augmente(algo,fonc::Function,contrainte::Function,gradfonc::Function,
	hessfonc::Function,grad_contrainte::Function,hess_contrainte::Function,x0,options)

	if options == []
		epsilon = 1e-8
		tol = 1e-5
		itermax = 1000
		lambda0 = 2
		mu0 = 100
		tho = 2
	else
		epsilon = options[1]
		tol = options[2]
		itermax = options[3]
		lambda0 = options[4] # λ0
		mu0 = options[5] # μ0
		tho = options[6] # τ
	end

  Tol_abs = sqrt(eps())

  n = length(x0)
  xmin = zeros(n)

	fxmin = 0
	flag = -1
	iter = 0 # k

  ### Initialization of parameters
  eta_chapeau0 = 0.1258925 # η^0  
  alpha = 0.1
  beta = 0.9
  epsilon0 = 1 / mu0 # ϵ0
  eta = eta_chapeau0 / (mu0 ^ alpha)
  lambda = lambda0
  mu = mu0
  epsilon = epsilon0
  x_k = x0

  while (true)
    # definition of Lagrangien
    La_f(x) = fonc(x) + transpose(lambda) * contrainte(x) + (mu/2) * (norm(contrainte(x)))^2
    La_gradf(x) = gradfonc(x) + transpose(lambda) * grad_contrainte(x) + mu * grad_contrainte(x) * contrainte(x)
    La_hessf(x) = hessfonc(x) + transpose(lambda) * hess_contrainte(x) + mu * hess_contrainte(x) * contrainte(x) + mu * grad_contrainte(x) * transpose(grad_contrainte(x))

    # Calculer approximation un minimiseur x_k
    if algo == "newton"
      x_k, _, _, _ = Algorithme_De_Newton(La_f, La_gradf, La_hessf, x_k, [])
    elseif algo == "cauchy" || algo == "gct"
      x_k, _, _, _ = Regions_De_Confiance(algo, La_f, La_gradf, La_hessf, x_k, [])
    end

    ##### convergence de l'algorithme global
    # ∇ La(xmin, λ, 0)
    La_gradf_xmin_lambda = gradfonc(x_k) + transpose(lambda) * grad_contrainte(x_k)
    # ∇ La(x0, λ0, 0)
    La_gradf_x0_lambda0 = gradfonc(x0) + transpose(lambda0) * grad_contrainte(x_k)

    ##### Condition of STOP
    # || ∇ La(xmin, λ, 0) ||
    if norm(La_gradf_xmin_lambda) <= max(epsilon, tol * norm(La_gradf_x0_lambda0)) && norm(contrainte(x_k)) <= max(tol, tol * norm(contrainte(x0)))
      # convergence
      flag = 0
      break
    elseif iter >= itermax
      # nombre maximal d'itération atteint
      flag = 1
      break
    end

    ##### Update the parameters
    if norm(contrainte(x_k)) <= eta
      lambda = lambda + mu * contrainte(x_k)
      epsilon = epsilon / mu
      eta = eta / (mu ^ beta)
      iter = iter + 1
    else
      mu = tho * mu
      epsilon = epsilon0 / mu
      eta = eta_chapeau0 / (mu ^ alpha)
      iter = iter + 1
    end

  end
  xmin = x_k
  fxmin = fonc(xmin)
	return xmin,fxmin,flag,iter 
end
