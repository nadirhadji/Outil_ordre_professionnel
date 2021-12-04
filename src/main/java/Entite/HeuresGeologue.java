package Entite;

public class HeuresGeologue {

    private int heuresAutreActiviteGeologue;
    private int heuresGroupeDeDiscussion;
    private int heuresProjetDeRecherche;
    private int heuresCours;

    public HeuresGeologue() {
        this.heuresAutreActiviteGeologue = 0;
        this.heuresGroupeDeDiscussion = 0;
        this.heuresProjetDeRecherche = 0;
        this.heuresCours = 0;
    }

    public HeuresGeologue(int heuresAutreActiviteGeologue, int heuresGroupeDeDiscussion, int heuresProjetDeRecherche, int heuresCours) {
        this.heuresAutreActiviteGeologue = heuresAutreActiviteGeologue;
        this.heuresGroupeDeDiscussion = heuresGroupeDeDiscussion;
        this.heuresProjetDeRecherche = heuresProjetDeRecherche;
        this.heuresCours = heuresCours;
    }

    public int obtenirHeuresAutreActiviteGeologue() {
        return heuresAutreActiviteGeologue;
    }

    public void incrementerHeuresAutreActiviteGeologue(int heuresAutreActiviteGeologue) {
        this.heuresAutreActiviteGeologue += heuresAutreActiviteGeologue;
    }

    public int obtenirHeuresGroupeDeDiscussion() {
        return heuresGroupeDeDiscussion;
    }

    public void incrementerHeuresGroupeDeDiscussion(int heuresGroupeDeDiscussion) {
        this.heuresGroupeDeDiscussion += heuresGroupeDeDiscussion;
    }

    public int obtenirHeuresProjetDeRecherche() {
        return heuresProjetDeRecherche;
    }

    public void incrementerHeuresProjetDeRecherche(int heuresProjetDeRecherche) {
        this.heuresProjetDeRecherche += heuresProjetDeRecherche;
    }

    public int obtenirHeuresCours() {
        return heuresCours;
    }

    public void incrementerHeuresCours(int heuresCours) {
        this.heuresCours += heuresCours;
    }
}
