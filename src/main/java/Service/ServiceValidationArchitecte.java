package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesCycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceValidationArchitecte implements InterfaceVerification {

    Map<String,Integer> dateMap = new HashMap<>();
    private int heuresActiviteDeGroupe;
    private int heuresPresentation;
    private int heuresGroupeDeDiscussion;
    private int heuresProjetDeRecherche;
    private int heuresRedactionProfessionel;
    private int nombreMinimumHeuresAvantValidation;

    public ServiceValidationArchitecte() {
        this.heuresActiviteDeGroupe = 0;
        this.heuresPresentation = 0;
        this.heuresGroupeDeDiscussion = 0;
        this.heuresProjetDeRecherche = 0;
        this.heuresRedactionProfessionel = 0;
        this.nombreMinimumHeuresAvantValidation = 0;
    }

    @Override
    public void verifier(Declaration general, Reponse reponse ) {
        if ( verifierCycle(general,reponse) ) {
            verifierHeureTransfere(general,reponse);
            verifierActivites(general,reponse);
            verifierNombreHeuresPourActiviteDeGroupe(general,reponse);
            verifierMaximumHeureParGroupeDeCategorie();
            verifierNombreHeuresTotaleDansDeclaration(
                    general.obtenirHeurestransfere(),reponse);
        }
    }

    /*############################### Service.Verification Cycle ##################################*/

    public boolean verifierCycle(Declaration general, Reponse reponse ) {
        if ( ! estCycleValide(general.obtenirCycle()) ) {
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageErreurCycleInvalide());
            return false;
        }
        return true;
    }

    public boolean estCycleValide( String cycle ) {
        if(estCycle2016a2020(cycle))
            return true;
        else
            return estCycle2020a2022(cycle);
    }

    public boolean estCycle2016a2020(String cycle) {
        if (cycle.equals(ConstantesCycle.CYCLE_2016_2018) ||
                cycle.equals(ConstantesCycle.CYCLE_2018_2020) ) {
            nombreMinimumHeuresAvantValidation = ConstantesCycle.ARCHITECTE_NOMBRE_HEURE_TOTALE_2016_2020;
            return true;
        }
        else
            return false;
    }

    public boolean estCycle2020a2022(String cycle) {
        if (cycle.equals(ConstantesCycle.CYCLE_2020_2022)){
            nombreMinimumHeuresAvantValidation = ConstantesCycle.ARCHITECTE_NOMBRE_HEURE_TOTALE_2020_2022;
            return true;
        }
        else
            return false;
    }

    /*################## Service.Verification Heures Transfere ######################*/

    private void verifierHeureTransfere(Declaration general, Reponse reponse) {
        verifierSiHeuresTransfereSuperieurA7(general,reponse);
        verifierSiHeuresTransfereNegatif(general,reponse);
    }

    private void verifierSiHeuresTransfereSuperieurA7 ( Declaration general ,
                                                        Reponse reponse) {
        if ( general.obtenirHeurestransfere() >
                Constantes.NOMBRE_HEURES_MAXIMALE_A_TRANSFERE) {
            general.modifierNombreHeuresTransfereA7();
            reponse.ajouterMessageInformation(
                    ServiceMessages.messageInfosHeuresTransfereSuperieurA7());
        }
    }

    private void verifierSiHeuresTransfereNegatif(Declaration general ,
                                                  Reponse reponse ) {
        if ( general.obtenirHeurestransfere() < 0 ) {
            general.modifierNombreHeuresTransfereA0();
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageErreurHeuresTransfereInferieurA0());
        }
    }

    /*##################### Service.Verification des activités #######################*/
    private void verifierActivites(Declaration general, Reponse reponse) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite(general.obtenirOrdre(),general.obtenirCycle());

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
            return verifierSiNombreHeureTransfereSuffisantePourValider(general,
                    total, minimum);
    }

    private boolean verifierSiNombreHeureTransfereSuffisantePourValider(
            Declaration general , int total, int minimum) {
        if ( total + general.obtenirHeurestransfere() >= minimum ) {
            int nombreASoustraire = minimum - total;
            general.soustraireAuNombreHeuresTransfere(nombreASoustraire);
            return true;
        }
        return false;
    }

    /*############# Service.Verification du nombre maximale par Activite  ############*/

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
        if ( heuresGroupeDeDiscussion >
                Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION)
            this.heuresGroupeDeDiscussion =
                    Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION;
    }

    private void verifierMaximumHeureProjetDeRecherche() {
        if( heuresProjetDeRecherche >
                Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER)
            this.heuresProjetDeRecherche =
                    Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER;
    }

    private void verifierMaximumHeuresHeuresRedactionProfessionel() {
        if( heuresRedactionProfessionel > Constantes.MAXIMUM_HEURE_REDACTION)
            this.heuresRedactionProfessionel = Constantes.MAXIMUM_HEURE_REDACTION;
    }

    /*############# Service.Verification du nombre totale d'heures ###################*/

    private void verifierNombreHeuresTotaleDansDeclaration(int heuresTransfere,
                                                           Reponse reponse ) {
        int nombreHeuresManquante = obtenirNombreHeuresManquante(heuresTransfere);
        if( nombreHeuresManquante > 0 ) {
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    private int obtenirNombreHeuresManquante(int heuresTransfere) {
        int total = obtenirNombreTotalHeures();
        if ( total >= this.nombreMinimumHeuresAvantValidation)
            return 0;

        if ( (total + heuresTransfere) <
                this.nombreMinimumHeuresAvantValidation)
            return this.nombreMinimumHeuresAvantValidation - (
                    total + heuresTransfere);
        else
            return 0;
    }

    private int obtenirNombreTotalHeures() {
        return heuresActiviteDeGroupe + heuresPresentation +
                heuresGroupeDeDiscussion + heuresProjetDeRecherche +
                heuresRedactionProfessionel;
    }
}
