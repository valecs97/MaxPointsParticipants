package MaxPointsParticipantsMV.model;

import java.util.ArrayList;
import java.util.List;

public class Consultation {
    private String consID;
    private String PatientSSN;
    private String diag;
    private List<String> meds;
    private String consultation_date;

    public Consultation() {
        this.consID = "";
        this.PatientSSN = "";
        this.diag = "";
        this.meds = new ArrayList<String>();
        this.consultation_date = "";
    }

    public Consultation(String consID, String PatientSSN, String diag, List<String> meds, String date) {
        this.consID = consID;
        this.PatientSSN = PatientSSN;
        this.diag = diag;
        this.meds = meds;
        this.consultation_date = date;
    }

    public String getConsID() {
        return consID;
    }

    public String getPatientSSN() {
        return PatientSSN;
    }

    public String getDiag() {
        return diag;
    }

    public List<String> getMeds() {
        return meds;
    }

    public String toString() {
        StringBuilder res;
        res = new StringBuilder(consID + "," + PatientSSN + ',' + diag + ",");

        for (String med : meds) {
            res.append(med).append(",");
        }

        res.append(consultation_date);

        return res.toString();

    }
}
