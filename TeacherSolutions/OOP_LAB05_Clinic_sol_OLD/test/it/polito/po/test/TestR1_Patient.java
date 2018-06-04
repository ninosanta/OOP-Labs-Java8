package it.polito.po.test;
import junit.framework.TestCase;
import clinic.*;

public class TestR1_Patient extends TestCase {

  public TestR1_Patient(String arg0) {
    super(arg0);
  }

  public void AddPatient() throws NoSuchPatient {
    Clinic s = new Clinic();

    String ssn = "THEPID12I99F181K";
    String name = "Giova";
    String surname = "Reds";
    s.addPatient(name, surname, ssn);

    Person p = s.getPatient(ssn);

    assertEquals(name, p.getFirst());
    assertEquals(surname, p.getLast());
  }

  public void testNotFoundEmpty() {
    Clinic s = new Clinic();

    String ssn = "THEPID12I99F181K";

    try {
      /*Person p =*/ s.getPatient(ssn);
      fail("There should be no patient at all");
    } catch (NoSuchPatient e) {
      assertTrue(true);
    }
  }

  public void testNotFound() {
    Clinic s = new Clinic();

    String cf = "THEPID12I99F181K";
    String nome = "Giova";
    String cognome = "Reds";

    s.addPatient(nome, cognome, cf);

    try {
      /*Person p =*/ s.getPatient(cf + "_");
      fail("There should be no patient with the given ID");
    } catch (NoSuchPatient e) {
      assertTrue(true);
    }
  }

  public void testGetNull() {
    Clinic s = new Clinic();

    String ssn = "THEPID12I99F181K";
    String name = "Giova";
    String surname = "Reds";

    s.addPatient(name, surname, ssn);

    try {
      Person p = s.getPatient(null);
      assertSame(null, p);
    } catch (NoSuchPatient e) {
      assertTrue(true);
    }
  }

  public void testInsertDouble() throws NoSuchPatient {
    Clinic s = new Clinic();

    String ssn = "THEPID12I99F181K";
    String name = "Giova";
    String surname = "Reds";

    s.addPatient(name + "X", surname + "Y", ssn);
    s.addPatient(name, surname, ssn);

    Person p = s.getPatient(ssn);

    assertEquals(name, p.getFirst());
  }

}
