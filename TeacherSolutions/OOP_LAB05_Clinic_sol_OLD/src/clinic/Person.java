package clinic;

import clinic.Doctor;

public class Person implements Comparable<Person> {

	private String first;
	private String last;
	private String ssn;
	private Doctor doctor;

	public Person(String first, String last, String ssn) {
		super();
		this.first = first;
		this.last = last;
		this.ssn = ssn;
	}

	public String getSSN(){
		return ssn;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	void setDoctor(Doctor d) {
		if(doctor!=null){
			doctor.removeMe(this);
		}
		doctor=d;
		doctor.addPatient(this);
	}

	@Override
	public int compareTo(Person o) {
		int dl =  this.last.compareTo(o.last);
		if(dl!=0) return dl;
		return this.first.compareTo(o.first);
	}

}
