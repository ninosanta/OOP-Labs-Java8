package university;

public class Course {
	
	private static final int MAX_STUD = 100;
	
	private int code;
	private String teacher;
	private String title;
	private Student[] iscritti;
	private int nIscritti;
	
	/**
	 * @param nome corso
	 * @param docente corso
	 */
	public Course(String corso, String titolare) {
		title = corso;
		teacher = titolare;
		iscritti = new Student[MAX_STUD];
		nIscritti = 0;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getTeacher() {
		return teacher;
	}


	public String getTitle() {
		return title;
	}
	
	public void setIscritti(Student studente) {
		iscritti[nIscritti++] = studente;
	}
	
	public Student[] getIscritti() {
		return iscritti;
	}
	
	
	
	
}
