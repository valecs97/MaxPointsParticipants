package MaxPointsParticipantsMV.model;

public class Patient {
    private String Patient_ID;
    private String Name;
    private String SSN;
    private String Adress;
    private int ConsNum;

    /**
     * Constructors
     */

    public Patient() {
        Patient_ID = null;
        Name = null;
        SSN = null;
        Adress = null;
        ConsNum = -1;
    }

    public Patient(String Name, String SSN, String address) {
        this.Patient_ID = SSN;
        this.Name = Name;
        this.SSN = SSN;
        this.Adress = address;
        this.ConsNum = 0;
    }

    public String getPatient_ID() {
        return Patient_ID;
    }

    public String getName() {
        return Name;
    }

    public String getSSN() {
        return SSN;
    }

    public String getAddress() {
        return Adress;
    }

    public void incrementConsNum() {
        this.ConsNum++;
    }

    public int getConsNum() {
        return this.ConsNum;
    }

    /**
     * Others
     */
    public String toString() {
        return Name + "," + SSN + "," + Adress;
        //	return Name + "," + ConsNum;
    }
}
