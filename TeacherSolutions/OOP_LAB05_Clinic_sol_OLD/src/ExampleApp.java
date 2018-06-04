import clinic.Clinic;


public class ExampleApp {

	public static void main(String[] args) {

		Clinic hospital = new Clinic();

		hospital.addPatient("Alice", "Green", "ALCGRN");
		hospital.addPatient("Robert", "Smith", "RBTSMT");
		hospital.addPatient("Steve", "Moore", "STVMRE");
		hospital.addPatient("Susan", "Madison", "SSNMDS");

		hospital.addDoctor("George", "Sun","SNUGRG", 14,"Physician");
		hospital.addDoctor("Kate", "Love", "LVOKTA",86,"Physician");

		try {
			hospital.assignPatientToDoctor("SSNMDS", 86);
			hospital.assignPatientToDoctor("ALCGRN", 14);
			hospital.assignPatientToDoctor("RBTSMT", 14);
			hospital.assignPatientToDoctor("STVMRE", 14);

			hospital.printDoctorOfPatient("SSNMDS");

			hospital.printPatientsOfDoctor(14);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			hospital.printPatientsOfDoctor(-1);
		} catch (Exception ex) {
			System.out.println("As expected we got the following exception for doctor -1:" + ex);
		}
	}

}
