package Entite;

/**
 * classe qui transforme les activite de l'object JSOn en String avec des
 * constructeurs
 */
public class Activite {

    private String description;
    private String categorie;
    private int heures;
    private String date;
    private boolean ignoree;

    
    public Activite(String description, String categorie, int heures,
                    String date) {
        this.description = description;
        this.categorie = categorie;
        this.heures = heures;
        this.date = date;
        this.ignoree = false;
    }

    public String obtenirDescription () {
        return this.description;
    }

    public String obtenirCategorie() {
        return categorie;
    }

    public int obtenirHeures (){
        return this.heures;
    }

    public String obtenirDate (){
        return  this.date;
    }

    public void sauvegarderHeures (int heures){
        this.heures = heures;
    }

    /**
     *verifie si l'activité est à ignorer: quand l'activité est déclarée à
     * l'extérieur de 2020/04/01-2022/04/01, si l'activité est dans une
     * catégorie non reconnue ou si l'activité possède une heure inferieur a 1
     * @return this.Ignoree
     */
    public boolean estIgnoree(){
        return this.ignoree;
    }

    public void ignorerActivite () {
        this.ignoree = true;
    }
}
