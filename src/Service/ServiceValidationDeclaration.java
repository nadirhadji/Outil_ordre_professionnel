package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.General;
import Entite.Reponse;
import Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class ServiceValidationDeclaration {

    private int heuresActiviteDeGroupe;
    private int heuresPresentation;
    private int heuresGroupeDeDiscussion;
    private int heuresProjetDeRecherche;
    private int heuresRedactionProfessionel;

    public ServiceValidationDeclaration() {
        this.heuresActiviteDeGroupe = 0;
        this.heuresPresentation = 0;
        this.heuresGroupeDeDiscussion = 0;
        this.heuresProjetDeRecherche = 0;
        this.heuresRedactionProfessionel = 0;
    }

    public void verifierDeclaration(General general, Reponse reponse ) {
        if ( verifierCycle(general,reponse) ) {
            verifierHeureTransfere(general,reponse);
            verifierActivites(general,reponse);
            verifierNombreHeuresPourActiviteDeGroupe(general,reponse);
            verifierMaximumHeureParGroupeDeCategorie();
            verifierNombreHeuresTotaleDansDeclaration(general.obtenirHeurestransfere(),reponse);
        }
    }

    /*############################### Verification Cycle ##################################*/

    public boolean verifierCycle( General general, Reponse reponse ) {
        if ( ! estCycleValide(general.obtenirCycle()) ) {
            reponse.ajouterMessageErreur(ServiceMessages.messageErreurCycleInvalide());
            return false;
        }
        return true;
    }

    public boolean estCycleValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISEE);
    }

    /*######################### Verification Heures Transfere ############################*/

    private void verifierHeureTransfere(General general, Reponse reponse) {
        verifierSiHeuresTransfereSuperieurA7(general,reponse);
        verifierSiHeuresTransfereNegatif(general,reponse);
    }

    private void verifierSiHeuresTransfereSuperieurA7 ( General general , Reponse reponse) {
        if ( general.obtenirHeurestransfere() > Constantes.NOMBRE_HEURES_MAXIMALE_A_TRANSFERE) {
            general.modifierNombreHeuresTransfereA7();
            reponse.ajouterMessageInformation(ServiceMessages.messageInfosHeuresTransfereSuperieurA7());
        }
    }

    private void verifierSiHeuresTransfereNegatif(General general , Reponse reponse ) {
        if ( general.obtenirHeurestransfere() < 0 ) {
            general.modifierNombreHeuresTransfereA0();
            reponse.ajouterMessageErreur(ServiceMessages.messageErreurHeuresTransfereInferieurA0());
        }
    }

    /*######################### Verification des activités ############################*/

    private void verifierActivites(General general, Reponse reponse) {
        ServiceValidationActivite serviceValidationActivite = new ServiceValidationActivite();

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

    public void verifierSiCategoriePresentation(String categorie, int nombreHeure) {
        if( categorie.equals(Categorie.PRESENTATION.toString()))
            this.heuresPresentation += nombreHeure;
    }

    public void verifierSiCategorieGroupeDeDiscussion(String categorie, int nombreHeure) {
        if ( categorie.equals(Categorie.GROUPE_DE_DISCUSSION.toString()))
            this.heuresGroupeDeDiscussion += nombreHeure;
    }

    public void verifierSiCategorieProjetDeRecherche(String categorie, int nombreHeure) {
        if ( categorie.equals(Categorie.PROJET_DE_RECHERCHE.toString()))
            this.heuresProjetDeRecherche += nombreHeure;
    }

    public void verifierSiCategorieRedactionProfessionnelle(String categorie, int nombreHeure) {
        if ( categorie.equals(Categorie.REDACTION_PROFESSIONNELLE.toString()))
            this.heuresRedactionProfessionel += nombreHeure;
    }

    /*########## Verification du nombre minimal pour activite de groupe ####################*/

    private void verifierNombreHeuresPourActiviteDeGroupe(General general, Reponse reponse) {
        if ( ! estNombreHeuresPourActiviteDeGroupeValide(general) )
            reponse.ajouterMessageErreur(ServiceMessages.messageErreurHeuresDansActiviteDeGroupe());
    }

    private boolean estNombreHeuresPourActiviteDeGroupeValide(General general) {
        return verifierTotalHeurePourActivite(general,heuresActiviteDeGroupe,
                Constantes.MINIMUM_HEURE_ACTIVITE_DE_GROUPE);
    }

    private boolean verifierTotalHeurePourActivite(General general, int total, int minimum) {
        if ( total >= minimum )
            return true;
        else
            return verifierSiNombreHeureTransfereSuffisantePourValider(general, total, minimum);
    }

    private boolean verifierSiNombreHeureTransfereSuffisantePourValider(General general , int total, int minimum) {
        if ( total + general.obtenirHeurestransfere() >= minimum ) {
            int nombreASoustraire = minimum - total;
            general.soustraireAuNombreHeuresTransfere(nombreASoustraire);
            return true;
        }
        return false;
    }

    /*#################### Verification du nombre maximale par Activite  #######################*/

    private void verifierMaximumHeureParGroupeDeCategorie() {
        verifierMaximumHeureDePresentation();
        verifierMaximumHeureGroupeDeDiscussion();
        verifierMaximumHeureProjetDeRecherche();
        verifierMaximumHeuresHeuresRedactionProfessionel();

    }

    private void verifierMaximumHeureDePresentation() {
        if( heuresPresentation > Constantes.MAXIMUM_HEURE_PRESENTATION)
            this.heuresPresentation = Constantes.MAXIMUM_HEURE_PRESENTATION;
    }

    private void verifierMaximumHeureGroupeDeDiscussion() {
        if ( heuresGroupeDeDiscussion > Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION)
            this.heuresGroupeDeDiscussion = Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION;
    }

    private void verifierMaximumHeureProjetDeRecherche() {
        if( heuresProjetDeRecherche > Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER)
            this.heuresProjetDeRecherche = Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER;
    }

    private void verifierMaximumHeuresHeuresRedactionProfessionel() {
        if( heuresRedactionProfessionel > Constantes.MAXIMUM_HEURE_REDACTION)
            this.heuresRedactionProfessionel = Constantes.MAXIMUM_HEURE_REDACTION;
    }

    /*#################### Verification du nombre totale d'heures #######################*/

    private void verifierNombreHeuresTotaleDansDeclaration(int heuresTransfere, Reponse reponse ) {
        int nombreHeuresManquante = obtenirNombreHeuresManquante(heuresTransfere);
        if( nombreHeuresManquante > 0 ) {
            reponse.ajouterMessageErreur(ServiceMessages.messageNombreHeuresTotalMoinsDe40(nombreHeuresManquante));
        }
    }

    private int obtenirNombreHeuresManquante(int heuresTransfere) {
        int total = obtenirNombreTotalHeures();
        if ( total >= Constantes.MINIMUM_HEURE_POUR_UNE_DECLARATION)
            return 0;

        if ( (total + heuresTransfere) < Constantes.MINIMUM_HEURE_POUR_UNE_DECLARATION)
            return Constantes.MINIMUM_HEURE_POUR_UNE_DECLARATION - (total + heuresTransfere);
        else
            return 0;
    }

    private int obtenirNombreTotalHeures() {
        return heuresActiviteDeGroupe + heuresPresentation +
                heuresGroupeDeDiscussion + heuresProjetDeRecherche +
                heuresRedactionProfessionel;
    }
}
