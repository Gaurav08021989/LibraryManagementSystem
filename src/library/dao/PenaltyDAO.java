package library.dao;

//DAO object created for Penalty payment for students
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import library.model.Penalty;

public class PenaltyDAO {

	// Connection object
	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method to save a bank model to database
	public Penalty create(Penalty fine) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		// Query to insert a record to the penalty table
		String query2 = "INSERT INTO gaurav_penalty( penalty_id , student_id , issue_date , recd_date ,  fineamount   ) VALUES ( ? ,?, ? , ?, ? ) ;";

		try (PreparedStatement statement = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, fine.getPenalty_id());

			statement.setInt(2, fine.getstudent_id());

			Date issuedate = Date.valueOf(fine.getIssue_date());
			statement.setDate(3, issuedate);

			Date receivedate = Date.valueOf(fine.getRecd_date());
			statement.setDate(4, receivedate);

			statement.setString(5, fine.getPenalty_amount());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// Set the id field of the database to the model
				fine.setstudent_id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			fine = null;
			System.out.println("Error Creating Bank: " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the fine object that was inserted with the id field set.
		// return fine ;
		return fine;
	}

}
