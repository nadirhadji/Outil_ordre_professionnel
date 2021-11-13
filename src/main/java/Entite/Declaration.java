package Entite;

import Exception.CleJSONInexistanteException;
import Service.ServiceJSON;
import Service.ServiceMessages;
import Service.ServiceReponse;
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
    private String cycle;
    private String ordre;
    private int heuresTransfereesDuCyclePrecedent;
    private List<Activite> activites;

    /**
     * constructeur qui transforme le document JSON dans une liste d'activités
     */
    public Declaration(ServiceJSON serviceJson) {
        initialiserNumeroDePermis(serviceJson);
        initiliserCycle(serviceJson);
        initialiserOrdre(serviceJson);
        initialiserHeuresTransfere(serviceJson);
        initialiserActivites(serviceJson);
    }

    private void termierExecution(Exception e) {
        System.out.println(e.getMessage());
        Reponse.obtenirInstance().ajouterMessageErreur(e.getMessage());
        ServiceReponse.ecrireFichierDeSortie(Constantes.ARG1,Reponse.obtenirInstance());
        System.exit(1);
    }

    private void initialiserNumeroDePermis(ServiceJSON serviceJson) {
        try {
            this.numeroDePermis = serviceJson.obtenirStringDeCle(
                    Constantes.CLE_NUMERO_DE_PERMIS);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void initiliserCycle(ServiceJSON serviceJson) {
        try {
            this.cycle = serviceJson.obtenirStringDeCle(Constantes.CLE_CYCLE);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void initialiserOrdre( ServiceJSON serviceJson) {
        try {
            this.ordre = serviceJson.obtenirStringDeCle(Constantes.CLE_ORDRE);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void initialiserHeuresTransfere( ServiceJSON serviceJson) {
        if( ordre.equals(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES)) {
            verifierHeureTransfereExistante(serviceJson);
        }
        else {
            try {
                verifierHeuresTransfereInexistante(serviceJson);
            } catch (CleJSONInexistanteException e) {
                termierExecution(e);
            }
        }
    }

    private void verifierHeureTransfereExistante(ServiceJSON serviceJson) {
        try {
            this.heuresTransfereesDuCyclePrecedent =
                    serviceJson.obtenirIntDeCle(Constantes.CLE_NOMBRE_HEURE_TRANSFERE);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void verifierHeuresTransfereInexistante(ServiceJSON serviceJson)
            throws CleJSONInexistanteException {
        if ( serviceJson.contientCle(Constantes.CLE_NOMBRE_HEURE_TRANSFERE) ) {
            throw new CleJSONInexistanteException(
                    ServiceMessages.messageErreurHeureTranfereNonSupporte(ordre)
            );
        }
    }

    private void initialiserActivites( ServiceJSON serviceJson) {
        try {
            this.activites = serviceJson.obtenirActivites();
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }
    public Declaration(){
        this.numeroDePermis = null;
        this.cycle = null;
        this.activites = null;
        this.heuresTransfereesDuCyclePrecedent = 0;
        this.ordre = null;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
    public void setNumeroDePermis(String numeroDePermis){
        this.numeroDePermis = numeroDePermis;
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