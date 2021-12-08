@doc doc"""
Tester l'algorithme de pas de Cauchy

# Entrées :
   * afficher : (Bool) affichage ou non des résultats de chaque test

# Les cas de test (dans l'ordre)
   * quadratique 1
   * quadratique 2
   * quadratique 3
"""
function tester_pas_de_cauchy(afficher::Bool,Pas_De_Cauchy::Function)

	tol_erreur = 1e-6

	@testset "Pas de Cauchy" begin
		"# Pour la quadratique 1"

		@testset "g = 0" begin
			g = [0; 0]
			H = [7 0 ; 0 2]
			delta = 1
		    s, e = Pas_De_Cauchy(g,H,delta)
	    	@test (e == 0) && (isapprox(s,[0; 0],atol=tol_erreur))
	    end

		@testset "quad 2, non saturé" begin
	    	g = [6; 2]
		    H = [7 0 ; 0 2]
		    delta = 1
		    s, e = Pas_De_Cauchy(g,H,delta)
			sol = -(norm(g)^2/(g'*H*g))*g
		    @test (e == 1) && (isapprox(s,sol,atol=tol_erreur)) # sol = [-0.9230769230769234; -0.30769230769230776]
	    end
		@testset "quad 2, saturé" begin
	    	g = [6; 2]
		    H = [7 0 ; 0 2]
		    delta = 0.9
		    s, e = Pas_De_Cauchy(g,H,delta)
			sol = -(delta/norm(g))*g
		    @test (e == -1) && (isapprox(s,sol,atol=tol_erreur)) # sol = [-0.9230769230769234; -0.30769230769230776]
	    end
		@testset "quad 3, non saturé" begin
		    g = [-2; 1]
		    H = [-2 0 ; 0 10]
		    delta = 6
		    s, e = Pas_De_Cauchy(g,H,delta)
			sol = -(norm(g)^2/(g'*H*g))*g
			println("Cauchy 4 = ", sol)
		    @test (e == 1) && (isapprox(s,sol,atol=tol_erreur)) # sol = [-0.9230769230769234; -0.30769230769230776]
	    end
		@testset "quad 3, saturé" begin
		    g = [-2; 1]
		    H = [-2 0 ; 0 10]
		    delta = 5
		    s, e = Pas_De_Cauchy(g,H,delta)
			sol = -(delta/norm(g))*g
			println("Cauchy 5= ", sol)
		    @test (e == -1) && (isapprox(s,sol,atol=tol_erreur)) # sol = [-0.9230769230769234; -0.30769230769230776]
	    end
		@testset "quad 3, g'*H*g <0 saturé" begin
		    g = [3; 1]
		    H = [-2 0 ; 0 10]
		    delta = 5
		    s, e = Pas_De_Cauchy(g,H,delta)
			sol = -(delta/norm(g))*g
			println("Cauchy 6= ", sol)
		    @test (e == -1) && (isapprox(s,sol,atol=tol_erreur)) # sol = [-0.9230769230769234; -0.30769230769230776]
	    end
		@testset "quad 3, g'*H*g = 0 saturé" begin
		    g = [1,2]
		    H = [2 -1 ; 4 -2]
		    delta = 5
		    s, e = Pas_De_Cauchy(g,H,delta)
			sol = -(delta/norm(g))*g
			println("Cauchy 6= ", sol)
		    @test (e == -1) && (isapprox(s,sol,atol=tol_erreur)) # sol = [-0.9230769230769234; -0.30769230769230776]
	    end
	end
end
