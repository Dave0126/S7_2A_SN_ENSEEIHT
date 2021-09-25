Installation de l'environnement sur vos machines personnelles

Pour Linux :
suivez la section 1.2, page 4 du sujet de TP.

Pour Mac :
suivez les étapes 1 à 3 ici : https://github.com/erikmd/tapfa-init.el
puis faites un mix avec le sujet de TP pour l'étape 4, soit :
% opam init --auto-setup --yes --compiler=ocaml-base-compiler.4.12.0
% eval $(opam env)
% opam install menhir dune utop merlin ppx_inline_test ppx_expect graphics

Pour Windows :
suivez les étapes 1 à 7 ici : https://github.com/erikmd/tapfa-init.el
puis faites un mix avec le sujet de TP pour l'étape 8, soit :
% opam init --disable-sandboxing --auto-setup --yes --compiler=ocaml-base-compiler.4.12.0
% eval $(opam env)
% opam install menhir dune utop merlin ppx_inline_test ppx_expect
% sudo apt install libx11-dev
% opam install graphics


------------------------------------------------------------------------------------------------------
在您的个人机器上安装环境

对于 Linux：
遵循实验室主题的第 4 页第 1.2 节.

对于 Mac：
按照此处的步骤 1 到 3：https : //github.com/erikmd/tapfa-init.el
然后与步骤 4 的 TP 主题混合，即：
% opam init --auto- setup --yes --编译器 = ocaml-base-compiler.4.12.0
% eval $ (opam env)
% opam install menhir dune utop merlin ppx_inline_test ppx_expect graphics

对于 Windows：
按照步骤 1 到 7 在这里：https://github.com/erikmd/tapfa-初始化文件
然后与第 8 步的实验对象混合，或者：
% opam init --disable-sandboxing --auto-setup --yes --compiler = ocaml-base-compiler.4.12.0
% eval $ (opam env)
% opam install menhir dune utop merlin ppx_inline_test ppx_expect
% sudo apt install libx11-dev
% opam install graphics