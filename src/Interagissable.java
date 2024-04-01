public interface Interagissable {
    public void interagir(Joueur moi);// Joueur en paramètre car une action peut potentiellement modifier l'état du joueur (ex : Fenêtre)
    public void arreter();
}