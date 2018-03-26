package MaxPointsParticipantsMV.validator;

import MaxPointsParticipantsMV.exceptions.ConsultationException;

public class ConsultationValidation {

    public static void emptyString(String string) throws ConsultationException {
        if (string.length() == 0) {
            throw new ConsultationException("One of the required fields is empty!");
        }
    }
}
