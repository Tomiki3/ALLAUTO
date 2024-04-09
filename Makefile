compileJeu:
	javac -d bin/  -sourcepath ./src -classpath ./bin src/Jeu.java

# compile :
# 	javac -d bin/ src/*

run : compileJeu
	java -cp bin/ Jeu

play : 
	java -cp bin/ Jeu

clean :
	rm -r ./bin