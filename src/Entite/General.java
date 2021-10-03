package Entite;

public class General {

    private String numero_de_permis;
    private String cycle;
    private int heures_transferees_du_cycle_precedent;
    private Activite[] activites;

    public void Declaraion (DeclarationJSON declarationJson){
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
    public Activite[] obtenirActivite (){
        return this.activites;
    }
}