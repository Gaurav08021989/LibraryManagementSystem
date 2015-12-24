package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle.Control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.controller.StudentController;
import library.model.Book;
import library.model.Student;

//This class contains methods to add update and deletion of student from database
public class StudentDAO {

	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method to save a student model to database
	public Student create(Student stu) {

		// Get a connection
		try {

			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		// Query to insert a record to the student table

		String query = "INSERT INTO Gaurav_Student (student_id,student_name,userid) VALUES (?, ?,?) ;";
		// Use prepared statements to avoid SQL injection attacks

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			// Set the parameters to the query
			statement.setInt(1, stu.getStudent_id());
			statement.setString(2, stu.getStudent_name());
			System.out.println(stu.getUserid());
			statement.setInt(3, stu.getUserid());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// Set the id field of the database to the model

			}
		} catch (SQLException e) {
			stu = null;
			System.out.println("Error Creating Student: " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the student object that was inserted with the id field set.
		return stu;
	}

	// Invoked when admin deletes a student from database
	public Student delete(Student stu1) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to delete a record to the student as well as table
		String query = "DELETE FROM Gaurav_Student WHERE userid=?";
		String query1 = "Delete from Gaurav_Login where userid = ?";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, stu1.getUserid1());
			// Execute the insert
			statement.executeUpdate();

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				// Set the id field of the database to the model

				stu1.setStudent_id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			stu1 = null;
			System.out.println("Error Creating Student: " + e);
		}
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, stu1.getUserid1());
			// Execute the insert
			statement.executeUpdate();

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				// Set the id field of the database to the model

				stu1.setStudent_id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			stu1 = null;
			System.out.println("Error Creating Bank: " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the Student object that was inserted with the id field set.
		return stu1;
	}

	// This method is invoked when admin wants to update the student details.
	public Student update(Student stu2) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to update a record to the student table
		String query = "Update Gaurav_Student set student_name=?,userid = ? where student_id=?";

		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setString(1, stu2.getStudent_name());
			statement.setInt(2, stu2.getStudent_id());
			statement.setInt(3, stu2.getUserid());
			// Execute the update
			statement.executeUpdate();

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				// Set the id field of the database to the model

				stu2.setStudent_id((resultSet.getInt(1)));
			}
		} catch (SQLException e) {
			stu2 = null;
			System.out.println("Error Creating Bank: " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the Student object that was updated with the id field set.
		return stu2;
	}

	// This method is invoked when admin wants to view the list of students
	// available
	public ObservableList<Student> Students() throws SQLException {

		try {

			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Student> list1 = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		String query = "Select * from Gaurav_Student";
		try {
			// Set the parameters to the query

			// To get the primary key (id) of the newly inserted record

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				// Student object created
				Student stu = new Student();
				// Details of student set

				stu.setStudent_id(resultSet.getInt("student_id"));
				stu.setStudent_name(resultSet.getString("student_name"));
				stu.setUserid(resultSet.getInt("userid"));

				System.out.println(resultSet.getInt(1));
				list1.add(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return list1;
	}

}
