package Model;

public class Digicode extends EntiteVivante {
    private Porte porte;
    private String secretCode;

    public Digicode(String nom, Boolean verrouille, Porte porte, String code) {
        super(nom, false);
        this.porte = porte;
        this.secretCode = code;
    }

    public Porte getPorte() {
        return porte;
    }

    public String getSecretCode() {
        return secretCode;
    }
}
