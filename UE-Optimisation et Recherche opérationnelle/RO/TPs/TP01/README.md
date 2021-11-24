/* Installer GLPK sur Windows (ou sur linux si le script n'a pas fonctionné)Fichier  */

mkdir solveurGLPK

cd solveurGLPK

mkdir sources

cd sources



wget ftp://ftp.gnu.org/gnu/glpk/glpk-5.0.tar.gz

tar -xzvf glpk-5.0.tar.gz

cd glpk-5.0



./configure --disable-shared

make
#make --jobs=4

make check



cd ../..

mkdir executables

cp sources/glpk-5.0/examples/glpsol executables/. 

cp sources/glpk-5.0/doc/glpk.pdf executables/. 

cp sources/glpk-5.0/doc/gmpl.pdf executables/.  


cd executables 

./glpsol --help



