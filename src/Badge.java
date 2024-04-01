public class Badge extends Objet {
    public Badge(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void examiner() {
        throw new UnsupportedOperationException("Unimplemented method 'examiner'");
    }
}
