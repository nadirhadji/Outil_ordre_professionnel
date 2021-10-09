package Utils;

import java.time.LocalDate;
import java.util.Date;

public class Constantes {

    public static final String FICHIER_ENTREE = "declaration.json";
    public static final String FICHIER_SORTIE = "reponse.json";

    public static final String cleNumeroDePermis = "numero_de_permis";
    public static final String cleCycle = "cycle";
    public static final String cleNombreHeureTransfere = "heures_transferees_du_cycle_precedent";
    public static final String cleListeActivite = "activites";
    public static final String cleDescription = "description";
    public static final String cleCategorie = "categorie";
    public static final String cleNombreHeure = "heures";
    public static final String cleDate = "date";

    public static final String CYCLE_AUTORISEE = "2020-2022";
    public static final int NOMBRE_HEURES_MAXIMALE_A_TRANSFERE = 7;
    public static final int NOMBRE_HEURE_MINIMALE_POUR_ACTIVITE = 1;
    public static final LocalDate DATE_DEBUT_ACTIVITE_AUTORISEE = LocalDate.parse("2020-04-01");
    public static final LocalDate DATE_FIN_ACTIVITE_AUTORISEE = LocalDate.parse("2022-04-01");

    public static final int MINIMUM_HEURE_POUR_UNE_DECLARATION = 40;
    public static final int MINIMUM_HEURE_ACTIVITE_DE_GROUPE = 17;
    public static final int MAXIMUM_HEURE_PRESENTATION = 23;
    public static final int MAXIMUM_HEURE_GROUPE_DE_DISCUSSION = 17;
    public static final int MAXIMUM_HEURE_PROJET_DE_RECHERCHER = 23;
    public static final int MAXIMUM_HEURE_REDACTION = 17;


}
