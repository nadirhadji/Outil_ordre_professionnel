package Entite;

public class MessageErreur {

    private final int code;
    private final String erreur;

    public MessageErreur(int code, String erreur) {
        this.code = code;
        this.erreur = erreur;
    }

    public int getCode() {
        return code;
    }

    public String getErreur() {
        return erreur;
    }
}
