package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.Before;
import org.junit.Test;

public class DoctorControllerTest {

    private Repository repo;
    private DoctorController ctrl;
    @Before
    public void setUp() {
        String patients = "FilePatients.txt";
        String consultations = "FileConsultations.txt";
        repo = new Repository(patients, consultations);
        ctrl = new DoctorController(repo);
    }

    @Test
    public void addPatient() {
        Patient p = new Patient("Alecs","3210987654321","Cluj Napoca");
        try {
            ctrl.addPatient(p);
            ctrl.removePatient(p.getName());
        } catch (PatientException e) {
            assert (false);
        }

    }
}