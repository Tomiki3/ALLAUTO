compileJeu:
	javac -d bin/  -sourcepath ./src -classpath ./bin src/Jeu.java

run : compileJeu
	java -cp bin/ Jeu

play : 
	java -cp bin/ Jeu

clean :
	rm -r ./bin