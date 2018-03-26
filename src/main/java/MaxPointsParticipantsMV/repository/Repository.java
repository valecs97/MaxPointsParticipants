package MaxPointsParticipantsMV.repository;


import MaxPointsParticipantsMV.model.Consultation;
import MaxPointsParticipantsMV.model.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Repository {

    private String patientsFileName; // list of patients
    private String consultationsFileName; // list of consultation

    private ArrayList<Consultation> consultationList;
    private ArrayList<Patient> patientList;

    public Repository(String patients, String consultations) throws IOException {
        this.patientsFileName = patients;
        this.consultationsFileName = consultations;

        consultationList = new ArrayList<Consultation>();
        patientList = new ArrayList<Patient>();
        getConsultationList();
        getPatientList();
    }

    private String[] getStringFromFile(String fileName) throws IOException {
        int n = 0;
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        while ((in.readLine()) != null) {
            n++;
        }
        in.close();
        String[] la = new String[n];
        String s;
        int i = 0;
        in = new BufferedReader(new FileReader(fileName));
        while ((s = in.readLine()) != null) {
            la[i] = s;
            i++;
        }
        in.close();
        return la;
    }

    private void getPatientList() throws IOException {
        String[] tokens = getStringFromFile(patientsFileName);
        String tok;
        String[] pat;
        int i = 0;
        while (i < tokens.length) {
            tok = tokens[i];
            pat = tok.split(",");
            patientList.add(new Patient(pat[0], pat[1], pat[2]));
            i = i + 1;
        }
    }

    private void getConsultationList() throws IOException {
        String[] tokens = getStringFromFile(consultationsFileName);
        String tok;
        String[] cons;
        String[] meds;
        List<String> med = new ArrayList<String>();
        int i = 0;
        while (i < tokens.length) {
            tok = tokens[i];
            cons = tok.split(",");
            meds = cons[3].split("\\+");
            Consultation consultation = new Consultation(cons[0], cons[1], cons[2], med, cons[4]);
            for (int j = 0; j < meds.length - 1; j++) {
                consultation.getMeds().add(meds[j]);
            }
            consultationList.add(consultation);
            i = i + 1;
        }
    }

    public void addPatient(Patient p) throws IOException        // save to file
    {
        patientList.add(p);
        FileWriter fw = new FileWriter(patientsFileName);
        PrintWriter out = new PrintWriter(fw);
        for (Patient aPatientList : patientList) out.println(aPatientList.toString());
        out.close();
    }

    public void addConsultation(Consultation c) throws IOException        // save to file
    {
        consultationList.add(c);
        FileWriter fw = new FileWriter(consultationsFileName);
        PrintWriter out;
        out = new PrintWriter(fw);
        for (Consultation aConsultationList : consultationList) out.println(aConsultationList.toString());
        out.close();
    }

    public void removePatient(Patient p) throws IOException {
        patientList.remove(p);
        FileWriter fw = new FileWriter(patientsFileName);
        PrintWriter out = new PrintWriter(fw);
        for (Patient aPatientList : patientList) out.println(aPatientList.toString());
        out.close();
    }

    public Patient findPatientByID(String id) {
        for (Patient aPatientList : patientList)
            if (aPatientList.getPatient_ID().equals(id))
                return aPatientList;
        return null;
    }

    public Consultation findConsulationByID(String id) {
        for (Consultation aConsultationList : consultationList)
            if (aConsultationList.getConsID().equals(id))
                return aConsultationList;
        return null;
    }


    public ArrayList<Consultation> getAllConsulations(){
        return consultationList;
    }

    public ArrayList<Patient> getAllPatients(){
        return patientList;
    }
}
