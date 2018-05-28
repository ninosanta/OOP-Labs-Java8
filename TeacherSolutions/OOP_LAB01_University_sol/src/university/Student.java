package university;

 class Student {
	
	//static int nextId = University.INITIAL_ID;
	// Incorrect: ids arre assigned globally (no per University)
	
	private int ID;
	private String first;
	private String last;
	
	private Course[] courses;
	
	public Student(int iD, String first, String last) {
		ID = iD;
		//ID = nextId++;
		this.first = first;
		this.last = last;
		courses = new Course[University.MAX_COURSES_PER_STUDENT];
	}
	
	public String toString(){
		return ID + " " + first + " " + last;
		// TODO: probably to be optimized with StringBuffer
	}
	
	public void enroll(Course c){
		for(int i=0; i< courses.length; ++i){
			if( courses[i] == null){
				courses[i] = c;
				break;
			}
		}
	}

	public String courses() {
		StringBuffer result = new StringBuffer();
		for(Course c : courses){
			if(c!=null){
				result.append(c).append("\n");
			}
		}
		return result.toString();
	}
	
	
	
	
	
	
	
	
	
}
