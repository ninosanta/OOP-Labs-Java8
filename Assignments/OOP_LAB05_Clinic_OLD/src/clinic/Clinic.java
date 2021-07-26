package clinic;

import java.io.IOException;
import java.util.Collection;

public class Clinic {

	public void addPatient(String first, String last, String ssn) {

	}

	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {

	}

	public Person getPatient(String ssn) throws NoSuchPatient {
		// TODO Auto-generated method stub
		return null;
	}

	public Doctor getDoctor(int docID) throws NoSuchDoctor {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {

	}

	/**
	 * returns the collection of doctors that have no patient at all, sorted in alphabetic order.
	 */
	public Collection<Doctor> idleDoctors(){
		return null;
	}

	/**
	 * returns the collection of doctors that a number of patients larger than the average.
	 */
	public Collection<Doctor> busyDoctors(){
		return null;
	}

	/**
	 * returns list of strings
	 * containing the name of the doctor and the relative number of patients
	 * with the relative number of patients, sorted by decreasing number.<br>
	 * The string must be formatted as "<i>### : ID SURNAME NAME</i>" where <i>###</i>
	 * represent the number of patients (printed on three characters).
	 */
	public Collection<String> doctorsByNumPatients(){
		return null;
	}
	
	/**
	 * computes the number of
	 * patients per (their doctor's) specialization.
	 * The elements are sorted first by decreasing count and then by alphabetic specialization.<br>
	 * The strings are structured as "<i>### - SPECIALITY</i>" where <i>###</i>
	 * represent the number of patients (printed on three characters).
	 */
	public Collection<String> countPatientsPerSpecialization(){
		return null;
	}
	
	public void loadData(String path) throws IOException {
		
	}


}
