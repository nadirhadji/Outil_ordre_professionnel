package Service;

import Entite.*;
import Utils.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class ServiceStatistique {

    public static final String PATH_TO_STATS = "src/main/java/Fichier/statistiques.json";

    public static boolean fichierExiste(String chemin_du_fichier) {
        Path chemin = Paths.get(chemin_du_fichier);
        return Files.exists(chemin) && !Files.isDirectory(chemin);
    }
/*
    public static void initialiserFichier() {
        Path chemin = Paths.get(PATH_TO_STATS);
        if(! Files.exists(chemin) && !Files.isDirectory(chemin)) {
            System.out.println("Fichier n'existe pas -> Creation en cours ...");
            File fichier = new File(PATH_TO_STATS);
            try {
                fichier.createNewFile();
            } catch (IOException e) {
                System.out.println("Echec de l'initialisation du fichier statistique");
                e.printStackTrace();
            }
        }
    }
*/
    //Recuper le fichier statistique.json dans Utils et afficher a la
    //console son contenur. (On peut faire un affichage stylé)
    public static void afficher() {
        Iterator<Map.Entry<String, String>> itr = Statistique.obtenirInstance().entrySet().iterator();
        while(itr.hasNext())
        {
            Map.Entry<String, String> entry = itr.next();
            String cle = entry.getKey();
            int value = (int)(long)Statistique.obtenirInstance().get(cle);
            System.out.println(entry.getKey()+ " = " + value);
        }
    }

    public static void reinitialiser() {
        Iterator<Map.Entry<String, String>> itr = Statistique.obtenirInstance().entrySet().iterator();
        while(itr.hasNext())
        {
            Map.Entry<String, String> entry = itr.next();
            String cle = entry.getKey();
            Statistique.obtenirInstance().replace(cle,0);
        }
        ServiceEcriture.ecrireFichierStatistique(PATH_TO_STATS,Statistique.obtenirInstance());
    }

    public static boolean estInvalide() {
        for (MessageErreur message: Reponse.obtenirInstance().obtenirMessagesErreur()) {
            if (message.getStatus() == CodeErreur.STATUS_ERREUR_INVALIDE)
                return true;
        }
        for (MessageErreur message: Reponse.obtenirInstance().obtenirMessageInformation()) {
            if (message.getStatus() == CodeErreur.STATUS_ERREUR_INVALIDE)
                return true;
        }
        return false;
    }

    public static boolean estComplet() {
        if ( ! Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty() )
            return false;
        for ( MessageErreur message : Reponse.obtenirInstance().obtenirMessageInformation() ) {
            int code = message.getStatus();
            if (code == CodeErreur.STATUS_ERREUR_INCOMPLETE ||
                code == CodeErreur.STATUS_ERREUR_INVALIDE ||
                code == CodeErreur.STATUS_NUMERO_PERMIS_INVALIDE )
                return false;
        }
        return true;
    }

    public static boolean estIncomplet() {
        for ( MessageErreur message : Reponse.obtenirInstance().obtenirMessagesErreur() ) {
            if (message.getStatus() == CodeErreur.STATUS_ERREUR_INCOMPLETE)
                return true;
        }
        return false;
    }

    public static void mettreAjour(Declaration declaration) {
        Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_DECLARATION_TRAITE);
        if ( estInvalide())
            miseAjourDeclarationInvalide();
        else
            miseAjourValide(declaration);
        ServiceEcriture.ecrireFichierStatistique(PATH_TO_STATS,Statistique.obtenirInstance());
    }

    public static void miseAjourDeclarationInvalide() {
        Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_DECLARATION_INVALIDE);
        for (MessageErreur message : Reponse.obtenirInstance().obtenirMessagesErreur()) {
            if (message.getStatus() == CodeErreur.STATUS_NUMERO_PERMIS_INVALIDE) {
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_PERMIS_INVALIDE);
                break;
            }
        }
    }

    public static void miseAjourValide(Declaration declaration) {
        declarationSexe(declaration);
        declaratrionNombreActivite(declaration);
        declaratrionNombreActivite(declaration);
        declarationActiviteParCategorie(declaration);
        declarationValideComplete(declaration);
        declarationValideIncomplete(declaration);
    }

    /*####################### Methodes de declarations pour changer les stats ##########################*/

    public static void declarationInvalide(Declaration declaration) {
    }

    public static void declarationSexe(Declaration declaration) {
        if(declaration.obtenirSexe() == 0) {
            Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_NON_BINAIRE);
        }
        if(declaration.obtenirSexe() == 1) {
            Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_HOMME);
        }
        if(declaration.obtenirSexe() == 2) {
            Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_FEMME);
        }
    }

    public static void declaratrionNombreActivite(Declaration declaration) {
        Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_ACTIVITE,
                declaration.obtenirActivites().size());
    }

    public static boolean verificationCategorie(Declaration declaration,String categorie, int i){
        return declaration.obtenirActivites().get(i).obtenirCategorie().equals(categorie);
    }

    public static void declarationActiviteParCategorie(Declaration declaration) {
        for(int i = 0 ; i < declaration.obtenirActivites().size() ; i++){
            if(verificationCategorie(declaration,Categorie.ATELIER.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_ATELIER);
            }
            if(verificationCategorie(declaration,Categorie.COLLOQUE.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_COLLOQUE);
            }
            if(verificationCategorie(declaration,Categorie.CONFERENCE.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_CONFERENCE);
            }
            if(verificationCategorie(declaration,Categorie.COURS.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_COURS);
            }
            if(verificationCategorie(declaration,Categorie.PRESENTATION.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_PRESENTATION);
            }
            if(verificationCategorie(declaration,Categorie.PROJET_DE_RECHERCHE.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_PROJET);
            }
            if(verificationCategorie(declaration,Categorie.GROUPE_DE_DISCUSSION.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_DISCUSSION);
            }
            if(verificationCategorie(declaration,Categorie.LECTURE_DIRIGEE.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_LECTURE);
            }
            if(verificationCategorie(declaration,Categorie.SEMINAIRE.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_SEMINAIRE);
            }
            if(verificationCategorie(declaration,Categorie.REDACTION_PROFESSIONNELLE.toString(),i)){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_REDACTION);
            }
        }
    }

    public static void declarationValideComplete(Declaration declaration) {
        if(estComplet()){
            if(declaration.obtenirOrdre() == ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_COMPLET_GEOLOGUE);
            }
            if(declaration.obtenirOrdre() == ConstantesPodiatre.PODIATRE){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_COMPLET_PODIATRE);
            }
            if(declaration.obtenirOrdre() == ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_COMPLET_ARCHI);
            }
            if(declaration.obtenirOrdre() == ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_COMPLET_PSYCHO);
            }
        }
    }

    public static void declarationValideIncomplete(Declaration declaration) {
        if(estIncomplet()){
            if(declaration.obtenirOrdre() == ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_INCOMPLET_GEOLOGUE);
            }
            if(declaration.obtenirOrdre() == ConstantesPodiatre.PODIATRE){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_INCOMPLET_PODIATRE);
            }
            if(declaration.obtenirOrdre() == ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_INCOMPLET_ARCHI);
            }
            if(declaration.obtenirOrdre() == ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES){
                Statistique.obtenirInstance().incrementerCle(ConstanteStatistique.CLE_INCOMPLET_PSHYCO);
            }
        }
    }
}

