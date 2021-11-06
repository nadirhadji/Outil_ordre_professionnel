package Utils;

import java.security.PublicKey;
import java.time.LocalDate;

public class Constantes {

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
    public static final int NOMBRE_HEURE_MINIMALE_POUR_ACTIVITE = 1;
    public static final LocalDate DATE_DEBUT_ACTIVITE_AUTORISEE = LocalDate.parse("2020-04-01");
    public static final LocalDate DATE_FIN_ACTIVITE_AUTORISEE = LocalDate.parse("2022-04-01");
    public static final String cleReponseComplet = "complet";
    public static final String cleReponseErreur = "erreurs";
    public static final int MINIMUM_HEURE_POUR_UNE_DECLARATION = 40;
    public static final int MINIMUM_HEURE_ACTIVITE_DE_GROUPE = 17;
    public static final int MAXIMUM_HEURE_PRESENTATION = 23;
    public static final int MAXIMUM_HEURE_GROUPE_DE_DISCUSSION = 17;
    public static final int MAXIMUM_HEURE_PROJET_DE_RECHERCHER = 23;
    public static final int MAXIMUM_HEURE_REDACTION = 17;
    public static final int MINIMUM_HEURE_FORMATION_CONTINUE = 90;
    public static final int MINIMUM_HEURE_COURS = 25;
    public static final int MAXIMUM_HEURE_CONFERENCE = 15;
    public static final LocalDate DATE_DEBUT_ACTIVITE_PSYCHOLOGUE_AUTORISEE =
            LocalDate.parse("2018-01-01");
    public static final LocalDate DATE_FIN_ACTIVITE_PSYCHOLOGUE_AUTORISEE =
            LocalDate.parse("2023-01-01");
}
