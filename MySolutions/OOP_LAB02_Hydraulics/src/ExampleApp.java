import hydraulic.*;

public class ExampleApp {

	public static void main(String args[]){
		
		HSystem s = new HSystem();
		HSystem s2 = new HSystem();
		
		// 1) the elements of the system are defined and added
		Source src = new Source("Src");
		s.addElement(src);
		Tap r = new Tap("R");
		s.addElement(r);
		Split t = new Split("T");
		s.addElement(t);
		Sink sink1 = new Sink("sink A");
		s.addElement(sink1);
		Sink sink2 = new Sink("sink B");
		s.addElement(sink2);
		
		Source src2= new Source("Src2");
		s2.addElement(src2);
		Tap r2 = new Tap("R2");
		s.addElement(r);
		Split t2 = new Split("T2");
		s.addElement(t);
		Sink sink12 = new Sink("sink A2");
		s.addElement(sink12);
		Sink sink22 = new Sink("sink B2");
		s.addElement(sink22);
		
		
		// 2) elements are then connected
		src.connect(r);
		r.connect(t);
		t.connect(sink1,0);
		t.connect(sink2,1);
		
		src2.connect(r2);
		r2.connect(t2);
		t2.connect(sink12,0);
		t2.connect(sink22,1);
		
		// 3) simulation parameters are then defined
		src.setFlow(20);
		r.setOpen(true);
		
		src2.setFlow(78.5);
		r2.setOpen(true);
		
		// 4) simulation starts
		s.simulate();
		System.out.println("");
		s2.simulate();
		
		// 5) print the system layout
		System.out.println("\n");
		System.out.println(s.layout());
		System.out.println("");
		System.out.println(s2.layout());
	}
	
}
