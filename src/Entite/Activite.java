package Entite;

import java.time.LocalDate;

/**
 * classe qui transforme les activite de l'object JSOn en String avec des
 * constructeurs
 */
public class Activite {

    private String description;
    private String categorie;
    private int heures;
    private LocalDate date;
    private boolean ignoree;

    /**
     *
     * @param description
     * @param categorie
     * @param heures
     * @param date
     */
    public Activite(String description, String categorie, int heures, LocalDate date) {
        this.description = description;
        this.categorie = categorie;
        this.heures = heures;
        this.date = date;

    }

    /**
     * @return this.description
     */
    public String obtenirDescription () {
        return this.description;
    }

    /**
     * @return categorie
     */
    public String obtenirCategorie() {
        return categorie;
    }

    /**
     * @return this.heures
     */
    public int obtenirHeures (){
        return this.heures;
    }

    /**
     * @return this.date
     */
    public LocalDate obtenirDate (){
        return  this.date;
    }

    /**
     *sauvegarde les heures entrées pour plus tard les valider
     * @param heures
     */
    public void sauvegarderHeures (int heures){
        this.heures = heures;
    }

    /**
     *verifie si l'activité est à ignorer: quand l'activité est  déclarée à
     * l'extérieur de 2020/04/01-2022/04/01, si l'activité est dans une
     * catégorie non reconnue ou si l'activité possède une heure inferieur a 1
     * @return this.Ignoree
     */
    public boolean estIgnoree (){
        return this.ignoree;
    }

    /**
     * ignore l'activite quand elle est à ignorer
     */
    public void ignorerActivite () {
        this.ignoree = true;
    }
}
