package Service;
import Entite.*;
import Utils.Constantes;
import Utils.ConstantesPsychologues;
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
    public void verifier(Declaration general) {
        if ( verifierCyclePsychologue( general) ) {
            verifierActivites(general);
            verifierNombreHeuresMinimumCours(heuresCours);
            verifierNombreHeuresPourConferenceSupA15(heuresConference);
            verifierNombreHeuresTotaleDansDeclaration();
        }
    }

    /*################ verification du cycle #################*/
    public boolean verifierCyclePsychologue(Declaration generale){
        if ( ! estCyclePsychologueValide(generale.obtenirCycle()) ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurCycleInvalidePsycho());
            return false;
        }
        return true;
    }

    public boolean estCyclePsychologueValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISE_POUR_PSYCHOLOGUES);
    }

    private boolean estDateValidePourPsychologue(String date) {
        boolean resultat;
        if ( LocalDate.parse(date).isBefore(DATE_DEBUT_ACTIVITE_PSYCHO) )
            resultat = false;
        else
            resultat = !LocalDate.parse(date).isAfter(DATE_FIN_ACTIVITE_PSYCHO);
        return resultat;
    }

    private void verifierActivites(Declaration declaration) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite(declaration.obtenirOrdre(),declaration.obtenirCycle());

        for (Activite activite : declaration.obtenirActivites() ) {
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

    private void verifierNombreHeuresMinimumCours(int heuresCours){
        if(this.heuresCours < ConstantesPsychologues.MINIMUM_HEURE_COURS){
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresPourCours());
        }
    }

    private void verifierNombreHeuresConference(String categorie,int nbrHeure) {
        if ( categorie.equals(Categorie.CONFERENCE.toString()))
            this.heuresConference += nbrHeure;
    }

    private void verifierNombreHeuresPourConferenceSupA15(int heuresConference){
        if (heuresConference > ConstantesPsychologues.MAXIMUM_HEURE_CONFERENCE){
            this.heuresConference = 15;
        }
    }

    private void verifierNombreHeuresTotaleDansDeclaration() {
        int nombreHeuresManquante = obtenirNombreHeuresManquante();
        if( nombreHeuresManquante > 0 ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    private int obtenirNombreHeuresManquante() {
        int total = obtenirNombreTotalHeures();

        if ( total >= MINIMUM_HEURE_POUR_PSYCH)
            return 0;
        else
            return MINIMUM_HEURE_POUR_PSYCH - total;
    }

    private int obtenirNombreTotalHeures() {
        return heuresConference + heuresCours + heuresAutreActivitesPsy;
    }
}
