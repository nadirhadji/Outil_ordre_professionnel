package Entite;

public class Declaration {

    private String numeroDePermis;
    private String cycle;
    private int nombreHeuresTransfere;
    private Activite activites;

    public Declaration( DeclarationJSON declarationJSON) {
        //TODO
    }

    public String obtenirNumeroDePermis() {
        return numeroDePermis;
    }

    public String obtenirCycle() {
        return cycle;
    }

    public int obtenirNombreHeuresTransfere() {
        return nombreHeuresTransfere;
    }

    public Activite obtenirActivites() {
        return activites;
    }
}
