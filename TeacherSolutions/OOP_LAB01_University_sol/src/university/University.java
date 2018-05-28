package university;

public class University {
	
	public static final int MAX_STUDENTS = 1000;
	public static final int MAX_COURSES = 50;
	public static final int MAX_COURSES_PER_STUDENT = 25;
	public static final int MAX_STUDENTS_PER_COURSE = 100;

	public final static int INITIAL_ID = 10000;
	public final static int INITIAL_CODE = 10;
	
	private String name;
	private String rector;
	
	private Student[] include; // = null
	private int nextId = INITIAL_ID;
	
	private Course[] offers;
	private int nextCode = INITIAL_CODE;

	public University(String name){
		this.name = name;
		this.rector = "<none>";
		
		include = new Student[MAX_STUDENTS];
		offers = new Course[MAX_COURSES];
	}
	
	public String getName(){
		return name;
	}
	
	public void setRector(String first, String last){
		this.rector = first + " " + last;
	}
	
	public String getRector(){
		return rector;
	}
	
	public int enroll(String first, String last){
		Student s = new Student( nextId , first, last);
		
		include[ nextId - INITIAL_ID ] = s;
		
		return nextId++;
	}
	
	public String student(int id){
		Student s = include[ id - INITIAL_ID ];
		return s.toString();
	}
	
	public int activate(String title, String teacher){
		Course c = new Course(nextCode,title,teacher);
		offers[nextCode - INITIAL_CODE] = c;
		return nextCode++;
	}
	
	public String course(int code){
		return offers[code-INITIAL_CODE].toString();
	}
	
	public void register(int studentID, int courseCode){
		Student s = include[studentID-INITIAL_ID];
		Course c = offers[courseCode-INITIAL_CODE];
		
		s.enroll(c);
		c.enroll(s);
	}
	
	public String listAttendees(int courseCode){
		Course c = offers[courseCode-INITIAL_CODE];
		return c.attendees();
	}

	public String studyPlan(int studentID){
		Student s = include[studentID-INITIAL_ID];
		return s.courses();
	}
}
