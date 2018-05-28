package university;

public class University {
	
	private static final int MAX_STUDENTS = 1000;
	private static final int ID_START = 10000;
	private static final int MAX_COURSES = 50;
	private static final int COURSES_START = 10;
	
	
	private String name;
	private String rName;
	private String rSurn;
	private Student[] iscritti;
	private int totIscritti;
	private int currStud;
	private int currId;
	private Course[] corsi;
	private int currCourse;
	private int currCode;
	
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		iscritti = new Student[MAX_STUDENTS];
		corsi = new Course[MAX_COURSES];
		totIscritti = 0;
		currStud = 0;
		currId = ID_START;
		this.name = name;
		currCourse = 0;
		currCode = 10;
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Defines the rector for the university
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		this.rName = first;
		this.rSurn = last;
		/*
		 * potevo fare qualcosa del tipo:
		 * this.rettore = fist + " " + last;
		 */
	}
	
	/**
	 * Retrieves the rector of the university
	 * @return
	 */
	public String getRector(){
		return ( rName + " " + rSurn );
		/*
		 * qui avrei potuto fare:
		 * return rettore;
		 */
	}
	
	/**
	 * Enroll a student in the university
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		if (++totIscritti > MAX_STUDENTS)
			return -1;
		Student s = new Student(first, last);
		s.setId(currId++);  // potevo farlo fare al costruttore della classe Student
		iscritti[currStud++] = s;
		return s.getId();
	}
	
	/**
	 * Retrieves the information for a given student
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id) {
		Student pippo = iscritti[id-ID_START]; 
		if (pippo != null)
			return pippo.getId() + " " + pippo.getName() + " " + pippo.getSurname();
			/**
			 * potevo fare un override del metodo toString all'interno della classe
			 * Student in modo da far restituire ID, Nome, Cognome con il formato
			 * del return soprastante...
			 */
		else
			return null;
	}
	
	/**
	 * Activates a new course with the given teacher
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		if(currCourse-COURSES_START > MAX_COURSES)
			return -1;
		/* anche qui valgonole stesse considerazioni fatte per il
		 * metodo enroll
		 */
		corsi[currCourse] = new Course(title, teacher);
		corsi[currCourse].setCode(currCode++);
		return corsi[currCourse++].getCode();
	}
	
	/**
	 * Retrieve the information for a given course
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code) {
		int index = code-COURSES_START;
		if(index < 0 || index >= MAX_COURSES)
			return null;
		Course foo = corsi[code-COURSES_START];
		if(foo != null) 
			return foo.getCode() + " " + foo.getTitle() + " " 
					+ foo.getTeacher();
			/*
			 * anche qui potevo fare l'override di toString(); 
			 */
		else
			return null;
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		int indexStud = studentID - ID_START;
		int indexCour = courseCode - COURSES_START;
		
		iscritti[indexStud].setCourse(corsi[indexCour]);
		corsi[indexCour].setIscritti(iscritti[indexStud]);
	}
	
	/**
	 * Retrieve a list of attendees
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		int indexCour = courseCode - COURSES_START;
		
		if(corsi[indexCour] != null) {
			String tmp = "";
			Student[] pippi = corsi[indexCour].getIscritti();
			
			for(Student pippo : pippi) {
				if (pippo == null)
					break;
				tmp += pippo.getId() + " " + pippo.getName() 
				+ " " + pippo.getSurname() + "\n";
			}
//			System.out.println(tmp);
			return tmp;
		} else {
			return null;
		}
	}

	/**
	 * Retrieves the study plan for a student
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		int indexStud = studentID-ID_START;
		
		if(iscritti[indexStud] != null) {
			String tmp = "";
			Course[] bar = iscritti[indexStud].getCourses();
			
			for(Course foo : bar) {
				if(foo == null) 
					break;
				tmp = tmp.concat(foo.getCode() + " " + foo.getTitle() + " " 
					+ foo.getTeacher() + "\n");
			}
			return tmp;
		} else {		
			return null;
		}
	}
	
}
