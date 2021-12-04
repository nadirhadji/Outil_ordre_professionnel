package Service;

import Entite.Activite;
import Utils.Constantes;
import java.util.HashMap;

public class ServiceRedondanceDate extends HashMap<String,Integer> {

    public void destructeur() {
        this.clear();
    }

    public void estUneDateDisponible(Activite activite)  {
        int nombreHeuresAconsiderer = obtenirNombreHeuresAconsiderer(activite);
        if ( nombreHeuresAconsiderer == 0 )
            activite.ignorerActivite();
        else if (nombreHeuresAconsiderer > 0)
            activite.sauvegarderHeures(nombreHeuresAconsiderer);
    }

    public int obtenirNombreHeuresAconsiderer(Activite activite) {
        String date = activite.obtenirDate();
        int nombreHeures = activite.obtenirHeures();
        return obtenirNombreHeuresAconsiderer(date,nombreHeures);
    }

    public int obtenirNombreHeuresAconsiderer(String date, int heuresNouvelleActivite) {

        if ( this.containsKey(date) ) {
            int nombreHeuresPourDate = this.get(date);
            int nombreHeuresRestante = Constantes.NOMBRE_HEURE_ACTIVITE_MAX_PAR_JOUR - nombreHeuresPourDate;

            if ( nombreHeuresPourDate == Constantes.NOMBRE_HEURE_ACTIVITE_MAX_PAR_JOUR)
                return 0;

            else if (nombreHeuresRestante >= heuresNouvelleActivite) {
                this.replace(date,nombreHeuresPourDate+heuresNouvelleActivite);
                return heuresNouvelleActivite;
            }

            else  {
                this.replace(date,Constantes.NOMBRE_HEURE_ACTIVITE_MAX_PAR_JOUR);
                return nombreHeuresRestante;
            }
        }
        else {
            this.put(date,heuresNouvelleActivite);
            return heuresNouvelleActivite;
        }
    }
}
