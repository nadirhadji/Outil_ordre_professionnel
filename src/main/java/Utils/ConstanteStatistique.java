package Utils;

import Entite.Categorie;

public class ConstanteStatistique {

    private static final String DEBUT_DE_LABEL = "Total des déclarations ";

    public static final String LABEL_TOTAL_DECLARATION_TRAITE =
            DEBUT_DE_LABEL+"traitée";

    public static final String LABEL_TOTAL_DECLARATION_COMPLETE =
            DEBUT_DE_LABEL+"complété";

    public static final String LABEL_TOTAL_DECLARATION_INVALIDE =
            DEBUT_DE_LABEL+"incompléte ou invalide";

    public static final String LABEL_TOTAL_DECLARATION_SEXE_HOMME =
            DEBUT_DE_LABEL+"faite par des hommes";

    public static final String LABEL_TOTAL_DECLARATION_SEXE_FEMME =
            DEBUT_DE_LABEL+"faite par des femmes";

    public static final String LABEL_TOTAL_DECLARATION_SEXE_NON_BINAIRE =
            DEBUT_DE_LABEL+"faite par des individus non binaire";

    public static final String LABEL_TOTAL_ACTIVITE = "Total des activités traitée";

    public static final String LABEL_GENERAL_CATEGORIE = "Total des activites faite dans la categorie : ";

    public static final String LABEL_TOTAL_CATEGORIE_ATELIER =
            LABEL_GENERAL_CATEGORIE+Categorie.ATELIER;

    public static final String LABEL_TOTAL_CATEGORIE_COLLOQUE = 
            LABEL_GENERAL_CATEGORIE+Categorie.COLLOQUE;

    public static final String LABEL_TOTAL_CATEGORIE_CONFENCE =
            LABEL_GENERAL_CATEGORIE+Categorie.CONFERENCE;

    public static final String LABEL_TOTAL_CATEGORIE_COURS =
            LABEL_GENERAL_CATEGORIE+Categorie.COURS;

    public static final String LABEL_TOTAL_CATEGORIE_PRESENTATION =
            LABEL_GENERAL_CATEGORIE+Categorie.PRESENTATION;

    public static final String LABEL_TOTAL_CATEGORIE_PROJET =
            LABEL_GENERAL_CATEGORIE+Categorie.PROJET_DE_RECHERCHE;

    public static final String LABEL_TOTAL_CATEGORIE_DISCUSSION =
            LABEL_GENERAL_CATEGORIE+Categorie.GROUPE_DE_DISCUSSION;

    public static final String LABEL_TOTAL_CATEGORIE_LECTURE =
            LABEL_GENERAL_CATEGORIE+Categorie.LECTURE_DIRIGEE;

    public static final String LABEL_TOTAL_SEMINAIRE =
            LABEL_GENERAL_CATEGORIE+Categorie.SEMINAIRE;

    public static final String LABEL_TOTAL_CATEGORIE_REDACTION =
            LABEL_GENERAL_CATEGORIE+Categorie.REDACTION_PROFESSIONNELLE;

    public static final String LABEL_GENERAL_COMPLET_ORDRE =
            "Total des déclarations complete pour l'ordre des :";

    public static final String LABEL_TOTAL_VALIDE_ARCHITECTE =
            LABEL_GENERAL_COMPLET_ORDRE+ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES;

    public static final String LABEL_TOTAL_VALIDE_GEOLOGUE =
            LABEL_GENERAL_COMPLET_ORDRE+ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES;

    public static final String LABEL_TOTAL_VALIDE_PSHYCOLOGUE =
            LABEL_GENERAL_COMPLET_ORDRE+ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES;

    public static final String LABEL_TOTAL_VALIDE_PODIATRE =
            LABEL_GENERAL_COMPLET_ORDRE+ConstantesPodiatre.PODIATRE;

    public static final String LABEL_GENERAL_INCOMPLET_ORDRE =
            "Total des déclarations incomplete pour l'ordre des :";

    public static final String LABEL_TOTAL_INCOMPLET_ARCHITECTE =
            LABEL_GENERAL_INCOMPLET_ORDRE+ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES;

    public static final String LABEL_TOTAL_INCOMPLET_GEOLOGUE =
            LABEL_GENERAL_INCOMPLET_ORDRE+ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES;

    public static final String LABEL_TOTAL_INCOMPLET_PSHYCOLOGUE =
            LABEL_GENERAL_INCOMPLET_ORDRE+ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES;

    public static final String LABEL_TOTAL_INCOMPLET_PODIATRE =
            LABEL_GENERAL_INCOMPLET_ORDRE+ConstantesPodiatre.PODIATRE;

    public static final String LABEL_TOTAL_PERMIS_INVALIDE =
            "Total des déclarations avec un permis Invalide";

    public static final String CLE_DECLARATION_TRAITE = "total_traitée";

    public static final String CLE_DECLARATION_COMPLETE = "total_complet";

    public static final String CLE_DECLARATION_INVALIDE = "total_invalide";

    public static final String CLE_HOMME = "total_homme";

    public static final String CLE_FEMME = "total_femme";

    public static final String CLE_NON_BINAIRE = "total_non_binaire";

    public static final String CLE_ACTIVITE = "total_activites";

    public static final String CLE_ATELIER = "total_atelier";

    public static final String CLE_COLLOQUE = "total_colloque";

    public static final String CLE_CONFERENCE = "total_conference";

    public static final String CLE_COURS = "total_cours";

    public static final String CLE_PRESENTATION = "total_presentation";

    public static final String CLE_PROJET = "total_projet";

    public static final String CLE_DISCUSSION = "total_discussion";

    public static final String CLE_LECTURE = "total_lecture";

    public static final String CLE_SEMINAIRE = "total_seminaire";

    public static final String CLE_REDACTION = "total_redaction";

    public static final String CLE_COMPLET_ARCHI = "total_complet_archi";

    public static final String CLE_COMPLET_GEOLOGUE = "total_complet_geologue";

    public static final String CLE_COMPLET_PSYCHO = "total_complet_psychologue";

    public static final String CLE_COMPLET_PODIATRE = "total_complet_podiatre";

    public static final String CLE_INCOMPLET_ARCHI = "total_incomplet_archi";

    public static final String CLE_INCOMPLET_GEOLOGUE = "total_incomplet_geologue";

    public static final String CLE_INCOMPLET_PSHYCO = "total_incomplet_pshycologue";

    public static final String CLE_INCOMPLET_PODIATRE = "total_incomplet_podiatre";

    public static final String CLE_PERMIS_INVALIDE = "total_permis_invalide";

}
