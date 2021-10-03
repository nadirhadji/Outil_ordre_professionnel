import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lib.DeclarationJson;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;

public class Generale {
    private String numero_de_permis;
    private String cycle;
    private int heures_transferees_du_cycle_precedent;
    private Activite[] activites;

    public void Declaraion (DeclarationJson declarationJson){


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