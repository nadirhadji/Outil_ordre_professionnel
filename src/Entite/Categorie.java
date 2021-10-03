package Entite;

public enum Categorie {

    COURS("cours"),
    ATELIER("atelier"),
    SEMINAIRE("séminaire"),
    COLLOQUE("colloque"),
    CONFERENCE("conférence"),
    LECTURE_DIRIGEE("lecture dirigée"),
    PRESENTATION("présentation"),
    GROUPE_DE_DISCUSSION("groupe de discussion"),
    PROJET_DE_RECHERCHE("projet de recherche"),
    REDACTION_PROFESSIONNELLE("rédaction professionnelle");

    private String categorie;

    Categorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return categorie;
    }
}
