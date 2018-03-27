package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.ConsultationException;
import MaxPointsParticipantsMV.exceptions.PatientException;
import MaxPointsParticipantsMV.model.Patient;
import MaxPointsParticipantsMV.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DoctorControllerTest {

    private Repository repo;
    private DoctorController ctrl;
    @Before
    public void setUp() throws IOException {
        String patients = "FilePatientsTest.txt";
        String consultations = "FileConsultationsTest.txt";
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

    @Test
    public void addConsultationValid() throws IOException {
        Patient p = new Patient("Alecss","3210987664321","Cluj Napoca");
        try {
            ctrl.addPatient(p);
            ctrl.addConsultation("4321",p.getSSN(),"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"27.03.2018");
            ctrl.removePatient(p.getPatient_ID());
            ctrl.removeConsulation("4321");
        } catch (ConsultationException e) {
            assert (false);
        } catch (PatientException e){
            assert (false);
        }
    }

    @Test
    public void addConsulationError() throws IOException {
        Patient p = new Patient("Alecss","3210987664321","Cluj Napoca");

        try {
            ctrl.addPatient(p);

        } catch (Exception e) {
            assert (false);
        }

        try{
            ctrl.addConsultation("4321",p.getSSN(),"Cancer", null,"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}

        try{
            ctrl.addConsultation("4321",null,"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
        try{
            ctrl.addConsultation("",p.getSSN(),"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
        try{
            ctrl.addConsultation("4321","","Cancer", null,"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
        try{
            ctrl.addConsultation("4321",p.getPatient_ID(),"Cancer", new ArrayList<String>(Collections.singletonList("")),"27.03.2018");
            assert(false);
        } catch (ConsultationException ignored){}
        try{
            ctrl.addConsultation("4321",p.getPatient_ID(),"Cancer", new ArrayList<String>(Collections.singletonList("Vape")),"");
            assert(false);
        } catch (ConsultationException ignored){}

        try {
            ctrl.removePatient(p.getPatient_ID());
        } catch (IOException e) {
            assert (false);
        }
    }
}