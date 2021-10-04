package Entite;

import java.util.ArrayList;
import java.util.List;

public class General {

    private String numero_de_permis;
    private String cycle;
    private int heures_transferees_du_cycle_precedent;
    private List<Activite> activites;

    public void General (DeclarationJSON declarationJson){
        this.activites = new ArrayList<>();
    }

    public String obtenirNumeroDePermis (){
        return this.numero_de_permis;
    }
    public String obtenirCycle (){
        return this.cycle;
    }
    public int obtenirHeurestransfere (){
        return this.heures_transferees_du_cycle_precedent;
    }
    public List<Activite> obtenirActivites (){
        return this.activites;
    }

    public void modifierNombreHeuresTransfereA7() {
        this.heures_transferees_du_cycle_precedent = 7;
    }
}