package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.General;
import Entite.Reponse;
import Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class ServiceValidationDeclaration {

    private int totalDesHeures;

    public ServiceValidationDeclaration() {
        this.totalDesHeures = 0;
    }

    public void verifierDeclaration(General general, Reponse reponse ) {
        verifierNumeroDePermis(general,reponse);
        verifierCycle(general,reponse);
        verifierHeureTransfere(general,reponse);
        verifierActivites(general,reponse);
        verifierNombreHeuresTotaleDansDeclaration(reponse);
        verifierNombreHeuresPourActiviteDeGroupe(general,reponse);
    }

    /*########################## Verification Numero De Permis ############################*/

    //TODO - Appeler le bon service de message.
    private void verifierNumeroDePermis(General general, Reponse reponse) {
        if ( ! estNumeroDePermisValide(general.obtenirNumeroDePermis()) )
            reponse.ajouterMessageInformation("TODO");
    }

    public boolean estNumeroDePermisValide( String numeroDePermis ) {
        boolean resultat;

        resultat = verifierPremierCaractereNumeroDePermis(numeroDePermis);
        resultat = resultat && verifierLongueurDuNumeroDePermis(numeroDePermis);
        resultat = resultat && verifierSiNumeroDePermisContientDesNombres(numeroDePermis);
        return resultat;
    }

    private boolean verifierPremierCaractereNumeroDePermis(String numeroDePermis) {
        boolean resultat = false;

        if ( numeroDePermis.charAt(0) >= 65 && numeroDePermis.charAt(0) <= 90 )
            resultat = true;
        return resultat;
    }

    private boolean verifierLongueurDuNumeroDePermis(String numeroDePermis) {
        boolean resultat = false;

        if ( numeroDePermis.length() == 5)
            resultat = true;
        return resultat;
    }

    /**
     * Dans l'example d'execution du TP, le numero de permis a cette forme "A0001".
     * l'implémentation de la methode qui suit verifie si les 4 dernier caracteres
     * sont des chiffres.
     *
     * @param numeroDePermis
     * @return True si la condition est respecté
     */
    private boolean verifierSiNumeroDePermisContientDesNombres(String numeroDePermis) {
        boolean resultat = true;

        for (char c : numeroDePermis.substring(1).toCharArray()) {
            if ( !Character.isDigit(c) )
                resultat = false;
        }
        return resultat;
    }

    /*############################### Verification Cycle ##################################*/

    //TODO - Appeler le bon service de message.
    public void verifierCycle( General general, Reponse reponse ) {
        if ( ! estCycleValide(general.obtenirCycle()) ) {
            reponse.ajouterMessageErreur("TODO");
        }
    }

    public boolean estCycleValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISEE);
    }

    /*######################### Verification Heures Transfere ############################*/

    private void verifierHeureTransfere(General general, Reponse reponse) {
        verifierSiHeuresTransfereSuperieurA7(general,reponse);
        verifierSiHeuresTransfereNegatif(general,reponse);
    }

    //TODO - Appeler la méthode du service de message.
    private void verifierSiHeuresTransfereSuperieurA7 ( General general , Reponse reponse) {
        if ( general.obtenirHeurestransfere() > Constantes.NOMBRE_HEURES_MAXIMALE_A_TRANSFERE) {
            general.modifierNombreHeuresTransfereA7();
            reponse.ajouterMessageInformation("TODO");
        }
    }

    //TODO - Appeler la méthode du service de message.
    private void verifierSiHeuresTransfereNegatif(General general , Reponse reponse ) {
        if ( general.obtenirHeurestransfere() < 0 ) {
            general.modifierNombreHeuresTransfereA0();
            reponse.ajouterMessageErreur("TODO");
        }
    }

    /*######################### Verification des activités ############################*/

    private void verifierActivites(General general, Reponse reponse) {
        ServiceValidationActivite serviceValidationActivite = new ServiceValidationActivite();

        for (Activite activite : general.obtenirActivites() ) {
            serviceValidationActivite.verifierActivite(activite, reponse);
            if ( ! activite.estIgnoree() )
                totalDesHeures = totalDesHeures + activite.obtenirHeures();
        }
    }

    /*#################### Verification du nombre totale d'heures #######################*/

    //TODO - Appeler la méthode du service de message.
    private void verifierNombreHeuresTotaleDansDeclaration( Reponse reponse ) {
        if( obtenirNombreHeuresManquante() > 0 ) {
            reponse.ajouterMessageErreur("TODO");
        }
    }

    private int obtenirNombreHeuresManquante() {
        int resultat = 0;
        if ( totalDesHeures < 40 )
            resultat = 40 - totalDesHeures;
        return resultat;
    }

    /*########## Verification du nombre minimal pour activite de groupe ####################*/

    //TODO - Appeler la méthode du service de message.
    private void verifierNombreHeuresPourActiviteDeGroupe(General general, Reponse reponse) {
        if ( ! estNombreHeuresPourActiviteDeGroupeValide(general) )
            reponse.ajouterMessageErreur("TODO");
    }

    private boolean estNombreHeuresPourActiviteDeGroupeValide(General general) {
        List<String> categories = obtenirListeDesActivitesDeGroupe();
        int total = obtenirTotalHeurePourGroupeDactivite(general.obtenirActivites(),categories);
        return verifierTotalHeurePourActivite(general,total,
                Constantes.NOMBRE_MINIMALE_HEURE_ACTIVITE_DE_GROUPE);
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

    private int obtenirTotalHeurePourGroupeDactivite(List<Activite> activites, List<String> categories) {
        int total = 0;
        for (Activite activite : activites ) {
            if ( categories.contains( activite.obtenirCategorie() ) &&
                    ( ! activite.estIgnoree() ) ) {
                total += activite.obtenirHeures();
            }
        }
        return total;
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

    //TODO - Appeler la méthode du service de message.
    private void verifierMaximumHeureParCategorie(General general, Reponse reponse, String categorie) {
        if ( ! estNombreHeurePourCategorieValide ( general, categorie) )
            reponse.ajouterMessageErreur("TODO");
    }

    private boolean estNombreHeurePourCategorieValide(General general, String categorie) {
        int total = obtenirTotalHeureDansActivitePourCategorie(general.obtenirActivites(),categorie);
        int nombreHeureMaximale = obtenirNombreHeureMaximaleParCategorie(categorie);
        return verifierTotalHeurePourActivite(general.obtenirActivites(),total,nombreHeureMaximale);
    }

    private int obtenirTotalHeureDansActivitePourCategorie(List<Activite> activites, String categorie) {
        int total = 0;
        for (Activite activite : activites ) {
            if ( categorie.equals( activite.obtenirCategorie() ) &&
                    ( ! activite.estIgnoree() ) ) {
                total += activite.obtenirHeures();
            }
        }
        return total;
    }

    private int obtenirNombreHeureMaximaleParCategorie(String categorie) {
        if ( categorie.equals(Categorie.PRESENTATION.toString()) ||
                categorie.equals(Categorie.PROJET_DE_RECHERCHE.toString()) ) {
            return 23;
        }
        else if ( categorie.equals(Categorie.GROUPE_DE_DISCUSSION.toString()) ||
                categorie.equals(Categorie.REDACTION_PROFESSIONNELLE.toString()) ) {
            return 17;
        }
        return 0;
    }

    private boolean verifierTotalHeurePourActivite(List<Activite> activites, int total, int maximum) {
        if (total <= maximum)
            return true;
        else
            return false;
    }

    //private boolean verifierParCategorieEtNombreHeures(General general, Categorie categorie) {
    //}
}
