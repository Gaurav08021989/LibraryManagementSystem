package library.model;

//this model is used by student class for setting their data
public class Student {
	// Instance variables declaration
	public Integer student_id; // name of student id
	public String student_name; // name of student name
	private Integer student_id1;
	private Integer userid; // name of user id
	private Integer userid1;

	// getters and setters for each attributes of the table
	public Integer getUserid1() {
		return userid1;
	}

	public void setUserid1(Integer userid1) {
		this.userid1 = userid1;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getStudent_id1() {
		return student_id1;
	}

	public void setStudent_id1(Integer student_id1) {
		this.student_id1 = student_id1;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer type1) {
		this.student_id = type1;

	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	// Default Constructor
	public Student() {

	}

	// fully parameterized constructor
	public Student(Integer type1, String student_name, Integer userid) {
		this.student_id = type1;
		this.student_name = student_name;
		this.userid = userid;
	}

}
