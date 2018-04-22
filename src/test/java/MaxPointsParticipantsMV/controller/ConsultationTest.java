package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.ConsultationException;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ConsultationTest {

    private Repository repo;
    private DoctorController ctrl;
    private Patient p;

    @Before
    public void setUp() throws IOException {
        String patients = "FilePatientsTest.txt";
        String consultations = "FileConsultationsTest.txt";
        repo = new Repository(patients, consultations);
        ctrl = new DoctorController(repo);
        p = new Patient("Alecss","3210987664321","Cluj Napoca");
        try {
            ctrl.addPatient(p);

        } catch (Exception e) {
            assert (false);
        }
    }

    @After
    public void tearDown() throws IOException {
        ctrl.removePatient(p.getPatient_ID());
    }

    @Test
    public void addConsultationNullMed() throws IOException {
        try{
            ctrl.addConsultation("4325",p.getSSN(),"Cancer", null,"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
    }

    @Test
    public void addConsultationNullPatient() throws IOException {
        try{
            ctrl.addConsultation("4325",null,"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
    }

    @Test
    public void addConsultationEmptyID() throws IOException {
        try{
            ctrl.addConsultation("",p.getSSN(),"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
    }

    @Test
    public void addConsultationEmptyPatient() throws IOException {
        try{
            ctrl.addConsultation("4325","","Cancer", null,"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
    }

    @Test
    public void addConsultationEmptyMeds() throws IOException {
        try{
            ctrl.addConsultation("4325",p.getPatient_ID(),"Cancer", new ArrayList<String>(Collections.singletonList("")),"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
    }

    @Test
    public void addConsultationEmptyDate() throws IOException {
        try{
            ctrl.addConsultation("4325",p.getPatient_ID(),"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"");
            assert(false);
        } catch (ConsultationException ignored){}
    }

    @Test
    public void addConsultationInexistentPatient() throws IOException {
        try{
            ctrl.addConsultation("4325","134","Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"");
            assert(false);
        } catch (ConsultationException ignored){}
    }

}