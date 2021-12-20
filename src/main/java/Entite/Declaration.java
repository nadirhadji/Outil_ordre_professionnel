package Entite;

import Exception.CleJSONInexistanteException;
import Service.ServiceDeclarationJSON;
import Service.ServiceMessages;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import java.util.List;

/**
 * transforme les JSON object en String par des constructeurs pour qu'on puisse
 * utiliser ces Strings tout au long du programme sans qu'on ne traduise chaque
 * fois l'objet JSON
 */
public class Declaration {

    private String numeroDePermis;
    private String nom;
    private String prenom;
    private int sexe;
    private String cycle;
    private String ordre;
    private int heuresTransfereesDuCyclePrecedent;
    private List<Activite> activites;

    //Constructeur pour les testes
    public Declaration(String nom, String prenom, int sexe,
                       String numeroDePermis, String cycle, String ordre,
                       int heuresTransfereesDuCyclePrecedent,
                       List<Activite> activites) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.numeroDePermis = numeroDePermis;
        this.cycle = cycle;
        this.ordre = ordre;
        this.heuresTransfereesDuCyclePrecedent = heuresTransfereesDuCyclePrecedent;
        this.activites = activites;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public void setOrdre(String ordre){
        this.ordre = ordre;
    }

    public void setHeuresTransfereesDuCyclePrecedent(int heuresTransfereesDuCyclePrecedent){
        this.heuresTransfereesDuCyclePrecedent = heuresTransfereesDuCyclePrecedent;
    }

    public  void setActivites(List<Activite> activites){
        this.activites = activites;
    }

    public String obtenirNom() {
        return this.nom;
    }

    public String obtenirPrenom() {
        return this.prenom;
    }

    public int obtenirSexe() {
        return this.sexe;
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

    public String obtenirOrdre() {
        return this.ordre;
    }

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