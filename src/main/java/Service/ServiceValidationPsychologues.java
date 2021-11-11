package Service;
import Entite.*;
import Utils.Constantes;
import Utils.ConstantesPsychologues;
import static Utils.Constantes.*;
import static Utils.ConstantesPsychologues.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ServiceValidationPsychologues implements InterfaceVerification {

    private int heuresConference;
    private int heuresCours;
    private int heuresAutreActivitesPsy;

    public ServiceValidationPsychologues() {
        this.heuresConference=0;
        this.heuresCours=0;
        this.heuresAutreActivitesPsy=0;
    }


    @Override
    public void verifier(Declaration general, Reponse reponse) {
        if ( verifierCyclePsychologue( general, reponse) ) {
            verifierActivites(general,reponse);
            verifierNombreHeuresMinimumCours(heuresCours,reponse);
            verifierNombreHeuresPourConferenceSupA15(heuresConference);
            verifierNombreHeuresTotaleDansDeclaration(reponse);

        }
    }

    /*################ verification du cycle #################*/
    public boolean verifierCyclePsychologue(Declaration generale, Reponse reponse){
        if ( ! estCyclePsychologueValide(generale.obtenirCycle()) ) {
            reponse.ajouterMessageErreur(
                    ServiceMessages.messageErreurCyclePsychologueInvalide());
            return false;
        }
        return true;
    }
    public boolean estCyclePsychologueValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISE_POUR_PSYCHOLOGUES);
    }

    private boolean estDateValidePourPsychologue(String date) {
        boolean resultat;
        if ( LocalDate.parse(date).isBefore(DATE_DEBUT_ACTIVITE_PSYCHO_AUTORISEE) )
            resultat = false;
        else
            resultat = !LocalDate.parse(date).isAfter(
                    Constantes.DATE_FIN_ACTIVITE_PSYCHO_AUTORISEE);
        return resultat;
    }


    private void verifierActivites(Declaration general, Reponse reponse) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite(general.obtenirOrdre(),general.obtenirCycle());

        for (Activite activite : general.obtenirActivites() ) {
            serviceValidationActivite.verifierActivite(activite);
            if ( ! activite.estIgnoree() )
                incrementerCompteurHeures(activite);
        }
    }

    private void incrementerCompteurHeures(Activite activite) {
        String categorie = activite.obtenirCategorie();
        int nbrHeures = activite.obtenirHeures();

        verifierAutreCategories(categorie, nbrHeures);
        verifierNombreHeuresCours(categorie,nbrHeures);
        verifierNombreHeuresConference(categorie,nbrHeures);
    }

    private void verifierAutreCategories(String categorie, int nbrHeures) {
        List<String> autreCategorie = obtenirAutresActivites();
        if(autreCategorie.contains(categorie))
            this.heuresAutreActivitesPsy += nbrHeures;
    }
    private List<String> obtenirAutresActivites(){
        List<String> autreActivitePsychologue = new ArrayList<>();

        autreActivitePsychologue.add(Categorie.ATELIER.toString());
        autreActivitePsychologue.add(Categorie.COLLOQUE.toString());
        autreActivitePsychologue.add(Categorie.GROUPE_DE_DISCUSSION.toString());
        autreActivitePsychologue.add(Categorie.LECTURE_DIRIGEE.toString());
        autreActivitePsychologue.add(Categorie.PRESENTATION.toString());
        autreActivitePsychologue.add(Categorie.PROJET_DE_RECHERCHE.toString());
        autreActivitePsychologue.add(Categorie.REDACTION_PROFESSIONNELLE.toString());
        autreActivitePsychologue.add(Categorie.SEMINAIRE.toString());

        return autreActivitePsychologue;
    }

    private void verifierNombreHeuresCours(String categorie,int nbrHeure) {
        if ( categorie.equals(Categorie.COURS.toString()))
            this.heuresCours += nbrHeure;
    }

    private void verifierNombreHeuresMinimumCours(int heuresCours, Reponse reponse){
        if(this.heuresCours < MINIMUM_HEURE_COURS){
            reponse.ajouterMessageErreur(
                    Service.ServiceMessages.messageErreurNombreHeuresCoursPsycho());
        }
    }

    private void verifierNombreHeuresConference(String categorie,int nbrHeure) {
        if ( categorie.equals(Categorie.CONFERENCE.toString()))
            this.heuresConference += nbrHeure;
    }

    private void verifierNombreHeuresPourConferenceSupA15(int heuresConference){
        if (heuresConference > MAXIMUM_HEURE_CONFERENCE){
            this.heuresConference = 15;
        }
    }

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
        if ( total >= MINIMUM_HEURE_POUR_PSYCH)
            return 0;

        if ( total  < MINIMUM_HEURE_POUR_PSYCH)
            return MINIMUM_HEURE_POUR_PSYCH - (
                    total );
        else
            return 0;
    }

    private int obtenirNombreTotalHeures(){
        return heuresConference + heuresCours + heuresAutreActivitesPsy;
    }
}
