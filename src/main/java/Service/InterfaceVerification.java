package Service;

import Entite.Activite;
import Entite.Declaration;

import java.util.List;

public interface InterfaceVerification {
    void verifiationGeneral(Declaration declaration);
    void verificationDesActivite(Declaration declaration);
    void verifierSpecifiqueOrdre(Declaration declaration);
}
