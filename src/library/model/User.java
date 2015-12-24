package library.model;

//User details viewing 
//this basically is the base clas model which is inherited by admin and student
public class User {

	// getters and setters for each attributes of class

	public static String username;

	public String password;

	public int userid;

	public static Integer Id;

	// Getters and setters for userid and password
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		User.username = username;
	}

	//
	public static Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		User.Id = Id;
	}

	// empty default constructor
	public User() {

	}

}
