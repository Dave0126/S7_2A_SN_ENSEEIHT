# Optinum - Méthodes numériques pour les problèmes d’optimisation

## Rendu
Le projet est à rendre sous Moodle *le vendredi 17 décembre à 18h00*. Vous rendrez un fichier compressé `votre nom.tgz` qui déarchivez contiendra un répertoire `votre nom` qui lui-même contiendra vos répertoire Optinum et TestOptinum. Votre notebook doit se trouver dans le sous répertoire `Optinum/src`.

## Instalation de julia et jupyter notebook sur vos machines

### Introduction
Dans les salles de TP, `julia` et `jupyter` sont déjà installés. 

### Installation de julia

Remarque : Il vaut mieux désinstaller, julia, atom, juno, conda et les packages associés si vous avez déjà fait des installations.

Pour installer `julia` sur Windows, MacOS ou Linux, le plus simple est de se rendre dans `julialang.org` dans la section des téléchargements. Vous pouvez télécharger `juliaPro` (julia+atom+juno).



### Installation du package IJulia et lancement d'un notebook, version Linux et MacOS

- Lancer julia
- Dans la fenêtre ce commande taper le caractère ], cela fait passer `julia` dans le mode gestion des packages et de l'environnenment (`v1.4` car sur ma machine c'est la version 1.4 de julia).  
`(@v1.4) pkg> add IJulia`
- Revenir en mode commande : taper la touche `backspace`
- Ce mettre dans un répertoire au dessus du répertoire Optinum dans lequel il y a un notebbok et lancer notebook  
`julia> notebook()`

Cela devrait fonctionner.

### Installation du package IJulia et lancement d'un notebook, version windows

Pour windows il faut considérer l'installation de Anaconda complet. Depuis le navigateur anaconda, on peut alors invoquer un jupyter_lab, et associer le kernel julia 1.5.2 associé à l'installation de julia-pro, et ensuite ouvrir n'importe quel fichier "ipynb".

### Les projets en julia
Le package [Optinum](https://github.com/mathn7/Optinum) concerne le projet d’Optimisation Numérique pour l’année 2020-2021 de l'École INP-ENSEEIHT. Pour récupérer le répertoire Optinum et toute la structuration il vous faut cloner ce dépot GitHub. Pour cela, ouvrer une fenêtre de commande et cloner ce dépot (il faut que git soit installé sur votre machine) : 

`git clone https://github.com/mathn7/Optinum.git`

Ce répertoire est un `Projet julia`, c'est-à-dire un ensemble de fichiers organisés de la façon suivante : 
 
- Un fichier `Project.toml`. C'est l'existence de ce fichier qui signale que l'ensemble des fichiers du répertoire définit un `projet julia`. Ce fichier contient des méta-données importantes sur le projet : 
  - un nom : name = "Optinum"
  - un identifiant : uuid = "289cfbba-6966-46b0-8dde-953cd55d6e8f"
  - les auteurs : authors = ["relmo <rachid.elmontassir@etu.enseeiht.fr>"]
  - un numero de version : version = "0.1.0"
  - une liste de packages externes que le projet peut utiliser
- Un fichier `Manifest.toml`qui est optionnel et qui contient des versions de chacune des dépendances listées dans `Project.toml`
- un fichier `src/Optinum.jl`qui est le fichier qui sera chargé par l'instruction `using` si le projet est utilisé comme dépendance d'un autre projet (ce qui n'est pas le cas ici).
- un répertoire `src` qui contient les sources `julia`
- un répertoire `docs` pour générer la documentation
- un répertoire `test`pour les tests unitaires
- un fichier `Markdown README.md` qui contient ce qui sera affiché sous un gestionaire du type `GitHub`.

### Soucis d'installation
Sous `linux` après installation du package `IJulia` losque vous lancer un notebook situé dans `Optinum, jupyter notebook` va automatiquement se mettre dans l'environnement d'Optinum. Le soucis est que dans cet environnement vous n'avez pas les bonnes versions des packages. Afin d'avoir ces bonnes versions il vous faut dans la fenêtre de commande `julia` lancé à partir du répertoire `Optinum` : 

- passez dans le mode  gestion des packages et de l'envirronenment (touche `]`)

- activer l'environnement `Optinum`  
(@v1.4) pkg> activate .

- installer les dépendances avec les bonnes versions  
(Optinum) pkg> instantiate

Ensuite cela devrait fonctionner et avec le notebook, il est inutile d'installer Mardown, ... car cela est déjà dans l'environnement. Ceci n'est à effectuer qu'une seule fois. Par contre si dans la fenêtre de commande de `julia` vous exécuter le fichier `test-TP-Projet-Optinum.jl` et que vous êtes dans l'envirronement général, il vous faut installer les packages nécessaires, mais si vous vous placez dans l'envirronement d'Optinum, cela devrait être ok.

Remarque : pour voir sous un notebook dans quel environnement vous êtes, vous pouvez exécuter la commande `Pkd.status()` :  
`julia> using Pkg`  
`julia > Pkg.status()`  
vous obtiendrez la liste des packages installés dans l'environnement.  
Vous pouvez donc comparer ceci avec la liste obtenu dans la fenêtre de commande de `julia` avant et après l'activation de l'environnement Optinum.

Remarque : C'est dans le mode gestion des packages et de l'environnenment :  

- que l'on peut passer dans l'environnement doc qui permet de générer la documentation
- que l'on peut effectuer les tests utitaires.


## Fichiers à modifier dans le cadre de ce projets

**Il ne faut en aucun cas modifier la structuration** de ce projet, ni les interfaces des fonctions.

Les fichiers `julia` que vous aurez à modifier se trouvent 

1. dans le répertoire `Optinum/src`. Ces fichiers sont : 
    1. `TP-Projet-Optinum.ipynb`: c'est un julia notebook dans lequel vous ferez toutes vos expérimentations numériques et répondrez aux questions posées. Ce fichier est à ouvrir avec `jupyter notebook`.
    2. Les fichiers `julia` qui codent les algorithmes demandés (voir le notebook).
2. dans le répertoire `TestOptinum/src` pour compléter les tests unitaires.

Enfin vous avez dans le répertoire `Optinum/julia` le fichier `notebook_Julia.pdf` d'introduction à `julia` et à l'utilisation des notebook et dans le répertoire `Optinum/julia/scr-julia` les notebook d'introduction au langage `julia`.

Remarque : vous pouvez travailler sur vos machine, mais vous pouvez aussi travailler sur les machines de l'enseeiht (en utilisant `x2go` ou `ssh`). Pour cela il vous faut exécuter le script  
`/usr/local/bin/install_ijulia`


## Organisation
Le projet d'optimisation numérique est un projet individuel.

###### Auteurs : O.Cots, J. Gergaud, S. Gratton, P. Matalon, C. Royer, D. Ruiz et E. Simon
