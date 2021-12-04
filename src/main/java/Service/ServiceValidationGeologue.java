package Service;

import Entite.*;
import Utils.ConstantesGeologue;
import java.util.ArrayList;
import java.util.List;

public class ServiceValidationGeologue implements InterfaceVerification {

    ServiceRedondanceDate serviceRedondanceDate;
    ServiceValidationActivite serviceValidationActivite;
    HeuresGeologue heuresGeologue;

    public ServiceValidationGeologue(ServiceRedondanceDate serviceRedondanceDate,
                                     ServiceValidationActivite serviceValidationActivite ) {
        this.serviceRedondanceDate = serviceRedondanceDate;
        this.serviceValidationActivite = serviceValidationActivite;
        heuresGeologue = new HeuresGeologue();
    }

    public ServiceValidationGeologue(int autre, int group, int projet, int cours) {
        heuresGeologue = new HeuresGeologue(autre,group,projet,cours);
    }

    public void verifier(Declaration declaration) {
        verifiationGeneral(declaration);
        verificationDesActivite(declaration);
        verifierSpecifiqueOrdre(declaration);
    }

    @Override
    public void verifiationGeneral(Declaration declaration) {
        if (verifierCycleGeologue(declaration)) {
            ServiceValidationNumeroDePermis.geologueOUpodologue(declaration.obtenirNumeroDePermis());
        }
    }

    @Override
    public void verificationDesActivite(Declaration declaration) {
        verifierActivites(declaration);
    }

    @Override
    public void verifierSpecifiqueOrdre(Declaration declaration) {
        verifierNombreHeuresTotaleDansDeclaration();
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
        for (Activite activite : general.obtenirActivites() ) {
            serviceRedondanceDate.estUneDateDisponible(activite);
            serviceValidationActivite.verifierActivite(activite);
            if ( ! activite.estIgnoree() ) {
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
            this.heuresGeologue.incrementerHeuresAutreActiviteGeologue(nombreHeure);
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
            this.heuresGeologue.incrementerHeuresGroupeDeDiscussion(nombreHeure);
    }

    public void verifierSiCategorieProjetDeRecherche (String categorie,
                                                      int nombreHeure) {
        if (categorie.equals(Categorie.PROJET_DE_RECHERCHE.toString()))
            this.heuresGeologue.incrementerHeuresProjetDeRecherche(nombreHeure);
    }

    public void verifierSiCategorieCours (String categorie, int nombreHeure){
        if (categorie.equals(Categorie.COURS.toString()))
            this.heuresGeologue.incrementerHeuresCours(nombreHeure);
    }

    /*############# Service.Verification du nombre minimum par Activite pour Geologue  ############*/
    public void verifierMinimumHeureParGroupeDeCategorie () {
        verifierMinimumHeureGroupeDeDiscussion();
        verifierMinimumHeureProjetDeRecherche();
        verifierMinimumHeureCours();
    }

    public void verifierMinimumHeureGroupeDeDiscussion () {
        int heuresGroupeDeDiscussion = heuresGeologue.obtenirHeuresGroupeDeDiscussion();
        if (heuresGroupeDeDiscussion < ConstantesGeologue.MINIMUM_HEURE_GROUPE_DE_DISCUSSION_GEOLOGUE) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresMinimumPourGroupeDeDiscussionGeo());
        }
    }

    public void verifierMinimumHeureProjetDeRecherche () {
        int heuresProjetDeRecherche = heuresGeologue.obtenirHeuresProjetDeRecherche();
        if (heuresProjetDeRecherche < ConstantesGeologue.MINIMUM_HEURE_PROJET_DE_RECHERCHE_GEOLOGUE) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresMinimumPourProjetGeo());
        }
    }

    public void verifierMinimumHeureCours () {
        int heuresCours = heuresGeologue.obtenirHeuresCours();
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
        return obtenirAutreActiviteHeures() + obtenirGroupeDiscussionHeures() +
                obtenirProjetRechercheHeures() + obtenirCoursHeures();
    }

    public int obtenirAutreActiviteHeures () {
        return this.heuresGeologue.obtenirHeuresAutreActiviteGeologue();
    }

    public int obtenirCoursHeures () {
        return this.heuresGeologue.obtenirHeuresCours();
    }

    public int obtenirProjetRechercheHeures () {
        return this.heuresGeologue.obtenirHeuresProjetDeRecherche();
    }

    public int obtenirGroupeDiscussionHeures () {
        return this.heuresGeologue.obtenirHeuresGroupeDeDiscussion();
    }
}