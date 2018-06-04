package it.polito.po.test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for default package");
    //$JUnit-BEGIN$
    suite.addTest(new TestSuite(TestR1_Patient.class));
    suite.addTest(new TestSuite(TestR2_Doctor.class));
    suite.addTest(new TestSuite(TestR3_AssignPatient.class));
    suite.addTest(new TestSuite(TestR4_LoadFile.class));
    suite.addTest(new TestSuite(TestR5_Stats.class));
    //$JUnit-END$
    return suite;
  }
}
