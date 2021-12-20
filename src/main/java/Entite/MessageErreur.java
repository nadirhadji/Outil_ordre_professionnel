package Entite;

public class MessageErreur {

    private final int status;
    private final int code;
    private final String erreur;

    public MessageErreur(int status, int code, String erreur) {
        this.status = status;
        this.code = code;
        this.erreur = erreur;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public String getErreur() {
        return erreur;
    }
}
