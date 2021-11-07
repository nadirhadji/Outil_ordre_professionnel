package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class ServiceValidationGeologue implements InterfaceVerification {

    private int heuresActiviteDeGroupe;
    private int heuresPresentation;
    private int heuresGroupeDeDiscussion;
    private int heuresProjetDeRecherche;
    private int heuresRedactionProfessionel;
    private int heuresCours;


    public ServiceValidationGeologue() {
        this.heuresActiviteDeGroupe = 0;
        this.heuresPresentation = 0;
        this.heuresGroupeDeDiscussion = 0;
        this.heuresProjetDeRecherche = 0;
        this.heuresRedactionProfessionel = 0;
        this.heuresCours = 0;
    }

    @Override
    public void verifier(Declaration general, Reponse reponse ) {
        if ( verifierCycleGeologue(general,reponse) ) {
            verifierActivites(general,reponse);
            verifierNombreHeuresPourActiviteDeGroupe(general,reponse);
            verifierMinimumHeureParGroupeDeCategorie();
            verifierNombreHeuresTotaleDansDeclaration(reponse);
        }
    }

    /*############################### Service.Verification Cycle ##################################*/

    public boolean verifierCycleGeologue(Declaration general, Reponse reponse ) {
        if ( ! estCycleGeologueValide(general.obtenirCycle()) ) {
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageErreurCycleInvalide());
            return false;
        }
        return true;
    }

    public boolean estCycleGeologueValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISEE_POUR_GEOLOGUE);
    }

    /*##################### Service.Verification des activités #######################*/

    private void verifierActivites(Declaration general, Reponse reponse) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite();

        for (Activite activite : general.obtenirActivites() ) {
            serviceValidationActivite.verifierActivite(activite, reponse);
            if ( ! activite.estIgnoree() )
                incrementerCompteurHeures(activite);
        }
    }

    private void incrementerCompteurHeures(Activite activite) {
        String categorie = activite.obtenirCategorie();
        int nombreHeure = activite.obtenirHeures();
        verifierSiCategorieDeGroupe(categorie,nombreHeure);
        verifierSiCategoriePresentation(categorie,nombreHeure);
        verifierSiCategorieGroupeDeDiscussion(categorie,nombreHeure);
        verifierSiCategorieProjetDeRecherche(categorie,nombreHeure);
        verifierSiCategorieRedactionProfessionnelle(categorie,nombreHeure);
    }

    public void verifierSiCategorieDeGroupe(String categorie, int nombreHeure) {
        List<String> liste = obtenirListeDesActivitesDeGroupe();
        if ( liste.contains(categorie) )
            this.heuresActiviteDeGroupe += nombreHeure;
    }

    private List<String> obtenirListeDesActivitesDeGroupe() {
        List<String> activateDeGroup = new ArrayList<String>();
        activateDeGroup.add(Categorie.COURS.toString());
        activateDeGroup.add(Categorie.ATELIER.toString());
        activateDeGroup.add(Categorie.SEMINAIRE.toString());
        activateDeGroup.add(Categorie.COLLOQUE.toString());
        activateDeGroup.add(Categorie.CONFERENCE.toString());
        activateDeGroup.add(Categorie.LECTURE_DIRIGEE.toString());
        return activateDeGroup;
    }

    public void verifierSiCategoriePresentation(String categorie,
                                                int nombreHeure) {
        if( categorie.equals(Categorie.PRESENTATION.toString()))
            this.heuresPresentation += nombreHeure;
    }

    public void verifierSiCategorieGroupeDeDiscussion(String categorie,
                                                      int nombreHeure) {
        if ( categorie.equals(Categorie.GROUPE_DE_DISCUSSION.toString()))
            this.heuresGroupeDeDiscussion += nombreHeure;
    }

    public void verifierSiCategorieProjetDeRecherche(String categorie,
                                                     int nombreHeure) {
        if ( categorie.equals(Categorie.PROJET_DE_RECHERCHE.toString()))
            this.heuresProjetDeRecherche += nombreHeure;
    }

    public void verifierSiCategorieRedactionProfessionnelle(String categorie,
                                                            int nombreHeure) {
        if ( categorie.equals(Categorie.REDACTION_PROFESSIONNELLE.toString()))
            this.heuresRedactionProfessionel += nombreHeure;
    }

    /*########## Service.Verification du nombre minimal pour activite de groupe ######*/

    private void verifierNombreHeuresPourActiviteDeGroupe(Declaration general,
                                                          Reponse reponse) {
        if ( ! estNombreHeuresPourActiviteDeGroupeValide(general) )
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageErreurHeuresDansActiviteDeGroupe());
    }

    private boolean estNombreHeuresPourActiviteDeGroupeValide(Declaration general) {
        return verifierTotalHeurePourActivite(general,heuresActiviteDeGroupe,
                Constantes.MINIMUM_HEURE_ACTIVITE_DE_GROUPE);
    }

    private boolean verifierTotalHeurePourActivite(Declaration general, int total,
                                                   int minimum) {
        if ( total >= minimum )
            return true;
        else
            return false;
    }

    /*############# Service.Verification du nombre minimum par Activite pour Geologue  ############*/

    private void verifierMinimumHeureParGroupeDeCategorie() {
        verifierMinimumHeureGroupeDeDiscussion();
        verifierMinimumHeureProjetDeRecherche();
        verifierMinimumHeureCours();
    }

    private void verifierMinimumHeureGroupeDeDiscussion() {
        if ( heuresGroupeDeDiscussion <
                Constantes.MINIMUM_HEURE_GROUPE_DE_DISCUSSION_GEOLOGUE){
            //TODO message d'erreur pour minimum d'heure
        }

    }

    private void verifierMinimumHeureProjetDeRecherche() {
        if( heuresProjetDeRecherche <
                Constantes.MINIMUM_HEURE_PROJET_DE_RECHERCHE_GEOLOGUE){
            //TODO message d'erreur pour minimum d'heure
        }

    }
    private void verifierMinimumHeureCours() {
        if( heuresCours <
                Constantes.MINIMUM_HEURE_COURS_GEOLOGUE){
            //TODO message d'erreur pour minimum d'heure
        }

    }

    /*############# Service.Verification du nombre totale d'heures ###################*/

    private void verifierNombreHeuresTotaleDansDeclaration( Reponse reponse ) {
        int nombreHeuresManquante = obtenirNombreHeuresManquante();
        if( nombreHeuresManquante > 0 ) {
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    private int obtenirNombreHeuresManquante() {
        int total = obtenirNombreTotalHeures();
        if ( total >= Constantes.MINIMUM_HEURE_POUR_UNE_DECLARATION_GEOLOGUE)
            return 0;

        if ( total  < Constantes.MINIMUM_HEURE_POUR_UNE_DECLARATION_GEOLOGUE)
            return Constantes.MINIMUM_HEURE_POUR_UNE_DECLARATION_GEOLOGUE - (
                    total );
        else
            return 0;
    }

    private int obtenirNombreTotalHeures() {
        return heuresActiviteDeGroupe + heuresPresentation +
                heuresGroupeDeDiscussion + heuresProjetDeRecherche +
                heuresRedactionProfessionel;
    }




    @Override
    public void verifier(Declaration declaration, Reponse reponse) {

    }
}
