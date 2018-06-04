package it.polito.po.test;

import java.util.Collection;


import clinic.*;
import junit.framework.TestCase;

public class TestR5_Stats extends TestCase {

	ClinicAdapter c;
	double average;
	protected void setUp() throws Exception {
		super.setUp();
		c = new ClinicAdapter();
		c.addPatient("John", "Smith", "SHNSMT23X12A543L");
		c.addPatient("Mary", "White", "MRYWHT98G76F987W");
		c.addPatient("Mario", "Rossi", "MRIRSS32J88K987P");
		c.addPatient("Giuseppe", "Verdi", "GPPVRD43A34H987O");
		c.addPatient("Fang","Li","LIIFNG98T54K123A");
		c.addPatient("Sirius","Black","BLKSRS11I88F981K");
		c.addDoctor("Umberto", "Veronesi", "MBTVRN43J56K124U", 111, "Oncologist");
		c.addDoctor("Luigi", "Neri", "LGINRI56K34L098K", 123, "Surgeon");
		c.addDoctor("Filippo", "Neri", "FPPNRI54K43L098J", 124, "Dentist");
		c.addDoctor("Felice", "Tranquillo", "FLCTRQ26G98T592R", 220, "Dentist");
		
		c.assignPatientToDoctor("SHNSMT23X12A543L", 111);
		c.assignPatientToDoctor("MRYWHT98G76F987W", 124);
		c.assignPatientToDoctor("GPPVRD43A34H987O", 124);
		//c.assignPatientToDoctor("FLCTRQ26G98T592R", 124);
		c.assignPatientToDoctor("MRIRSS32J88K987P", 124);
		c.assignPatientToDoctor("BLKSRS11I88F981K", 123);
		c.assignPatientToDoctor("LIIFNG98T54K123A", 123);
		
		average = (0+1+3+2) / 4.0;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

/**
 * The method idleDoctors() returns the collection of doctors that have no patient at all, sorted in alphabetic order.
 */
	public void testIdle(){
		
		Collection<Doctor> idle = c.idleDoctors();
		
		assertEquals(1,idle.size());
		
		assertEquals("Felice",idle.iterator().next().getFirst());
		
	}
	
	
/**	
 * The method busyDoctors() returns the collection of doctors that are assigned a number of patients larger than the average.
 */
	public void testBusy(){
		
		Collection<Doctor> busy = c.busyDoctors();
		
		assertEquals(2, busy.size());
		
		assertTrue(busy.stream().allMatch( d -> d.getPatients().size() >= average ) );
		
	}

	/**	
 * The method doctorsByNumPatients() returns list of strings containing the name of the doctor and the relative number 
 * of patients with the relative number of patients, sorted by decreasing number.
 * The string must be formatted as "### : ID SURNAME NAME" where ### represent the number of patients (printed on three characters).
 */
	public void testByNumPatients(){
		
		Collection<String> byNum = c.doctorsByNumPatients();
		
		byNum.stream()
				.allMatch( s ->{
					try{
						Integer.parseInt(s.substring(0, 3).trim());
					}catch(NumberFormatException e){
						fail("Cannot parse # patients "  + s);
						return false;
					}
					assertTrue("Missing ':' in " + s,s.indexOf(':')>0);
					return true;
				});
		
		String first = byNum.iterator().next();
		int n = Integer.parseInt(first.substring(0, 3).trim());
		String[] doc = first.split("\\s*:\\s*")[1].split("\\s+");
		
		assertEquals("Number of patients for busiest doctor",3,n);
		assertEquals("in " + first,124,Integer.parseInt(doc[0].trim()));
	}


	/**	
 * The method countPatientsPerSpecialization() computes the number of patients per (their doctor's) specialization. 
 * The elements are sorted first by decreasing count and then by alphabetic specialization.
 * The strings are structured as "### - SPECIALITY" where ### represent the number of patients (printed on three characters).
 */
	public void testPerSpecialization(){
		Collection<String> byNum = c.countPatientsPerSpecialization();
		byNum.stream()
		.allMatch( s ->{
			try{
				Integer.parseInt(s.substring(0, 3).trim());
			}catch(NumberFormatException e){
				fail("Cannot parse # patients "  + s);
				return false;
			}
			assertTrue("Missing '-' in " + s,s.indexOf('-')>0);
			return true;
		});
		
		String first = byNum.iterator().next();
		int n = Integer.parseInt(first.substring(0, 3).trim());
		String spec = first.split("\\s*-\\s*")[1];
		
		assertEquals("Num of patient per most popular specializtion",3,n);
		assertEquals("Most popular specialization" + first,"Dentist",spec);
	}
}
