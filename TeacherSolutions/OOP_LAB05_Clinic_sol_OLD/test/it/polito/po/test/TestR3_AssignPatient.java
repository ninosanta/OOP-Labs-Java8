package it.polito.po.test;
import java.util.Collection;

import clinic.*;
import junit.framework.TestCase;



public class TestR3_AssignPatient extends TestCase {

  public TestR3_AssignPatient(String arg0) {
    super(arg0);
  }

  
	public void testAssignDoctor() throws NoSuchPatient, NoSuchDoctor{
		Clinic s = new Clinic();
  	
		String ssn = "THEPID12I99F181K";
		s.addPatient("Giova","Reds",ssn);

		int id = 124;
		s.addDoctor("Mario", "White", "THEFIT12I97F181Z",id, "Surgeon");		
		s.assignPatientToDoctor(ssn,id);
		
		Person p = s.getPatient(ssn);
		Doctor m = p.getDoctor();
		
		int result = m.getId();
		
		assertEquals(id,result);
	}
  
	public void testNoAssignedDoctor() throws NoSuchPatient{
		Clinic s = new Clinic();
  	
		String ssn = "THEPID12I99F181K";
		s.addPatient("Giova","Reds",ssn);

		Person p = s.getPatient(ssn);
		
		Doctor m = p.getDoctor();
		
		assertSame(null,m);	
	}
  
	public void testDuplicateAssignment() throws NoSuchPatient, NoSuchDoctor{
		Clinic s = new Clinic();
  	
		String ssn = "THEPID12I99F181K";
		s.addPatient("Giova","Reds",ssn);

		int id1 = 1234;
		s.addDoctor("Mario", "White", "THEFIT12I97F181Z",id1, "Surgeon");		
		s.assignPatientToDoctor(ssn,id1);
		
		int id2 = 5678;
		s.addDoctor("Joseph", "Greens", "JSHGRN18I93F181Z",id2, "Surgeon");		
		s.assignPatientToDoctor(ssn,id2);

		Person p = s.getPatient(ssn);
		Doctor m = p.getDoctor();
		
		int result = m.getId();
		
		assertEquals("The last assignment should have effect",
					id2,result);
	}

	  public void testList() throws NoSuchPatient, NoSuchDoctor{
			Clinic s = new Clinic();
	  	
			String ssn1 = "THEPID12I99F181K";
			s.addPatient("Giova","Reds",ssn1);

			String ssn2 = "BLKSRS11I88F981K";
			s.addPatient("Sirius","Black",ssn2);

			int id = 124;
			s.addDoctor("Mario", "White", "THEFIT12I97F181Z",id, "Surgeon");		
			s.assignPatientToDoctor(ssn1,id);
			s.assignPatientToDoctor(ssn2,id);
			
			Person p1 = s.getPatient(ssn1);
			Person p2 = s.getPatient(ssn2);

			Doctor m = s.getDoctor(id);
			
			Collection<Person> patients = m.getPatients();
			
		    assertTrue(patients.contains(p1));
			assertTrue(patients.contains(p2));
	  }
	  
//	  public void testSortedList() throws NoSuchPatient, NoSuchDoctor{
//			Clinic s = new Clinic();
//	  	
//			String cf3 = "RSSPLA13I88F981K";
//			s.addPatient("Paolo","Reds",cf3);
//
//			String cf1 = "THEPID12I99F181K";
//			s.addPatient("Giova","Reds",cf1);
//
//			String cf2 = "BLKSRS11I88F981K";
//			s.addPatient("Sirius","Black",cf2);
//
//			int mat = 123;
//			s.addDoctor("Mario", "White", "THEFIT12I97F181Z",mat, "Dentist");		
//			s.assignPatientToDoctor(cf1,mat);
//			s.assignPatientToDoctor(cf2,mat);
//			s.assignPatientToDoctor(cf3,mat);
//			
//			Doctor m = s.getDoctor(mat);
//			
//			Collection<Person> pazienti = m.getPatients();
//			Iterator<Person> it = pazienti.iterator();
//			
//	  		Person p1 = (Person)it.next();
//			Person p2 = (Person)it.next();
//			Person p3 = (Person)it.next();
//			
//			assertEquals("Black",p1.getLast());
//			assertEquals("Reds",p2.getLast());
//			assertEquals("Reds",p3.getLast());
//			assertEquals("Paolo",p3.getFirst());
//	  }


}
