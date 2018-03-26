package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ECPatientTest {

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
    public void NullTest() throws IOException {
        Patient p1 = new Patient(null,"1234567890123","Valid adress");
        Patient p2 = new Patient("Valid name",null,"Valid adress");
        Patient p3 = new Patient("Valid name","1234567890123",null);
        Patient p4 = new Patient("Valid name","1234561230123","Valid adress");
        try{
            ctrl.addPatient(p1);
            assert (false);
        } catch (PatientException ignored){
        }
        try{
            ctrl.addPatient(p2);
            assert (false);
        } catch (PatientException ignored){
        }
        try{
            ctrl.addPatient(p3);
            assert (false);
        } catch (PatientException ignored){
        }
        try {
            ctrl.addPatient(p4);
            ctrl.removePatient(p4.getPatient_ID());
        } catch (PatientException e) {
            e.printStackTrace();
            assert (false);
        }

    }

    @Test
    public void EmptyStringTest() throws IOException {
        Patient p1 = new Patient("Valid name","1234561230123","");
        Patient p2 = new Patient("","1234561230123","Valid adress");
        try{
            ctrl.addPatient(p1);
            assert (false);
        } catch (PatientException ignored){
        }
        try{
            ctrl.addPatient(p2);
            assert (false);
        } catch (PatientException ignored){
        }
    }

    @Test
    public void IiligalCharacterTest() throws IOException {
        Patient p1 = new Patient("Valid n@me","1234561230123","Valid adress");
        try{
            ctrl.addPatient(p1);
            assert (false);
        } catch (PatientException ignored){
        }
    }
}