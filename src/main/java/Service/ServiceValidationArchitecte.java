package Service;

import Entite.*;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceValidationArchitecte implements InterfaceVerification {

    Map<String, Integer> dateMap;
    HeuresArchitecte heuresArchitecte;

    public ServiceValidationArchitecte() {
        this.dateMap = new HashMap<>();
        this.heuresArchitecte = new HeuresArchitecte();
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
        if (cycle.equals(ConstantesArchitecte.CYCLE_2016_2018) ||
                cycle.equals(ConstantesArchitecte.CYCLE_2018_2020) ) {
            heuresArchitecte.enregistrerMinimumAvantValidation
                    (ConstantesArchitecte.ARCHITECTE_NOMBRE_HEURE_TOTALE_2016_2020);
            return true;
        }
        else
            return false;
    }

    public boolean estCycle2020a2022(String cycle) {
        if (cycle.equals(ConstantesArchitecte.CYCLE_2020_2022)){
            heuresArchitecte.enregistrerMinimumAvantValidation(
                    ConstantesArchitecte.ARCHITECTE_NOMBRE_HEURE_TOTALE_2020_2022);
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
            if ( ! activite.estIgnoree() && ! estActiviteRedondante(activite) ){
                dateMap.put(activite.obtenirDate(),activite.obtenirHeures());
                incrementerCompteurHeures(activite);
            }
        }
    }

    private boolean estActiviteRedondante(Activite activite) {
        String date = activite.obtenirDate();
        int nombreHeures = activite.obtenirHeures();
        return estUneDateExistante(date,nombreHeures);
    }

    private boolean estUneDateExistante(String date, int nombreHeures) {
        if ( dateMap.containsKey(date) ) {
            int totalHeures = dateMap.get(date) + nombreHeures;
            if ( totalHeures > 10) {
                return true;
            } else {
                dateMap.replace(date,totalHeures);
                return false;
            }
        }
        return false;
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
            heuresArchitecte.incrementerActiviteDeGroupe(nombreHeure);
    }

    private List<String> obtenirListeDesActivitesDeGroupe() {
        List<String> activateDeGroup = new ArrayList<>();
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
            heuresArchitecte.incrementerPresentation(nombreHeure);
    }

    public void verifierSiCategorieGroupeDeDiscussion(String categorie,
                                                      int nombreHeure) {
        if ( categorie.equals(Categorie.GROUPE_DE_DISCUSSION.toString()))
            heuresArchitecte.incrementerGroupeDeDiscussion(nombreHeure);
    }

    public void verifierSiCategorieProjetDeRecherche(String categorie,
                                                     int nombreHeure) {
        if ( categorie.equals(Categorie.PROJET_DE_RECHERCHE.toString()))
            heuresArchitecte.incrementerProjetDeRecherche(nombreHeure);
    }

    public void verifierSiCategorieRedactionProfessionnelle(String categorie,
                                                            int nombreHeure) {
        if ( categorie.equals(Categorie.REDACTION_PROFESSIONNELLE.toString()))
            heuresArchitecte.incrementerRedactionProfessionel(nombreHeure);
    }

    /*########## Service.Verification du nombre minimal pour activite de groupe ######*/

    private void verifierNombreHeuresPourActiviteDeGroupe(Declaration general,
                                                          Reponse reponse) {
        if ( ! estNombreHeuresPourActiviteDeGroupeValide(general) )
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageErreurHeuresDansActiviteDeGroupe());
    }

    private boolean estNombreHeuresPourActiviteDeGroupeValide(Declaration general) {
        int nombreHeures = heuresArchitecte.obtenirActiviteDeGroupe();
        return verifierTotalHeurePourActivite(general,nombreHeures,
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
        int nombreHeures = heuresArchitecte.obtenirPresentation();
        if( nombreHeures > Constantes.MAXIMUM_HEURE_PRESENTATION)
            heuresArchitecte.enregistrerPresentation(Constantes.MAXIMUM_HEURE_PRESENTATION);
    }

    private void verifierMaximumHeureGroupeDeDiscussion() {
        int heuresGroupeDeDiscussion = heuresArchitecte.obtenirGroupeDeDiscussion();
        if ( heuresGroupeDeDiscussion > Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION)
            heuresArchitecte.enregistrerGroupeDeDiscussion
                    (Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION);
    }

    private void verifierMaximumHeureProjetDeRecherche() {
        int heuresProjetDeRecherche = heuresArchitecte.obtenirProjetDeRecherche();
        if( heuresProjetDeRecherche >
                Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER)
            heuresArchitecte.enregistrerProjetDeRecherche(Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER);
    }

    private void verifierMaximumHeuresHeuresRedactionProfessionel() {
        int heuresRedactionProfessionel = heuresArchitecte.obtenirRedactionProfessionel();
        if( heuresRedactionProfessionel > Constantes.MAXIMUM_HEURE_REDACTION)
            heuresArchitecte.enregistrerRedactionProfessionel(Constantes.MAXIMUM_HEURE_REDACTION);
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
        if ( total >= heuresArchitecte.obtenirMinimumAvantValidation() )
            return 0;

        if ( (total + heuresTransfere) < heuresArchitecte.obtenirMinimumAvantValidation())
            return heuresArchitecte.obtenirMinimumAvantValidation() - (
                    total + heuresTransfere);
        else
            return 0;
    }

    private int obtenirNombreTotalHeures() {
        return  heuresArchitecte.obtenirActiviteDeGroupe() +
                heuresArchitecte.obtenirPresentation() +
                heuresArchitecte.obtenirGroupeDeDiscussion() +
                heuresArchitecte.obtenirGroupeDeDiscussion() +
                heuresArchitecte.obtenirProjetDeRecherche() +
                heuresArchitecte.obtenirRedactionProfessionel();
    }
}
