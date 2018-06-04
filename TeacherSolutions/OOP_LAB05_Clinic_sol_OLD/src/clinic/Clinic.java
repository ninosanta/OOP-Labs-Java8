package clinic;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Clinic {
	private Map<String,Person> patients = new HashMap<>();
	private Map<Integer,Doctor> doctors = new HashMap<>();

	public void addPatient(String first, String last, String ssn) {
		ssn = ssn.replaceAll("\\s+", "");
		Person p = new Person(first,last,ssn);
		patients.put(ssn, p);
	}

	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		ssn = ssn.replaceAll("\\s+", "");
		Doctor d = new Doctor(first,last,ssn,docID,specialization);
		doctors.put(docID, d);
		patients.put(ssn, d);
	}

	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		Person p = getPatient(ssn);
		Doctor d = getDoctor(docID);
		
		p.setDoctor(d);

	}

	public void printDoctorOfPatient(String ssn)  {

	}

	public void printPatientsOfDoctor(int docID) {

	}

	public Person getPatient(String ssn) throws NoSuchPatient {
		if(!patients.containsKey(ssn)) throw new NoSuchPatient();
		return patients.get(ssn);
	}

	public Doctor getDoctor(int docID) throws NoSuchDoctor {
		if(!doctors.containsKey(docID)) throw new NoSuchDoctor();
		return doctors.get(docID);
	}
	
	
	/**
	 * returns the collection of doctors that have no patient at all, sorted in alphabetic order.
	 */
	Collection<Doctor> idleDoctors(){
		return doctors.values().stream()
				.filter( d -> d.getPatients().isEmpty() )
		//		.sorted()  // Doctor must be Comparable	or
				.sorted(comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
				.collect(toList())
				;
	}

	/**
	 * returns the collection of doctors that a number of patients larger than the average.
	 */
	Collection<Doctor> busyDoctors(){
		double average = doctors.values().stream().mapToInt( d -> d.getPatients().size()).average().orElse(0.0);
		return doctors.values().stream()
				.filter( d -> d.getPatients().size() > average )
				.collect(toList())
				;
	}

	/**
	 * returns list of strings
	 * containing the name of the doctor and the relative number of patients
	 * with the relative number of patients, sorted by decreasing number.<br>
	 * The string must be formatted as "<i>### : ID SURNAME NAME</i>" where <i>###</i>
	 * represent the number of patients (printed on three characters).
	 */
	Collection<String> doctorsByNumPatients(){
//		return 
//		doctors.values().stream()
//				.sorted(comparing((Doctor d)->d.getPatients().size()).reversed())
//				.map( d -> String.format("%3d", d.getPatients().size()) + " : "
//							+ d.getId() +  " " + d.getLast() + " " + d.getFirst()
//					)
//				.collect(toList())
//				;
//		// OR
//		return doctors.values().stream()
//				.collect(groupingBy(d->d.getPatients().size(),
//						()->new TreeMap<Integer,List<Doctor>>(reverseOrder()), // type pars required to guide compiler
//						toList()
//						))
//		.entrySet().stream()
//		.flatMap(e->e.getValue().stream())
//		.map( d -> String.format("%3d", d.getPatients().size()) + " : "
//					+ d.getId() +  " " + d.getLast() + " " + d.getFirst()
//			)
//		.collect(toList())
//		;
//// OR
		return 
		patients.values().stream()
				.filter(p->p.getDoctor()!=null)
				.collect(groupingBy(Person::getDoctor,counting()))
				.entrySet().stream()
//				.sorted(comparing(Map.Entry::getValue).reversed()) // the compiler cannot infer the type
				.sorted(comparing(Map.Entry::getValue,reverseOrder())) // while here it can
				.map( e -> String.format("%3d", e.getValue()) + " : "
						+ e.getKey().getId() +  " " + e.getKey().getLast() + " " + e.getKey().getFirst()
				)
				.collect(toList())
				;
	}
	
	/**
	 * computes the number of
	 * patients per (their doctor's) specialization.
	 * The elements are sorted first by decreasing count and then by alphabetic specialization.<br>
	 * The strings are structured as "<i>### - SPECIALITY</i>" where <i>###</i>
	 * represent the number of patients (printed on three characters).
	 */
	public Collection<String> countPatientsPerSpecialization(){
		return patients.values().stream()
				.filter(p->p.getDoctor()!=null)
				.collect(groupingBy( (Person p) -> p.getDoctor().getSpecialization(),
									 ()->new TreeMap<String,Long>(reverseOrder()),
									 counting()
						))
				.entrySet().stream()
				.sorted(comparing(Map.Entry::getValue,reverseOrder()))
				.map( e -> String.format("%3d", e.getValue()) + " - " + e.getKey() ) 
				.collect(toList())
				;
	}
		
	public void loadData(String path) throws IOException {
		try(BufferedReader r = new BufferedReader(new FileReader(path))){
			r.lines()
//			.peek(System.out::println)
			.map( l -> l.split("\\s*;\\s*"))
			.forEach( items -> {
				try{
					if(items[0].trim().equalsIgnoreCase("P")){
						addPatient(items[1],items[2],items[3]);
					}else
					if(items[0].trim().equalsIgnoreCase("M")){
						addDoctor(items[2],items[3],items[4],Integer.parseInt(items[1]),items[5].trim());
					}else{
						System.err.println("Unrecognized line type");
					}
				}catch(ArrayIndexOutOfBoundsException e){
					System.err.println("Missing elements on the line");
				}catch(NumberFormatException e){
					System.err.println("Cannot parse integer value");
				}
			});
		}
	}


}
