package Entite;

import java.time.LocalDate;

public class Activite {

    private String description;
    private String categorie;
    private int heures;
    private LocalDate date;
    private boolean ignoree;

    public String obtenirDescription () {
        return this.description;
    }
    public String obtenirCategorie() {
        return categorie;
    }
    public int obtenirHeures (){
        return this.heures;
    }
    public LocalDate obtenirDate (){
        return  this.date;
    }
    public void sauvegarderHeures (int heures){
        this.heures = heures;
    }
    public boolean estIgnoree (){
        return this.ignoree;
    }
    public void ignorerActivite () {
        this.ignoree = true;
    }
}
