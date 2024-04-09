compileJeu:
	javac -d bin/  -sourcepath ./src -classpath ./bin src/Controller/Jeu.java

compile :
	javac -d bin/ src/*

run : compileJeu
	java -cp bin/ Controller/Jeu

play : 
	java -cp bin/ Controller/Jeu