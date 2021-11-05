package Service;

public class VerificationFactory {

    public static InterfaceVerification obtenirInstance(String ordreProfessionel){
        InterfaceVerification interfaceVerification = switch (ordreProfessionel) {
            case "architectes" -> new ServiceValidationArchitecte();
            case "géologues" -> new ServiceValidationGeologue();
            case "psychologues" -> new ServiceValidationPsychologues();
            default -> null;
        };
        return interfaceVerification;
    }
}
