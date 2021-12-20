package Service;
import Entite.*;
import Utils.Constantes;
import Utils.ConstantesPsychologues;
import static Utils.ConstantesPsychologues.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceValidationPsychologues implements InterfaceVerification {

    ServiceRedondanceDate serviceRedondanceDate;
    ServiceValidationActivite serviceValidationActivite;
    private final HeuresPsycologue heuresPsycologue;

    public ServiceValidationPsychologues(ServiceRedondanceDate serviceRedondanceDate,
                         ServiceValidationActivite serviceValidationActivite) {
        this.serviceRedondanceDate = serviceRedondanceDate;
        this.serviceValidationActivite = serviceValidationActivite;
        this.heuresPsycologue = new HeuresPsycologue();
    }

    public ServiceValidationPsychologues(int cours, int conference, int autre){
        this.heuresPsycologue = new HeuresPsycologue(conference,cours,autre);
    }

    public void verifier(Declaration declaration) {
        verifiationGeneral(declaration);
        verificationDesActivite(declaration);
        verifierSpecifiqueOrdre(declaration);
    }

    @Override
    public void verifiationGeneral(Declaration declaration) {
        if ( verifierCyclePsychologue( declaration) ) {
            ServiceValidationNumeroDePermis.psychologues(declaration.obtenirNumeroDePermis());
        }
    }

    @Override
    public void verificationDesActivite(Declaration declaration) {
        verifierActivites(declaration);
    }

    @Override
    public void verifierSpecifiqueOrdre(Declaration declaration) {
        verifierNombreHeuresMinimumCours();
        verifierNombreHeuresPourConferenceSupA15(heuresPsycologue.obtenirHeuresConference());
        verifierNombreHeuresTotaleDansDeclaration();
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
            this.heuresPsycologue.incrementerHeuresAutreActivitesPsy(nbrHeures);
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
            this.heuresPsycologue.incrementerHeuresCours(nbrHeure);
    }

    public int obtenirHeuresCoursPsycho(){
        return this.heuresPsycologue.obtenirHeuresCours();
    }

    public void verifierNombreHeuresMinimumCours(){
        if(this.heuresPsycologue.obtenirHeuresCours() < ConstantesPsychologues.MINIMUM_HEURE_COURS){
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresPourCours());
        }
    }

    public void verifierNombreHeuresConference(String categorie,int nbrHeure) {
        if ( categorie.equals(Categorie.CONFERENCE.toString())){
            int temp = verifierNombreHeuresPourConferenceSupA15(nbrHeure);
            this.heuresPsycologue.incrementerHeuresConference(temp);
        }
    }

    public int verifierNombreHeuresPourConferenceSupA15(int heuresConference){
        if (heuresConference > ConstantesPsychologues.MAXIMUM_HEURE_CONFERENCE){
            return 15;
        }
        return heuresConference;
    }
    public int obtenirHeuresConference(){
        return this.heuresPsycologue.obtenirHeuresConference();
    }

    public int obtenirHeuresAutreActivitesPsy(){
        return this.heuresPsycologue.obtenirHeuresAutreActivitesPsy();
    }

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
        return this.heuresPsycologue.obtenirHeuresAutreActivitesPsy() +
                this.heuresPsycologue.obtenirHeuresConference() +
                this.heuresPsycologue.obtenirHeuresCours();
    }
}
