package Entite;

import java.util.ArrayList;
import java.util.List;

/**
 * transforme les JSON object en String par des constructeurs pour qu'on puisse
 * utiliser ces Strings tout au long du programme sans qu'on ne traduise chaque
 * fois l'objet JSON
 */
public class General {

    private String numeroDePermis;
    private String cycle;
    private int heuresTransfereesDuCyclePrecedent;
    private List<Activite> activites;

    /**
     * constructeur qui transforme le document JSON dans une liste d'activités
     * @param declarationJson
     */
    public General (DeclarationJSON declarationJson){
        this.activites = new ArrayList<>();
    }

    /**
     * @return this.numeroDePermis
     */
    public String obtenirNumeroDePermis (){
        return this.numeroDePermis;
    }

    /**
     * @return this.cycle
     */
    public String obtenirCycle (){
        return this.cycle;
    }

    /**
     * @return this.heuresTransfereesDuCyclePrecedent
     */
    public int obtenirHeurestransfere (){
        return this.heuresTransfereesDuCyclePrecedent;
    }

    /**
     * @return this.activites
     */
    public List<Activite> obtenirActivites (){
        return this.activites;
    }

    public void soustraireAuNombreHeuresTransfere(int nombre) {
        heuresTransfereesDuCyclePrecedent = heuresTransfereesDuCyclePrecedent - nombre;
    }

    /**
     * transforme les heuresTransfereesDuCyclePrecedent a 7 chaque fois que
     * l'utilisateur entre un nombre superieur a 7
     */
    public void modifierNombreHeuresTransfereA7() {
        this.heuresTransfereesDuCyclePrecedent = 7;
    }

    /**
     * transforme les heuresTransfereesDuCyclePrecedent a 0 chaque fois que
     * l'utilisateur entre un nombre inferieur a 0
     */
    public void modifierNombreHeuresTransfereA0() {
        this.heuresTransfereesDuCyclePrecedent = 0;
    }
}