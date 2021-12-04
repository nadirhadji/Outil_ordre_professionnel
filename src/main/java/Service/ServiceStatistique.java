package Service;

import java.io.File;
import java.io.IOException;

public class ServiceStatistique {

    private static final String PATH_TO_STATS = "src/main/java/Fichier/statistiques.json";

    public static void initialiserFichier() throws IOException {
        File file = new File(PATH_TO_STATS);
        if(!file.exists() && !file.isDirectory()) {
            file.createNewFile();
        }
    }
}
