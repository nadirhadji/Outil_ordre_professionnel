package Entite;

import Utils.ConstanteStatistique;
import Utils.Constantes;
import org.json.simple.JSONObject;

public class LigneStatistique extends JSONObject {

    public LigneStatistique(String cle, int total) {
        this.put(cle,total);
    }

    public static String getDescriptionGeneral(String cle) {
        return switch (cle) {
            case "total_traite" -> ConstanteStatistique.LABEL_TOTAL_DECLARATION_TRAITE;
            case "total_complet" -> ConstanteStatistique.LABEL_TOTAL_DECLARATION_COMPLETE;
            case "total_incomplet" -> ConstanteStatistique.LABEL_TOTAL_DECLARATION_INVALIDE;
            case "total_homme" -> ConstanteStatistique.LABEL_TOTAL_DECLARATION_SEXE_HOMME;
            case "total_femme" -> ConstanteStatistique.LABEL_TOTAL_DECLARATION_SEXE_FEMME;
            case "total_non_binaire" -> ConstanteStatistique.LABEL_TOTAL_DECLARATION_SEXE_NON_BINAIRE;
            case "total_activite" -> ConstanteStatistique.LABEL_TOTAL_ACTIVITE;
            case "total_permis_invalide" -> ConstanteStatistique.LABEL_TOTAL_PERMIS_INVALIDE;
            default -> "Erreur";
        };
    }

}
