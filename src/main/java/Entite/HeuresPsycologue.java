package Entite;

public class HeuresPsycologue {

    private int heuresConference;
    private int heuresCours;
    private int heuresAutreActivitesPsy;

    public HeuresPsycologue() {
        this.heuresConference = 0;
        this.heuresCours = 0;
        this.heuresAutreActivitesPsy = 0;
    }

    public HeuresPsycologue(int heuresConference, int heuresCours, int heuresAutreActivitesPsy) {
        this.heuresConference = heuresConference;
        this.heuresCours = heuresCours;
        this.heuresAutreActivitesPsy = heuresAutreActivitesPsy;
    }

    public int obtenirHeuresConference() {
        return heuresConference;
    }

    public void incrementerHeuresConference(int heuresConference) {
        this.heuresConference += heuresConference;
    }

    public int obtenirHeuresCours() {
        return heuresCours;
    }

    public void incrementerHeuresCours(int heuresCours) {
        this.heuresCours += heuresCours;
    }

    public int obtenirHeuresAutreActivitesPsy() {
        return heuresAutreActivitesPsy;
    }

    public void incrementerHeuresAutreActivitesPsy(int heuresAutreActivitesPsy) {
        this.heuresAutreActivitesPsy += heuresAutreActivitesPsy;
    }
}
