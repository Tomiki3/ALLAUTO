package Model;

public class IA extends EntiteVivante{
    private Arraylist<String> Questions = new Arraylist<String>;
    private Arraylist<String> Reponses = new Arraylist<String>;
    private int size = 0;

    public IA(String nom, Boolean verrouille) {
        super(nom, verrouille);
    }

    public void setQuestRep(String quest, String rep) {
        Questions.add(quest);
        Reponses.add(rep);
        size++;
    }

    public void getSize() {
        return size;
    }
}
