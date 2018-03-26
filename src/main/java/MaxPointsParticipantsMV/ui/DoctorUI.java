package MaxPointsParticipantsMV.ui;

import MaxPointsParticipantsMV.controller.DoctorController;
import MaxPointsParticipantsMV.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorUI {
    private DoctorController ctrl;
    private Scanner in;

    public DoctorUI(DoctorController ctrl) {
        this.ctrl = ctrl;
        this.in = new Scanner(System.in);
    }

    private void printMenu() {
        String menu;
        menu = "PatientsManagement Menu: \n";
        menu += "\t 1 - to add a new patient; \n";
        menu += "\t 2 - to add a new consultation; \n";
        menu += "\t 3 - to list all the patients, having a certain disease; \n";
        menu += "\t 0 - exit \n";

        System.out.println(menu);
    }

    private void printMedsMenu() {
        String menu;
        menu = "Prescriptions Menu: \n";
        menu += "\t 1 - to add a new med; \n";
        menu += "\t 2 - to close the prescription; \n";

        System.out.println(menu);
    }

    private List<String> RunMeds() {
        List<String> meds = new ArrayList<String>();
        printMedsMenu();
        int cmd = in.nextInt();
        in.nextLine();

        while (cmd != 2) {
            if (cmd == 1) {
                System.out.println("Enter med:");
                String med = in.nextLine();
                meds.add(med);
                System.out.println(meds.toString());
            }

            printMedsMenu();
            cmd = in.nextInt();
            in.nextLine();
        }
        return meds;
    }

    public void Run() {
        printMenu();
        int cmd = in.nextInt();
        in.nextLine();
        //System.out.println(Integer.toString(c));
        while (cmd != 0) {
            if (cmd == 1) {
                Patient p;
                System.out.println("Enter CNP:");
                String cnp = in.nextLine();
                System.out.println("Enter name:");
                String name = in.nextLine();
                System.out.println("Enter address:");
                String address = in.nextLine();
                p = new Patient(name, cnp, address);
                try {
                    ctrl.addPatient(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (cmd == 2) {
                System.out.println("Enter the PatientSSN:");
                String patientSSN = in.nextLine();
                //Consultation c = null;
                System.out.println("Enter the consID:");
                String consID = in.nextLine();
                System.out.println("Enter the diag:");
                String diag = in.nextLine();
                System.out.println("Introduce the prescripted meds:");
                List<String> meds = RunMeds();
                System.out.println("Date:");
                String date = in.nextLine();
//				c = new Consultation(consID, patientSSN, diag, meds, date);
                try {
                    ctrl.addConsultation(consID, patientSSN, diag, meds, date);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("> Consultation (" + consID + ") has been successfully added.");
            }
            if (cmd == 3) {
                System.out.println("Enter the filtering disease:");
                String disease = in.nextLine();
                try {
                    for (Patient p : ctrl.getPatientsWithDisease(disease)) {
                        System.out.println(p.toString());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            printMenu();
            cmd = in.nextInt();
            in.nextLine();
        }
    }
}

