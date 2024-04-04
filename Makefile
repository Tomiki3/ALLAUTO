compileJeu:
	javac -d bin/ -cp src/ src/Jeu.java

compile :
	javac -d bin/ src/*.java

run :
	java -cp bin/ Jeu