package clinic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Doctor extends Patient {

	private int ID;
	private String specialization;
	private Map<String, Patient> patients;
	
	public Doctor(String first, String last, String ssn, int docID, String specialization) {
		super(first, last, ssn);
		this.ID = docID;
		this.specialization = specialization;
		patients = new HashMap<>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	@Override
	public String toString() {
		return super.getLast()+" "+super.getFirst()+" ("+super.getSSN()
					+") ["+this.ID+"]: "+this.specialization;
	}
	
	public void addPatient(Patient patient) {
		patients.put(patient.getSSN(), patient);
	}


	public Collection<String> getPatients() {
		return patients.keySet();
	}
	
}
