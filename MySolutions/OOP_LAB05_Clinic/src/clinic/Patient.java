package clinic;

public class Patient {
	
	private String last;
	private String first;
	private String SSN;
	private Doctor doctor;
	
	public Patient(String first, String last, String SSN) {
		this.last = last;
		this.first = first;
		this.SSN = SSN;
	}
	
	
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	@Override
	public String toString() {
		return last + " " + first + " ("+SSN+")";
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
}
