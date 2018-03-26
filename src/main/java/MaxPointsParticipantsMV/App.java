package MaxPointsParticipantsMV;

import MaxPointsParticipantsMV.controller.DoctorController;
import MaxPointsParticipantsMV.repository.Repository;
import MaxPointsParticipantsMV.ui.DoctorUI;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        String patients = "FilePatients.txt";
        String consultations = "FileConsultations.txt";
        Repository repo = null;
        try {
            repo = new Repository(patients, consultations);
        } catch (IOException e) {
            System.out.println("Problems at reading the file !");
        }
        DoctorController ctrl = new DoctorController(repo);

        DoctorUI console = new DoctorUI(ctrl);
        console.Run();
    }
}
