package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.Main;
import library.model.Report;
import library.model.User;

public class LoginDAO {

	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	public User create(User user) {

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Insert Query for adding users into the database
		String query = "INSERT INTO Gaurav_Login (userid,name,password,usertype) VALUES (?, ?,?,'Student') ;";
		// Use prepared statements to avoid SQL injection attacks

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			// Set the parameters to the query
			statement.setInt(1, user.getId());
			statement.setString(2, user.getUsername());
			

			statement.setString(3, user.getPassword());
			
			System.out.println(user.getId());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// Set the id field of the database to the model

				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
			}
		} catch (SQLException e) {
			user = null;
			System.out.println("Error Creating Bank: " + e);
		}

		return user;

	}

}
