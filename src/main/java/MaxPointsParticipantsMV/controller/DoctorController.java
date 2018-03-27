package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.ConsultationException;
import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Consultation;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import MaxPointsParticipantsMV.validator.ConsultationValidation;
import MaxPointsParticipantsMV.validator.PatientValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    private Repository rep;

    /**
     * Constructors
     */

    public DoctorController(Repository rep) {
        this.rep = rep;
        // Get list from file in order to avoid duplicates.
    }

    /**
     * Others
     */
    public void addPatient(Patient p) throws PatientException, IOException {
        if (p.getName() != null && p.getSSN() != null && p.getAddress() != null) {
            PatientValidation.nameValidate(p.getName());
            PatientValidation.ssnValidate(p.getSSN());
            PatientValidation.addressValidate(p.getAddress());
        } else {
            throw new PatientException("Null fields");
        }
        if (rep.findPatientByID(p.getPatient_ID()) != null)
            throw new PatientException("SSN is not unique!");
        rep.addPatient(p);
    }

    public void removePatient(String id) throws IOException {
        Patient p = rep.findPatientByID(id);
        rep.removePatient(p);
    }

    public void removeConsulation (String id) throws  IOException{
        Consultation c = rep.findConsulationByID(id);
        rep.removeConsulation(c);
    }

    // adding of a new consultation for a patient (consultation date,
    // diagnostic, prescription drugs)

    public void addConsultation(String consID, String patientSSN, String diag,
                                List<String> meds, String date) throws ConsultationException, IOException {
        //1
        if (meds == null)
            //2
            throw new ConsultationException("meds is null");
        //3
        if (consID != null && patientSSN != null
                && diag != null && meds.size() != 0
                && rep.findPatientByID(patientSSN) != null
                && rep.findConsulationByID(consID) == null) {
            //4
            ConsultationValidation.emptyString(consID);
            //5
            ConsultationValidation.emptyString(diag);
            //6
            for (String med : meds)
                //7
                ConsultationValidation.emptyString(med);
            //8
            ConsultationValidation.emptyString(date);

            Consultation c = new Consultation(consID, patientSSN, diag, meds, date);
            //9
            rep.findPatientByID(patientSSN).incrementConsNum();
            rep.addConsultation(c);
        //10
        } else {
            throw new ConsultationException("invalid arguments");
        }

    }

    public List<Patient> getPatientsWithDisease(String disease) throws PatientException {
        List<Consultation> c = rep.getAllConsulations();
        List<Patient> p = new ArrayList<Patient>();
        if (disease != null) {
            if (disease.length() == 0) {
                throw new PatientException("Empty disease provided");
            }
            int chk = 1;

            for (Consultation aC : c) {
                if (aC.getDiag().toLowerCase()
                        .contains(disease.toLowerCase())) // so that it is case
                // insensitive
                {
                    for (Patient aP : p) {
                        if (aP.getSSN().equals(aC.getPatientSSN())) {
                            chk = aP.getConsNum();
                        }
                    }

                    if (chk == 1) {
                        p.add(rep.findPatientByID(aC.getPatientSSN())); // get
                    }
                    chk = 1;
                }
            }

            // Sort the list

            Patient paux;

            for (int i = 0; i < p.size(); i++)
                for (int j = i + 1; j < p.size() - 1; j++)
                    if (p.get(j - 1).getConsNum() < p.get(j).getConsNum()) {
                        paux = p.get(j - 1);
                        p.set(j - 1, p.get(j));
                        p.set(j, paux);
                    }
        } else {
            throw new PatientException("Null disease parameter provided");
        }
        return p;
    }


}
