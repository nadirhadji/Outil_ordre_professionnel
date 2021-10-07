package Utils;

import java.time.LocalDate;
import java.util.Date;

public class Constantes {

    public static final String FICHIER_ENTREE = "declaration.json";
    public static final String FICHIER_SORTIE = "reponse.json";

    public static final String CYCLE_AUTORISEE = "2020-2022";
    public static final int NOMBRE_HEURES_MAXIMALE_A_TRANSFERE = 7;
    public static final int NOMBRE_HEURE_MINIMALE_POUR_ACTIVITE = 1;
    public static final LocalDate DATE_DEBUT_ACTIVITE_AUTORISEE = LocalDate.parse("2020-04-01");
    public static final LocalDate DATE_FIN_ACTIVITE_AUTORISEE = LocalDate.parse("2022-04-01");

    public static final int NOMBRE_MINIMALE_HEURE_ACTIVITE_DE_GROUPE = 17;
}
