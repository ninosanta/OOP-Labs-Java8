package clinic;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Doctor extends Person {
	private int id;
	private String specialization;
	private List<Person> patients = new LinkedList<>();
	
	public Doctor(String first, String last, String ssn, int docID,
			String specialization) {
		super(first,last,ssn);
		this.id=docID;
		this.specialization = specialization;
	}

	public int getId(){
		return id;
	}
	
	public String getSpecialization(){
		return specialization;
	}
	
	public Collection<Person> getPatients() {
		Collections.sort(patients);
		return patients;
	}


	public void addPatient(Person p) {
		patients.add(p);
	}

	void removeMe(Person person) {
		patients.remove(person);
	}

}
