compileJeu:
	javac -d bin/  -sourcepath ./src -classpath ./bin src/Controller/Jeu.java

compile :
	javac -d bin/ src/*.java

run :
	java -cp bin/ Controller/Jeu