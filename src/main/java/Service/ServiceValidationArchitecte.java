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
    public void verifier(Declaration declaration) {
        if ( verifierCycle(declaration) ) {
            verifierHeureTransfere(declaration);
            verifierActivites(declaration);
            verifierNombreHeuresPourActiviteDeGroupe(declaration);
            verifierMaximumHeureParGroupeDeCategorie();
            verifierNombreHeuresTotaleDansDeclaration(declaration.obtenirHeurestransfere());
        }
    }

    /*############################### Service.Verification Cycle ##################################*/

    public boolean verifierCycle(Declaration declaration) {
        if ( ! estCycleValide(declaration.obtenirCycle()) ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
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

    public void verifierHeureTransfere(Declaration declaration) {
        verifierSiHeuresTransfereSuperieurA7(declaration);
        verifierSiHeuresTransfereNegatif(declaration);
    }

    public void verifierSiHeuresTransfereSuperieurA7 (Declaration declaration) {
        if ( declaration.obtenirHeurestransfere() >
                Constantes.NOMBRE_HEURES_MAXIMALE_A_TRANSFERE) {
            declaration.modifierNombreHeuresTransfereA7();
            Reponse.obtenirInstance().ajouterMessageInformation(
                    ServiceMessages.messageInfosHeuresTransfereSuperieurA7());
        }
    }

    public void verifierSiHeuresTransfereNegatif(Declaration declaration) {
        if ( declaration.obtenirHeurestransfere() < 0 ) {
            declaration.modifierNombreHeuresTransfereA0();
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurHeuresTransfereInferieurA0());
        }
    }

    /*##################### Service.Verification des activités #######################*/
    public void verifierActivites(Declaration declaration) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite(declaration.obtenirOrdre(),declaration.obtenirCycle());
        for (Activite activite : declaration.obtenirActivites() ) {
            serviceValidationActivite.verifierActivite(activite);
            if ( ! activite.estIgnoree() && ! estActiviteRedondante(activite) ){
                dateMap.put(activite.obtenirDate(),activite.obtenirHeures());
                incrementerCompteurHeures(activite);
            }
        }
    }

    public boolean estActiviteRedondante(Activite activite) {
        String date = activite.obtenirDate();
        int nombreHeures = activite.obtenirHeures();
        return estUneDateExistante(date,nombreHeures);
    }

    public boolean estUneDateExistante(String date, int nombreHeures) {
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

    public void incrementerCompteurHeures(Activite activite) {
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

    public List<String> obtenirListeDesActivitesDeGroupe() {
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

    public void verifierNombreHeuresPourActiviteDeGroupe(Declaration declaration) {
        if ( ! estNombreHeuresPourActiviteDeGroupeValide(declaration) )
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurHeuresDansActiviteDeGroupe());
    }

    public boolean estNombreHeuresPourActiviteDeGroupeValide(Declaration declaration) {
        int nombreHeures = heuresArchitecte.obtenirActiviteDeGroupe();
        return verifierTotalHeurePourActivite(declaration,nombreHeures,
                Constantes.MINIMUM_HEURE_ACTIVITE_DE_GROUPE);
    }

    public boolean verifierTotalHeurePourActivite(Declaration declaration, int total,
                                                   int minimum) {
        if ( total >= minimum )
            return true;
        else
            return verifierSiNombreHeureTransfereSuffisantePourValider(declaration,
                    total, minimum);
    }

    public boolean verifierSiNombreHeureTransfereSuffisantePourValider(
            Declaration declaration , int total, int minimum) {
        if ( total + declaration.obtenirHeurestransfere() >= minimum ) {
            int nombreASoustraire = minimum - total;
            declaration.soustraireAuNombreHeuresTransfere(nombreASoustraire);
            return true;
        }
        return false;
    }

    /*############# Service.Verification du nombre maximale par Activite  ############*/

    public void verifierMaximumHeureParGroupeDeCategorie() {
        verifierMaximumHeureDePresentation();
        verifierMaximumHeureGroupeDeDiscussion();
        verifierMaximumHeureProjetDeRecherche();
        verifierMaximumHeuresHeuresRedactionProfessionel();
    }

    public void verifierMaximumHeureDePresentation() {
        int nombreHeures = heuresArchitecte.obtenirPresentation();
        if( nombreHeures > Constantes.MAXIMUM_HEURE_PRESENTATION)
            heuresArchitecte.enregistrerPresentation(Constantes.MAXIMUM_HEURE_PRESENTATION);
    }

    public void verifierMaximumHeureGroupeDeDiscussion() {
        int heuresGroupeDeDiscussion = heuresArchitecte.obtenirGroupeDeDiscussion();
        if ( heuresGroupeDeDiscussion > Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION)
            heuresArchitecte.enregistrerGroupeDeDiscussion
                    (Constantes.MAXIMUM_HEURE_GROUPE_DE_DISCUSSION);
    }

    public void verifierMaximumHeureProjetDeRecherche() {
        int heuresProjetDeRecherche = heuresArchitecte.obtenirProjetDeRecherche();
        if( heuresProjetDeRecherche >
                Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER)
            heuresArchitecte.enregistrerProjetDeRecherche(Constantes.MAXIMUM_HEURE_PROJET_DE_RECHERCHER);
    }

    public void verifierMaximumHeuresHeuresRedactionProfessionel() {
        int heuresRedactionProfessionel = heuresArchitecte.obtenirRedactionProfessionel();
        if( heuresRedactionProfessionel > Constantes.MAXIMUM_HEURE_REDACTION)
            heuresArchitecte.enregistrerRedactionProfessionel(Constantes.MAXIMUM_HEURE_REDACTION);
    }

    /*############# Service.Verification du nombre totale d'heures ###################*/

    public void verifierNombreHeuresTotaleDansDeclaration(int heuresTransfere) {
        int nombreHeuresManquante = obtenirNombreHeuresManquante(heuresTransfere);
        if( nombreHeuresManquante > 0 ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    public int obtenirNombreHeuresManquante(int heuresTransfere) {
        int total = obtenirNombreTotalHeures();
        if ( total >= heuresArchitecte.obtenirMinimumAvantValidation() )
            return 0;

        if ( (total + heuresTransfere) < heuresArchitecte.obtenirMinimumAvantValidation())
            return heuresArchitecte.obtenirMinimumAvantValidation() - (
                    total + heuresTransfere);
        else
            return 0;
    }

    public int obtenirNombreTotalHeures() {
        return  heuresArchitecte.obtenirActiviteDeGroupe() +
                heuresArchitecte.obtenirPresentation() +
                heuresArchitecte.obtenirGroupeDeDiscussion() +
                heuresArchitecte.obtenirGroupeDeDiscussion() +
                heuresArchitecte.obtenirProjetDeRecherche() +
                heuresArchitecte.obtenirRedactionProfessionel();
    }
}
