package Service;

import Entite.Declaration;
import Entite.Reponse;
import Utils.ConstantesGeologue;
import Utils.ConstantesPodiatre;

public class ServiceValidationPodiatre extends ServiceValidationGeologue {

    public void verifier(Declaration declaration) {
        verifiationGeneral(declaration);
        verificationDesActivite(declaration);
        //verifierSpecifiqueOrdre(declaration);
        verifierNombreHeuresTotaleDansDeclarationPodiatre();
    }

    @Override
    public void verifiationGeneral(Declaration declaration) {
        if (verifierCycleGeologue(declaration)) {
            ServiceValidationNumeroDePermis.podiatre(declaration.obtenirNumeroDePermis());
        }
    }

    /*############# Service.Verification du nombre totale d'heures ###################*/
    public void verifierNombreHeuresTotaleDansDeclarationPodiatre () {
        int nombreHeuresManquante = obtenirNombreHeuresManquantePodiatre();
        if (nombreHeuresManquante > 0) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageNombreHeuresTotalMoinsDe40(
                            nombreHeuresManquante));
        }
    }

    public int obtenirNombreHeuresManquantePodiatre () {
        int total = obtenirNombreTotalHeures();
        verifierMinimumHeureParGroupeDeCategorie();

        if (total >= ConstantesPodiatre.MINIMUM_HEURE_POUR_UNE_DECLARATION_PODIATRE)
            return 0;
        else
            return ConstantesPodiatre.MINIMUM_HEURE_POUR_UNE_DECLARATION_PODIATRE - total;
    }

}
