package clinic;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;




/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	
	private static final int P_SSN = 3;
	private static final int P_LAST = 2;
	private static final int P_NAME = 1;
	private static final int D_SPEC = 5;
	private static final int D_SSN = 4;
	private static final int D_LAST = 3;
	private static final int D_NAME = 2;
	private static final int D_ID = 1;
	private static final int PAT_FIELDS = 4;
	private static final int DOC_FIELDS = 6;
	Map<String, Patient> patients = new HashMap<>();
	Map<Integer, Doctor> doctors = new HashMap<>();
	
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		patients.put(ssn, new Patient(first, last, ssn));
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
//		String patient = patients.get(ssn).toString();
//		if (patient == null) {
//			String msg = "Patient " + ssn + "  is not present!";
//			throw new NoSuchPatient(msg);
//		}
//		return patient;
		
		try { 
			String patient = patients.get(ssn).toString();
			return patient;
		} catch (NullPointerException np) {
			String msg = "\n\nPatient " + ssn + " is not present!\n";
			throw new NoSuchPatient(msg);
		}

	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		Doctor doctor = new Doctor(first, last, ssn, docID, specialization);
		doctors.put(docID, doctor);
		patients.put(ssn, new Patient(first, last, ssn));
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		try {
			return doctors.get(docID).toString();
		} catch (NullPointerException nd) {
			String msg = "\n\nDoctor "+docID+" not found!\n\n";
			throw new NoSuchDoctor(msg);
		}
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
//		if (patients.containsKey(ssn)) {
//			throw new NoSuchPatient();
//		}
//		etc...
		 
		// OPPURE:
		try {
			Doctor doc = doctors.get(docID);
			try {
				Patient patient = patients.get(ssn);
				doc.addPatient(patient);
				patient.setDoctor(doc);
			} catch (NullPointerException np) {
				throw new NoSuchPatient();
			}
		} catch (NullPointerException nd) {
			throw new NoSuchDoctor();
		}
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		try {
			Patient patient = patients.get(ssn);
			Doctor doc = patient.getDoctor();
			if (doc == null) {
				throw new NoSuchDoctor("Patient "+patient+" has no doctor assigned!");
			}
			return doc.getID();
		} catch (NullPointerException np) {
			throw new NoSuchPatient("Patient "+ssn+" doesn't exist");
		}
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSN
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		try {
			Doctor doc = doctors.get(id);
			return doc.getPatients();
		} catch (NullPointerException nd) {
			throw new NoSuchDoctor("\n\nDoctor "+id+" not found!\n\n");
		}
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param path the path of the required file
	 * @throws IOException in case of IO error
	 */
	public void loadData(Reader reader) throws IOException {
		List<String> lines = null;
		
		try (BufferedReader br = new BufferedReader(reader)) {
//			 lines = br.lines().filter(l -> !l.equals("")).collect(toList());
			 lines = br.lines().collect(toList());
		}  catch(IOException e) { 
			System.err.println(e.getMessage());
			throw e;
		}
		
		lines.stream()
			 .forEach( line -> {
				 	line = line.trim().replace("; ", ";");
//				 	System.out.println(line);
//					String s = "  ciao    PIPPO     ";
//				 	s.trim()  // " ciao pippo ";
				 	String[] fields = line.split(";");
				 	if (fields.length == DOC_FIELDS || fields.length == PAT_FIELDS) {
				 		try {
						 	switch (line.charAt(0)) {
							case 'M':
								if (checkID(fields[D_ID]) == false) {
									try {
										throw new IOException("\nID field: " + fields[1] + " must be an integer!\n");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										System.err.println(e.getMessage());
									}
									break;
								}
								Doctor doc = new Doctor(fields[D_NAME], fields[D_LAST], fields[D_SSN], 
														Integer.parseInt(fields[D_ID]), fields[D_SPEC]);
								doctors.put(doc.getID(), doc);
								break;
							case 'P':
								Patient patient = new Patient(fields[P_NAME], fields[P_LAST], fields[P_SSN]);
								patients.put(patient.getSSN(), patient);
								break;
							default:
								System.err.println("\nLine: " + line
										            + "doesn't start with M or P\n");
								break;
							}
				 		} catch(ArrayIndexOutOfBoundsException e){
							System.err.println("Tried to access to an inexistent field of the line!");
				 		}
				 	} else {
				 		System.err.println("Line:\n\t" + line 
				 						+ "\nhas a wrogn number of elements\n");
				 	}
			 	}
			 );
	
		// oppure lettura char by char
//		StringBuffer line = new StringBuffer();
//		int ch;
//		
//		while ((ch = reader.read()) != EOF) {
//			char unicode = (char) ch;
//			if (unicode == '\n') {
//				lines.add(line);
//				// buffer reset
//				line.setLength(0);
//				line = new StringBuffer();
//				continue;
//			}
//			line.append(unicode);
//		}
		
		reader.close();
	}

	
	private boolean checkID(String line) {
		String regex = "[0-9]+";
//		System.out.println(line);
		Pattern p = Pattern.compile(regex);
		Matcher recognizer = p.matcher(line);
		
		return recognizer.matches();
	}
	

	/**
	 * Retrieves the collection of doctors that have no patient at all, sorted in alphabetic order.
	 * 
	 * @return the collection of doctors with no patient sorted in alphabetic order (last name and then first name)
	 */
	public Collection<Integer> idleDoctors() {
		return doctors.values().stream()  // Stream<Doctor>
			  .filter(d -> d.getPatients().isEmpty())  // Stream<Doctor> con meno dottori
			  .sorted(comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
			  .map(Doctor::getID)
			  .collect(toList());
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors
	 */
	public Collection<Integer> busyDoctors(){
		double average = doctors.values().stream()
//								.map(p -> p.getPatients().size())
								.collect(averagingDouble(p -> p.getPatients().size()));
		return doctors.values().stream()
					 .filter(p -> p.getPatients().size() > average)
					 .map(Doctor::getID)
					 .collect(toList());
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		
		return doctors.values().stream() // Stream<Doctor>
					  .filter(d -> ! d.getPatients().isEmpty())
					  .sorted(comparing(d -> d.getPatients().size() , reverseOrder()))
					  .map(d -> String.format("%3d", d.getPatients().size()) + " : " 
							   			+ d.getID() + " "+ d.getLast() + " "+ d.getFirst())
					  .collect(toList());
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  specialization 
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic specialization.
	 * 
	 * @return the collection of strings with specialization and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization() {
		return patients.values().stream()
					   .filter(p -> p.getDoctor() != null)
					   .collect(groupingBy(p -> p.getDoctor().getSpecialization(),
							  			   () -> new TreeMap<String, Long>(),
							  			   counting()						
							  	     	  )
							  )  // TreeMap<Specializzazione, NumeroPazientiPerSpecializzazione>
				.entrySet().stream()
						   .sorted(comparing(Map.Entry::getValue, reverseOrder()))
						   .sorted(comparing(Map.Entry::getKey))
						   .map(e -> String.format("%3d", e.getValue()) + " - " 
								   		+ e.getKey())
						   .collect(toList());
	}
	
}
