package library.dao;

//This class is basically the connection from database to the admin protal for addin updation and deletion of books.
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.model.Book;

public class BookDAO {
	// Connection created for database.
	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method to save a book model to database
	public Book create(Book book) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to insert a record to the bank table
		String query = "INSERT INTO Gaurav_Book (book_id, book_name,author) VALUES (?, ?, ?) ;";
		String query1 = "insert into Gaurav_Report (student_id,issue_date,due_date,recd_date,book_name,book_id) values (0,current_date,current_date,current_date,'0',?);";

		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, book.getBook_id());
			statement.setString(2, book.getBook_name());
			statement.setString(3, book.getAuthor());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// Set the id field of the database to the model

				book.setBook_id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			book = null;
			System.out.println("Error Creating Bank: " + e);
		}

		try (PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement1.setInt(1, book.getBook_id());

			// Execute the insert
			statement1.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet1 = statement1.getGeneratedKeys();
			if (resultSet1.next()) {
				// Set the id field of the database to the model

				book.setBook_id(resultSet1.getInt(1));
			}
		} catch (SQLException e) {
			book = null;
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
		return book;
	}

	// Method to delete a book from database
	public Book delete(Book book1) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to delete a record to the book table and report table
		String query = "DELETE FROM Gaurav_Book WHERE book_id=?";
		String query1 = "Delete from Gaurav_Report where book_id =?";

		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			// statement.getInteger(1, book.setBook_id(Integer.parseInt(Id)));

			System.out.println(book1.getBook_id1());
			statement.setInt(1, book1.getBook_id1());
			// Execute the insert
			statement.executeUpdate();

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				// Set the id field of the database to the model

				book1.setBook_id1(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			book1 = null;
			System.out.println("Error Creating Bank: " + e);
		}
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, book1.getBook_id1());
			// Execute the insert
			statement.executeUpdate();

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				// Set the id field of the database to the model

				book1.setBook_id1(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			book1 = null;
			System.out.println("Error Creating Bank: " + e);
		}

		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the book object that was inserted with the id field set.
		return book1;
	}

	// Method to update book details in database
	public Book update(Book book2) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to insert a record to the bank table
		String query = "Update Gaurav_Book set book_name=?,author=?  where book_id=?";

		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setString(1, book2.getBook_name());
			statement.setString(2, book2.getAuthor());
			statement.setInt(3, book2.getBook_id());

			// Execute the insert
			statement.executeUpdate();

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				// Set the id field of the database to the model

				book2.setBook_id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			book2 = null;
			System.out.println("Error Creating Bank: " + e);
		}
		// Close the connection to the database - Very important!!!
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the book object that was inserted with the id field set.
		return book2;
	}

	// Method used to invoke the list of books to the admin from database
	public ObservableList<Book> Books() throws SQLException {

		try {

			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Book> list1 = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		String query = "Select * from Gaurav_Book";
		try {

			// To get the primary key (id) of the newly inserted record

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				// Book instance created
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
