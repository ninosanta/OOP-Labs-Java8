import university.*;

public class ExampleApp {

	public static void main(String[] args) {
		
		String universityName = "Politecnico di Torino";
		
		University poli = new University(universityName);
		
		poli.setRector("Francesco", "Profumo");
		
		System.out.println("Rector of " + poli.getName() + " : " + poli.getRector());
		
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		
		System.out.println("\nEnrolled students " + s1 + ", " + s2);
		System.out.println("s1 = " + poli.student(s1));
		System.out.println("s2 = " + poli.student(s2));

		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "Marco Torchiano");
		
		System.out.println("\nActivated courses " + macro + " and " + oop);
		System.out.println("c1 = " + poli.course(macro));
		System.out.println("c2 = " + poli.course(oop));
		
		poli.register(s1, macro);
		poli.register(s1, oop);
		poli.register(s2, macro);
		
		System.out.println("\n" + poli.listAttendees(macro));
		System.out.println(poli.listAttendees(oop));
		
		System.out.println(poli.studyPlan(s1));
		
	}
}
