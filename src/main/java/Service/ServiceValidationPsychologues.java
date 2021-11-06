package main.java.Service;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;
import java.time.LocalDate;
import static Utils.Constantes.*;

public class ServiceValidationPsychologues implements Service.InterfaceVerification {

    private int heuresConference;
    private int heuresCours;

    public ServiceValidationPsychologues() {
    this.heuresConference=0;
    this.heuresCours=0;
    }

    public int obtenirHeuresConference( Entite.Activite conférence) {
        heuresConference = conférence.obtenirHeures();
        return heuresConference;
    }

    public int obtenirHeuresCours(Entite.Activite cours) {
        heuresCours = cours.obtenirHeures();
        return heuresCours;
    }

    @Override
    public void verifier(Declaration declaration, Reponse reponse) {
        if ( verifierCyclePsychologue( declaration, reponse) ) {
            verifierActivites(declaration,reponse);
        }
    }

    /*################ verification du cycle #################*/
    public boolean verifierCyclePsychologue(Declaration declaration, Reponse reponse){
        if ( ! estCyclePsychologueValide(declaration.obtenirCycle()) ) {
            reponse.ajouterMessageErreur(
                    Service.ServiceMessages.messageErreurCyclePsychologueInvalide());
            return false;
        }
        return true;
    }
    public boolean estCyclePsychologueValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISE_POUR_PSYCHOLOGUES);
    }
    private boolean estDateValidePourPsychologue(String date) {
        boolean resultat;
        if ( LocalDate.parse(date).isBefore(DATE_DEBUT_ACTIVITE_PSYCHOLOGUE_AUTORISEE) )
            resultat = false;
        else
            resultat = !LocalDate.parse(date).isAfter(
                    Constantes.DATE_FIN_ACTIVITE_PSYCHOLOGUE_AUTORISEE);
        return resultat;
    }


    /*################## Verifier Si heuresConference>15 #####################*/
    public void modifierNombreHeuresPourConferenceA15(){
        if (heuresConference > MAXIMUM_HEURE_CONFERENCE){
            this.heuresConference = 15;
        }
    }

    public void verifierActivites(Declaration general, Reponse reponse) {
    //TODO
    }
    public void verifierNombreHeuresCours(Declaration generale, Reponse reponse){
        if(heuresCours < MINIMUM_HEURE_COURS){
            reponse.ajouterMessageErreur(
                    Service.ServiceMessages.messageErreurNombreHeuresPourCours());
        }
    }

}
