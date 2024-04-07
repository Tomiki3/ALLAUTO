package Model;
import java.util.ArrayList;

public class IA extends EntiteVivante{
    private ArrayList<String> Questions = new ArrayList<String>();
    private ArrayList<String> Reponses = new ArrayList<String>();
    private int size = 0;

    public IA(String nom, Boolean verrouille) {
        super(nom, verrouille);
    }

    public void setQuestRep(String quest, String rep) {
        Questions.add(quest);
        Reponses.add(rep);
        size++;
    }

    public int getSize() {
        return this.size;
    }

    public String getQuestion(int i) {
        return Questions.get(i);
    }

    public String getReponse(int i) {
        return Reponses.get(i);
    }
}
