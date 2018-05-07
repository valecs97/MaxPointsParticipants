package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.ConsultationException;
import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Consultation;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncrementIntegrationTest {
    private Repository repo;
    private DoctorController ctrl;
    private Patient p;
    private Consultation c;

    @Before
    public void setUp() throws IOException {
        String patients = "FilePatientsTest.txt";
        String consultations = "FileConsultationsTest.txt";
        repo = new Repository(patients, consultations);
        ctrl = new DoctorController(repo);
    }

    @After
    public void tearDown() throws IOException {
        if (p!=null)
            ctrl.removePatient(p.getPatient_ID());
        if (c!=null)
            ctrl.removeConsulation("4321");

    }

    @Test
    public void integrationA() throws IOException {
        functionA();
    }

    @Test
    public void integrationB() throws IOException {
        functionA();
        functionB();
    }

    @Test
    public void integrationC() throws IOException {
        functionA();
        functionB();
        functionC();
    }

    private void functionA() throws IOException {
        p = new Patient("Alecss","3210987664321","Cluj Napoca");
        try {
            ctrl.addPatient(p);

        } catch (PatientException e) {
            assert (false);
        }
    }

    private void functionB() throws IOException {
        try {
            c = new Consultation();
            ctrl.addConsultation("4321",p.getPatient_ID(),"Vape", new ArrayList<String>(Collections.singletonList("Vape123")),"27.03.2018");        } catch (ConsultationException e) {
            assert (false);
        }
    }

    private void functionC() {
        try{
            List<Patient> list = ctrl.getPatientsWithDisease("Vape");
            assert (list.size()==1);
            assert (list.get(0).getPatient_ID().equals(p.getPatient_ID()));
        } catch (PatientException e) {
            assert (false);
        }
    }
}
