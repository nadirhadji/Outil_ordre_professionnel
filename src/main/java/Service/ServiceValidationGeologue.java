package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Declaration;
import Entite.Reponse;
import Utils.ConstantesGeologue;

import java.util.ArrayList;
import java.util.List;

public class ServiceValidationGeologue implements InterfaceVerification {

    private int heuresAutreActiviteGeologue;
    private int heuresGroupeDeDiscussion;
    private int heuresProjetDeRecherche;
    private int heuresCours;

    public ServiceValidationGeologue() {
        this.heuresAutreActiviteGeologue = 0;
        this.heuresGroupeDeDiscussion = 0;
        this.heuresProjetDeRecherche = 0;
        this.heuresCours = 0;
    }
    public ServiceValidationGeologue(int autre, int group, int projet, int cours) {
        this.heuresAutreActiviteGeologue = autre;
        this.heuresGroupeDeDiscussion = group;
        this.heuresProjetDeRecherche = projet;
        this.heuresCours = cours;
    }

    @Override
    public void verifier(Declaration general) {
        if (verifierCycleGeologue(general)) {
            verifierActivites(general);
            verifierNombreHeuresTotaleDansDeclaration();
        }
    }

    /*############################### Service.Verification Cycle ##################################*/

    public boolean verifierCycleGeologue(Declaration general) {
        if (!estCycleGeologueValide(general.obtenirCycle())) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurCycleInvalideGeo());
            return false;
        }
        return true;
    }

    public boolean estCycleGeologueValide(String cycle) {
        return cycle.equals(ConstantesGeologue.CYCLE_GEOLOGUE);
    }

    /*##################### Service.Verification des activités #######################*/

    public void verifierActivites(Declaration general) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite(general.obtenirOrdre(), general.obtenirCycle());

        for (Activite activite : general.obtenirActivites()) {
            serviceValidationActivite.verifierActivite(activite);
            if (!activite.estIgnoree()) {
                incrementerCompteurHeures(activite);
            }
        }
    }

    public void incrementerCompteurHeures(Activite activite) {
        String categorie = activite.obtenirCategorie();
        int nombreHeure = activite.obtenirHeures();
        verifierSiCategorieAutre(categorie, nombreHeure);
        verifierSiCategorieCours(categorie, nombreHeure);
        verifierSiCategorieGroupeDeDiscussion(categorie, nombreHeure);
        verifierSiCategorieProjetDeRecherche(categorie, nombreHeure);
    }

    public void verifierSiCategorieAutre(String categorie, int nombreHeure) {
        List<String> liste = obtenirListeDesActivitesDeGroupe();
        if (liste.contains(categorie))
            this.heuresAutreActiviteGeologue += nombreHeure;
    }

    private List<String> obtenirListeDesActivitesDeGroupe() {
        List<String> autreActiviteGeologue = new ArrayList<String>();
        autreActiviteGeologue.add(Categorie.PRESENTATION.toString());
        autreActiviteGeologue.add(Categorie.ATELIER.toString());
        autreActiviteGeologue.add(Categorie.SEMINAIRE.toString());
        autreActiviteGeologue.add(Categorie.COLLOQUE.toString());
        autreActiviteGeologue.add(Categorie.CONFERENCE.toString());
        autreActiviteGeologue.add(Categorie.LECTURE_DIRIGEE.toString());
        autreActiviteGeologue.add(Categorie.REDACTION_PROFESSIONNELLE.toString());
        return autreActiviteGeologue;
    }

    public void verifierSiCategorieGroupeDeDiscussion(String categorie,
                                                      int nombreHeure) {
        if (categorie.equals(Categorie.GROUPE_DE_DISCUSSION.toString()))
            this.heuresGroupeDeDiscussion += nombreHeure;
    }


    public void verifierSiCategorieProjetDeRecherche (String categorie,
                                                      int nombreHeure) {
        if (categorie.equals(Categorie.PROJET_DE_RECHERCHE.toString()))
            this.heuresProjetDeRecherche += nombreHeure;
    }

    public void verifierSiCategorieCours (String categorie, int nombreHeure){
        if (categorie.equals(Categorie.COURS.toString()))
            this.heuresCours += nombreHeure;
    }

    /*############# Service.Verification du nombre minimum par Activite pour Geologue  ############*/

    public void verifierMinimumHeureParGroupeDeCategorie () {
        verifierMinimumHeureGroupeDeDiscussion();
        verifierMinimumHeureProjetDeRecherche();
        verifierMinimumHeureCours();
    }

    public void verifierMinimumHeureGroupeDeDiscussion () {
        if (heuresGroupeDeDiscussion < ConstantesGeologue.MINIMUM_HEURE_GROUPE_DE_DISCUSSION_GEOLOGUE) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresMinimumPourGroupeDeDiscussionGeo());
        }
    }

    public void verifierMinimumHeureProjetDeRecherche () {
        if (heuresProjetDeRecherche < ConstantesGeologue.MINIMUM_HEURE_PROJET_DE_RECHERCHE_GEOLOGUE) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresMinimumPourProjetGeo());
        }
    }

    public void verifierMinimumHeureCours () {
        if (heuresCours < ConstantesGeologue.MINIMUM_HEURE_COURS_GEOLOGUE) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresMinimumPourCoursGeo());
        }
    }

    /*############# Service.Verification du nombre totale d'heures ###################*/
    public void verifierNombreHeuresTotaleDansDeclaration () {
        int nombreHeuresManquante = obtenirNombreHeuresManquante();
        if (nombreHeuresManquante > 0) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    public int obtenirNombreHeuresManquante () {
        int total = obtenirNombreTotalHeures();
        verifierMinimumHeureParGroupeDeCategorie();

        if (total >= ConstantesGeologue.MINIMUM_HEURE_POUR_UNE_DECLARATION_GEOLOGUE)
            return 0;
        else
            return ConstantesGeologue.MINIMUM_HEURE_POUR_UNE_DECLARATION_GEOLOGUE - total;
    }

    public int obtenirNombreTotalHeures () {
        return heuresAutreActiviteGeologue + heuresGroupeDeDiscussion +
                heuresProjetDeRecherche + heuresCours;
    }

    public int obtenirAutreActiviteHeures () {
        return heuresAutreActiviteGeologue;
    }

    public int obtenirCoursHeures () {
        return heuresCours;
    }
    public int obtenirProjetRechercheHeures () {
        return heuresProjetDeRecherche;
    }
    public int obtenirGroupeDiscussionHeures () {
        return heuresGroupeDeDiscussion;
    }


}