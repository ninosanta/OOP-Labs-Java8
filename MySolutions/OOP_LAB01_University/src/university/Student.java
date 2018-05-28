package university;

public class Student {
	
	private static final int MAX_COUR = 25;
	
	private String name;
	private String surname;
	private int id;
	private Course[] courses;
	private int nCours;
	
	/**
	 * @param nome
	 * @param cognome
	 * @param matricola
	 */
	public Student( String nome, String cognome ) {
		name = nome;
		surname = cognome;
		courses = new Course[MAX_COUR];
		nCours = 0;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCourse(Course code) {
		courses[nCours++] = code; 
	}
	
	public Course[] getCourses() {
		return courses;
	}

}
