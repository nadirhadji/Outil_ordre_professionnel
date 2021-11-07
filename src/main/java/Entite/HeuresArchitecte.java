package Entite;

/**
 * Cette classe va permettre de stocker les nombres d'heures suivant la
 * declaration d'un architecte.
 */
public class HeuresArchitecte {

    private int minimumAvantValidation;
    private int activiteDeGroupe;
    private int presentation;
    private int groupeDeDiscussion;
    private int projetDeRecherche;
    private int redactionProfessionel;

    public HeuresArchitecte() {
        this.minimumAvantValidation = 0;
        this.activiteDeGroupe = 0;
        this.presentation = 0;
        this.groupeDeDiscussion = 0;
        this.projetDeRecherche = 0;
        this.redactionProfessionel = 0;
    }

    public int obtenirActiviteDeGroupe() {
        return activiteDeGroupe;
    }

    public int obtenirPresentation() {
        return presentation;
    }

    public int obtenirGroupeDeDiscussion() {
        return groupeDeDiscussion;
    }

    public int obtenirProjetDeRecherche() {
        return projetDeRecherche;
    }

    public int obtenirRedactionProfessionel() {
        return redactionProfessionel;
    }

    public int obtenirMinimumAvantValidation() {
        return minimumAvantValidation;
    }

    public void incrementerActiviteDeGroupe(int heuresActiviteDeGroupe) {
        this.activiteDeGroupe += heuresActiviteDeGroupe;
    }

    public void incrementerPresentation(int heuresPresentation) {
        this.presentation += heuresPresentation;
    }

    public void incrementerGroupeDeDiscussion(int heuresGroupeDeDiscussion) {
        this.groupeDeDiscussion += heuresGroupeDeDiscussion;
    }

    public void incrementerProjetDeRecherche(int heuresProjetDeRecherche) {
        this.projetDeRecherche += heuresProjetDeRecherche;
    }

    public void incrementerRedactionProfessionel(int heuresRedactionProfessionel) {
        this.redactionProfessionel += heuresRedactionProfessionel;
    }

    public void enregistrerActiviteDeGroupe(int heuresActiviteDeGroupe) {
        this.activiteDeGroupe = heuresActiviteDeGroupe;
    }

    public void enregistrerPresentation(int heuresPresentation) {
        this.presentation = heuresPresentation;
    }

    public void enregistrerGroupeDeDiscussion(int heuresGroupeDeDiscussion) {
        this.groupeDeDiscussion = heuresGroupeDeDiscussion;
    }

    public void enregistrerProjetDeRecherche(int heuresProjetDeRecherche) {
        this.projetDeRecherche = heuresProjetDeRecherche;
    }

    public void enregistrerRedactionProfessionel(int heuresRedactionProfessionel) {
        this.redactionProfessionel = heuresRedactionProfessionel;
    }

    public void enregistrerMinimumAvantValidation(int nombreMinimumHeuresAvantValidation) {
        this.minimumAvantValidation = nombreMinimumHeuresAvantValidation;
    }


}
