package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import library.controller.ReportController;
import library.model.Book;
import library.model.Report;

//DAO object used for student to add and view data.
public class ReportDAO {

	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method invoked when student tries to add a book from the list of books
	// available
	public Report update(Report rep) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		// Query to set isvailable flag so that no other student can borrow the
		// book once borrowed by the student
		String query1 = "Select isavailable from gaurav_book where book_id=" + rep.getBook_id();

		System.out.println(query1);
		try (PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {

			int flag = 0;
			ResultSet res;
			res = statement1.executeQuery(query1);

			/*while (res.next()) {
				flag = Integer.parseInt(res.getString(1));
			}*/

			if (flag == 0) {

				String query = "Update gaurav_report set  student_id = ?,issue_date = current_date(),due_date = current_date() + interval 15 day,recd_date= ?,book_name = ? where book_id = ?;";
				String query2 = "Update gaurav_book set isavailable = 1 where book_id =" + rep.getBook_id();

				try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

					statement.setInt(1, rep.getStudent_id());

					System.out.println(rep.getStudent_id());

					Date recddate = Date.valueOf(rep.getRecd_date());
					System.out.println("rec" + recddate);
					statement.setDate(2, recddate);

					statement.setString(3, rep.getBook_name());

					statement.setInt(4, rep.getBook_id());

					// Execute the insert
					statement.executeUpdate();

					// To get the primary key (id) of the newly inserted record
					ResultSet resultSet = statement.getGeneratedKeys();

					if (resultSet.next()) {
						// Set the id field of the database to the model

						rep.setBook_id(resultSet.getInt(1));
					}
				} catch (SQLException e) {
					rep = null;
					System.out.println("Error Creating Bank: " + e);
				}

				try (PreparedStatement statement = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)) {
					// Set the parameters to the query

					// Execute the insert
					statement.executeUpdate();

					// To get the primary key (id) of the newly inserted record
					ResultSet resultSet = statement.getGeneratedKeys();

					if (resultSet.next()) {
						// Set the id field of the database to the model

						rep.setBook_id(resultSet.getInt(1));
					}
				} catch (SQLException e) {
					rep = null;
					System.out.println("Error Creating Bank: " + e);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Book Not Available", "Result", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			rep = null;
			System.out.println("Error Creating Book: " + e);
		}

		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the book object that was inserted with the id field set.
		return rep;
	}

	// Used to display the report to the admin to see the data in the report
	// table
	public ObservableList<Report> Reports() throws SQLException {

		try {
			// Connection creation to database
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Report> list1 = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		String query = "Select * from gaurav_report";
		try {
			// Set the parameters to the query
			PreparedStatement statement1 = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			// To get the primary key (id) of the newly inserted record

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				// Model object created for calling methods
				Report rep = new Report();

				rep.setStudent_id((resultSet.getInt("student_id")));

				rep.setBook_id(resultSet.getInt("book_id"));

				rep.setIssue_date(resultSet.getDate("issue_date"));

				System.out.println(rep.getIssue_date());

				rep.setDue_Date(resultSet.getDate("due_date"));

				System.out.println(rep.getDue_Date());

				/* rep.setRecd_date(resultSet.getDate("recd_date")); */

				rep.setBook_name(resultSet.getString("book_name"));

				System.out.println(resultSet.getInt(1));
				list1.add(rep);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return list1;
	}

	// Invoked when student sees the list of books availabe in the library
	public ObservableList<Book> Books() throws SQLException {

		try {

			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Book> list1 = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		String query = "Select * from gaurav_book";
		try {
			// Set the parameters to the query

			// To get the primary key (id) of the newly inserted record

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Book book = new Book();
				book.setBook_id(resultSet.getInt("book_id"));

				book.setBook_name(resultSet.getString("book_name"));
				book.setAuthor(resultSet.getString("author"));

				System.out.println(resultSet.getInt(1));
				list1.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return list1;
	}

}
