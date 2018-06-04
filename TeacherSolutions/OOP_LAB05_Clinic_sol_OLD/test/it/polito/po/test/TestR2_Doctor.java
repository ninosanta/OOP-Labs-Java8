package it.polito.po.test;
import junit.framework.TestCase;
import clinic.*;

public class TestR2_Doctor extends TestCase {

  public TestR2_Doctor(String arg0) {
    super(arg0);
  }

  public void testAdd() throws NoSuchDoctor {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEPID12I99F181K";
    String name = "Giova";
    String surname = "Reds";
    s.addDoctor(name, surname, ssn,id, "Surgeon");

    Doctor p = s.getDoctor(id);

    assertEquals(id, p.getId());
    assertEquals(name, p.getFirst());
    assertEquals(surname, p.getLast());
  }

  public void testAddDoctorGetPatient() {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEFIT12I97F181Z";
    String name = "Mario";
    String surname = "White";
    s.addDoctor(name, surname, ssn,id, "Surgeon");

    try {
      Person p = s.getPatient(ssn);
      assertEquals(name, p.getFirst());
    } catch (NoSuchPatient e) {
      fail("A doctor should be a patient too");
    }

  }

  public void testNotFound() {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEFIT12I97F181Z";
    String name = "Mario";
    String surname = "White";
    s.addDoctor(name, surname, ssn,id, "Surgeon");

    try {
      /*Doctor p =*/ s.getDoctor(id + 1000);
      fail("There should be no doctor with such id");
    } catch (NoSuchDoctor e) {
      assertTrue(true);
    }
  }

  public void testGetNull() {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEFIT12I97F181Z";
    String name = "Mario";
    String surname = "White";

    s.addDoctor(name, surname, ssn,id,"Surgeon");

    try {
      Doctor p = s.getDoctor(0);
      assertNull("There should be not doctor with code 0",p);
    } catch (NoSuchDoctor e) {
      assertTrue(true);
    }

  }

  public void testInsertDuplicate() throws NoSuchDoctor {
    Clinic s = new Clinic();

    int id = 321;
    String ssn = "THEFIT12I97F181Z";
    String name = "Mario";
    String surname = "White";

    s.addDoctor(name + "X", surname + "X", ssn + "X",id,"Dentist");
    s.addDoctor(name, surname, ssn,id,"Generic");

    Doctor p = s.getDoctor(id);

    assertEquals(id, p.getId());
  }

}
