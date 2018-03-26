package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.Before;
import org.junit.Test;

public class BVAPatientTest {

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
    public void BoundariesTest() {
        Patient p1 = new Patient("Valid name","123","Valid adress");
        Patient p2 = new Patient("Valid name","12345612301234","Valid adress");
        Patient p3 = new Patient("Valid name","1234561230123","Valid adress");
        try{
            ctrl.addPatient(p1);
            assert (false);
        } catch (PatientException e){
        }
        try{
            ctrl.addPatient(p2);
            assert (false);
        } catch (PatientException e){
        }
        try {
            ctrl.addPatient(p3);
            ctrl.removePatient("Valid name");
        } catch (PatientException e) {
            assert (false);
        }
    }
}