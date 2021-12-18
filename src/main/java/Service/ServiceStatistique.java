package Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServiceStatistique {

    public static final String PATH_TO_STATS = "src/main/java/Fichier/statistiques.json";

    public static boolean fichierExiste(String chemin_du_fichier) {
        Path chemin = Paths.get(chemin_du_fichier);
        return Files.exists(chemin) && !Files.isDirectory(chemin);
    }

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

}
