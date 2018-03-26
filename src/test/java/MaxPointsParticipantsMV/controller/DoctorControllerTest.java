package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DoctorControllerTest {

    private Repository repo;
    private DoctorController ctrl;
    @Before
    public void setUp() throws IOException {
        String patients = "FilePatients.txt";
        String consultations = "FileConsultations.txt";
        repo = new Repository(patients, consultations);
        ctrl = new DoctorController(repo);
    }

    @Test
    public void addPatient() throws IOException {
        Patient p = new Patient("Alecss","3210987664321","Cluj Napoca");
        try {
            ctrl.addPatient(p);
            ctrl.removePatient(p.getPatient_ID());
        } catch (PatientException e) {
            assert (false);
        }

    }
}