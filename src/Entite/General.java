package Entite;

import java.util.ArrayList;
import java.util.List;

public class General {

    private String numeroDePermis;
    private String cycle;
    private int heuresTransfereesDuCyclePrecedent;
    private List<Activite> activites;

    public void General (DeclarationJSON declarationJson){
        this.activites = new ArrayList<>();
    }

    public String obtenirNumeroDePermis (){
        return this.numeroDePermis;
    }

    public String obtenirCycle (){
        return this.cycle;
    }

    public int obtenirHeurestransfere (){
        return this.heuresTransfereesDuCyclePrecedent;
    }

    public List<Activite> obtenirActivites (){
        return this.activites;
    }

    public void soustraireAuNombreHeuresTransfere(int nombre) {
        heuresTransfereesDuCyclePrecedent = heuresTransfereesDuCyclePrecedent - nombre;
    }

    public void modifierNombreHeuresTransfereA7() {
        this.heuresTransfereesDuCyclePrecedent = 7;
    }

    public void modifierNombreHeuresTransfereA0() {
        this.heuresTransfereesDuCyclePrecedent = 0;
    }
}