import java.util.Date;

public class Activite {
    private String description;
    private String categorie;
    private int heures;
    private Date date;
    private boolean estIgnoree;

    public void sauvegarderHeures (int heures){
        this.heures = heures;
    }
    public String obtenirDescription () {
        return this.description;
    }
    public int obtenirHeures (){
        return this.heures;
    }
    public boolean isEstIgnoree (){
        return this.estIgnoree;
    }
    public Date obtenirDate (){
        return  this.date;
    }
}
