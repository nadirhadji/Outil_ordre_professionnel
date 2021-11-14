package Utils;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constantes {

    public static String ARG0 = "";
    public static String ARG1 = "";
    public static List<Character> LISTE_LETTRE_NUMERO_DE_PERMIS =
            new ArrayList<>(Arrays.asList('A','R','S','Z'));
    public static final String CLE_NUMERO_DE_PERMIS = "numero_de_permis";
    public static final String CLE_CYCLE = "cycle";
    public static final String CLE_NOMBRE_HEURE_TRANSFERE = "heures_transferees_du_cycle_precedent";
    public static final String CLE_LISTE_ACTIVITE = "activites";
    public static final String CLE_DESCRIPTION = "description";
    public static final String CLE_CATEGORIE = "categorie";
    public static final String CLE_NOMBRE_HEURE = "heures";
    public static final String CLE_DATE = "date";
    public static final String CLE_ORDRE = "ordre";
    public static final String CYCLE_AUTORISE_POUR_ARCHITECTE = "2018-2020";
    public static final String CYCLE_AUTORISE_POUR_PSYCHOLOGUES = "2018-2023";
    public static final int NOMBRE_HEURES_MAXIMALE_A_TRANSFERE = 7;
    public static final int NOMBRE_HEURE_MAXIMALE_POUR_UNE_ACTIVITE = 10;
    public static final String cleReponseComplet = "complet";
    public static final String cleReponseErreur = "erreurs";
    public static final int MAXIMUM_HEURE_PRESENTATION_ARCHITECTE = 23;
    public static final int MAXIMUM_HEURE_PROJET_DE_RECHERCHER_ARCHITECTE = 23;
    public static final int MAXIMUM_HEURE_REDACTION_ARCHITECTE = 17;
}
