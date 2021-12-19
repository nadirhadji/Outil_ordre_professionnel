package Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServiceStatistique {

    private static final String PATH_TO_STATS = "src/main/java/Fichier/statistiques.json";

    public static boolean fichierExiste(String chemin_du_fichier) {
        Path chemin = Paths.get(chemin_du_fichier);
        return Files.exists(chemin) && !Files.isDirectory(chemin);
    }

    public static void initialiserFichier() throws IOException {
        Path chemin = Paths.get(PATH_TO_STATS);
        if(! Files.exists(chemin) && !Files.isDirectory(chemin)) {
            System.out.println("Fichier n'existe pas");
            File fichier = new File(PATH_TO_STATS);
            fichier.createNewFile();
        }
    }

    //Recuper le fichier statistique.json dans Utils et afficher a la
    //console son contenur. (On peut faire un affichage stylé)
    public static void afficher() {
    }

    //Reinitialise le fichier statistiques.json
    //On pourra utiliser le singleton Statistique pour produire
    //L'objet json initiale qu'on transformera ensuite en fichier
    public static void reinitialiser() {
    }

    public static void mettreAjour() {

    }

    public static void declarationSexe() {

    }

    public static void declaratrionNombreActivite() {

    }

    public static void declarationActiviteParCategorie() {

    }

    public static void declarationValideComplete() {

    }

    public static void declarationValideIncomplete() {

    }

    public static void declarationNumeroPermisInvalide() {

    }

}
/*
À chaque exécution, vous devrez calculer les statistiques suivantes :
• Le nombre total de déclarations traitées;---------------------------------------------------------------------
• Le nombre total de déclarations complètes;--------------------------------------------------------------------
    Déclaration complète : Une déclaration valide qui respecte toutes les règles permettant d'affirmer que
        le cycle de formation continue a été complété.

• Le nombre total de déclarations incomplètes ou invalides;-----------------------------------------------------
    Déclaration incomplète : Une déclaration valide mais pour laquelle le cycle de formation continue n'est
        pas complet (ex. il manque des heures dans une catégorie spécifique). Une déclaration incomplète doit
        obligatoirement produire au moins un message d'erreur dans le fichier de sortie

    Déclaration invalide : La déclaration est invalide dans les cas suivants :
        • Le cycle n'est pas reconnu.
        • L'ordre n'est pas reconnu.
        • Le champ heures_transferees_du_cycle_precedent contient une valeur négative.
        • Une activité possède un nombre d'heure négatif ou équivalent à 0.
        • Une date n'est pas en format ISO8601.
        • Le numéro de permis n'a pas le bon format.
        • Une activité possède une description de moins de 21 caractères.
        • Il manque un champ dans le fichier d'entrée.
        • Une valeur n'est pas du bon type dans le fichier d'entrée.
        • Le nom est vide.
        • Le prénom est vide.
        • Le sexe ne correspond pas à une des 3 valeurs reconnues.
        • Une activité possède une catégorie non reconnue.
        • Une activité n'est pas dans l'intervalle de dates du cycle.

        Une déclaration invalide ne doit pas altérer les statistiques, à l'exception des statistiques suivantes :
            • Le nombre total de déclarations traitées;
            • Le nombre total de déclarations incomplètes ou invalides;
            • Le nombre de déclarations soumises avec un numéro de permis invalide (si applicable).

• Le nombre total de déclarations faites par des hommes;--------------------------------------------------------
• Le nombre total de déclarations faites par des femmes;--------------------------------------------------------
• Le nombre total de déclarations faites par des gens de sexe inconnu;------------------------------------------
• Le nombre total d'activités dans les déclarations;------------------------------------------------------------
• Le nombre d'activités par catégorie;--------------------------------------------------------------------------
• Le nombre total de déclarations valides et complètes par type d'ordre professionnel;--------------------------
    Déclaration valide : La déclaration est valide si le fichier d'entrée possède toutes les propriétés
        attendues avec les bons types de données. Certaines valeurs exigent un format spécifique.

• Le nombre total de déclarations valides et incomplètes par type d'ordre professionnel;------------------------

• Le nombre de déclarations soumises avec un numéro de permis invalide.-----------------------------------------



 */
