package Entite;

import Exception.CleJSONInvalideException;
import Exception.CleJSONInexistanteException;
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
    public Declaration(DeclarationJSON declarationJson){
        initialiserNumeroDePermis(declarationJson);
        initiliserCycle(declarationJson);
        initialiserOrdre(declarationJson);
        initialiserHeuresTransfere(declarationJson);
        initialiserActivites(declarationJson);
    }

    private void termierExecution(Exception e) {
        System.out.println(e.getMessage());
        Reponse.obtenirInstance().ajouterMessageErreur(e.getMessage());
        ServiceReponse.ecrireFichierDeSortie(Constantes.ARG1,Reponse.obtenirInstance());
        System.exit(1);
    }

    private void initialiserNumeroDePermis(DeclarationJSON declarationJson) {
        try {
            this.numeroDePermis = declarationJson.obtenirStringDeCle(
                    Constantes.CLE_NUMERO_DE_PERMIS);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void initiliserCycle(DeclarationJSON declarationJSON) {
        try {
            this.cycle = declarationJSON.obtenirStringDeCle(Constantes.CLE_CYCLE);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void initialiserOrdre( DeclarationJSON declarationJSON) {
        try {
            this.ordre = declarationJSON.obtenirStringDeCle(Constantes.CLE_ORDRE);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void initialiserHeuresTransfere( DeclarationJSON declarationJSON) {
        if( ordre.equals(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES)) {
            verifierHeureTransfereExistante(declarationJSON);
        }
        else {
            try {
                verifierHeuresTransfereInexistante(declarationJSON);
            } catch (CleJSONInexistanteException e) {
                termierExecution(e);
            }
        }
    }

    private void verifierHeureTransfereExistante(DeclarationJSON declarationJSON) {
        try {
            this.heuresTransfereesDuCyclePrecedent =
                    declarationJSON.obtenirIntDeCle(Constantes.CLE_NOMBRE_HEURE_TRANSFERE);
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
    }

    private void verifierHeuresTransfereInexistante(DeclarationJSON declarationJSON)
            throws CleJSONInexistanteException {
        if ( declarationJSON.contientCle(Constantes.CLE_NOMBRE_HEURE_TRANSFERE) ) {
            throw new CleJSONInexistanteException(
                    ServiceMessages.messageErreurHeureTranfereNonSupporte(ordre)
            );
        }
    }

    private void initialiserActivites( DeclarationJSON declarationJSON) {
        try {
            this.activites = declarationJSON.obtenirActivites();
        } catch (CleJSONInexistanteException e) {
            termierExecution(e);
        }
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