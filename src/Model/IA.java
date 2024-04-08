package Model;
import java.util.ArrayList;

public class IA extends EntiteVivante{
    private ArrayList<String> questions;
    private ArrayList<String> reponses;
    private int size = 0;

    public IA(String nom, Boolean verrouille) {
        super(nom, verrouille);
        this.questions = new ArrayList<String>();
        this.reponses = new ArrayList<String>();
    }

    // permet d'ajouter une question et une réponse à l'IA (utilisée dans l'initialisation du jeu)
    public void setQuestRep(String quest, String rep) {
        questions.add(quest);
        reponses.add(rep);
        size++;
    }

    public int getSize() {
        return this.size;
    }

    public String getQuestion(int i) {
        return questions.get(i);
    }

    public String getReponse(int i) {
        return reponses.get(i);
    }
}
