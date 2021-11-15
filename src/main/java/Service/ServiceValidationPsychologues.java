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

    public ServiceValidationPsychologues(int cours, int conference, int autre){
        this.heuresConference=conference;
        this.heuresCours= cours;
        this.heuresAutreActivitesPsy= autre;
    }

    @Override
    public void verifier(Declaration general) {
        if ( verifierCyclePsychologue( general) ) {
            verifierActivites(general);
            verifierNombreHeuresMinimumCours();
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


    public void verifierActivites(Declaration declaration) {
        ServiceValidationActivite serviceValidationActivite = new
                ServiceValidationActivite(declaration.obtenirOrdre(),declaration.obtenirCycle());

        for (Activite activite : declaration.obtenirActivites() ) {
            serviceValidationActivite.verifierActivite(activite);
            if ( ! activite.estIgnoree() )
                incrementerCompteurHeures(activite);
        }
    }

    public void incrementerCompteurHeures(Activite activite) {
        String categorie = activite.obtenirCategorie();
        int nbrHeures = activite.obtenirHeures();

        verifierAutreCategories(categorie, nbrHeures);
        verifierNombreHeuresCours(categorie,nbrHeures);
        verifierNombreHeuresConference(categorie,nbrHeures);
    }

    public void verifierAutreCategories(String categorie, int nbrHeures) {
        List<String> autreCategorie = obtenirAutresActivites();
        if(autreCategorie.contains(categorie))
            this.heuresAutreActivitesPsy += nbrHeures;
    }
    public List<String> obtenirAutresActivites(){
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

    public void verifierNombreHeuresCours(String categorie,int nbrHeure) {
        if ( categorie.equals(Categorie.COURS.toString()))
            this.heuresCours += nbrHeure;
    }

    public int obtenirHeuresCoursPsycho(){
        return heuresCours;
    }

    public void verifierNombreHeuresMinimumCours(){
        if(this.heuresCours < ConstantesPsychologues.MINIMUM_HEURE_COURS){
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresPourCours());
        }
    }

    public void verifierNombreHeuresConference(String categorie,int nbrHeure) {
        if ( categorie.equals(Categorie.CONFERENCE.toString())){
            int temp = verifierNombreHeuresPourConferenceSupA15(nbrHeure);
            this.heuresConference += temp;
        }
    }

    public int verifierNombreHeuresPourConferenceSupA15(int heuresConference){
        if (heuresConference > ConstantesPsychologues.MAXIMUM_HEURE_CONFERENCE){
            return 15;
        }
        return heuresConference;
    }
    public int obtenirHeuresConference(){
        return heuresConference;
    }

    public int obtenirHeuresAutreActivitesPsy(){ return heuresAutreActivitesPsy; }

    public void verifierNombreHeuresTotaleDansDeclaration() {
        int nombreHeuresManquante = obtenirNombreHeuresManquante();
        if( nombreHeuresManquante > 0 ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    public int obtenirNombreHeuresManquante() {
        int total = obtenirNombreTotalHeures();

        if ( total >= MINIMUM_HEURE_POUR_PSYCH)
            return 0;
        else
            return MINIMUM_HEURE_POUR_PSYCH - total;
    }

    public int obtenirNombreTotalHeures() {
        return heuresConference + heuresCours + heuresAutreActivitesPsy;
    }
}
