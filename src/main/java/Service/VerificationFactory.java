package Service;

public class VerificationFactory {

    public static InterfaceVerification obtenirInstance(String ordre, String cycle) {
        ServiceRedondanceDate serviceRedondance = new ServiceRedondanceDate();
        ServiceValidationActivite serviceActivite = new ServiceValidationActivite(ordre,cycle);
        return switch (ordre) {
            case "architectes" -> new ServiceValidationArchitecte(serviceRedondance,serviceActivite);
            case "géologues"  -> new ServiceValidationGeologue(serviceRedondance,serviceActivite);
            case "psychologues" -> new ServiceValidationPsychologues(serviceRedondance,serviceActivite);
            default -> null;
        };
    }
}
