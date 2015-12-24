package library.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import library.Main;
import library.dao.BookDAO;
import library.model.Book;
import library.model.Report;

//This class consists of the methods in the book console which admin uses for addition ,deletion or updation of books
public class BookController {

	// Declaration of text fields used in the book console.
	@FXML
	private TextField txtBookID;
	@FXML
	private TextField txtBookID1;
	@FXML
	private TextField txtBookName;
	@FXML
	private TextField txtBookAuthor;
	@FXML
	private TextField txtStudentID;
	// Status which indicates whether book is added,deleted or updated
	// suceessfully.
	@FXML
	private Label lblStatus;

	// Button for save add and delete of data
	@FXML
	private Button save;
	@FXML
	private Button delete;
	@FXML
	private Stage dialogStage;

	@FXML
	private Hyperlink btnLogin;

	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method to save the form to database

	public void save() {
		try {
			// Extract the data from the view elements
			String Id = this.txtBookID.getText();
			String bookname = this.txtBookName.getText();
			String authorname = this.txtBookAuthor.getText();

			// Validate the data

			if (Id == null || Id.trim().equals("")) {
				return;
			}
			if (bookname == null || bookname.trim().equals("") || !bookname.matches("[a-zA-Z]*")) {
				return;
			}
			if (authorname == null || authorname.trim().equals("") || !authorname.matches("[a-zA-Z]*")) {
				return;
			}

			// Create the model object
			Book book = new Book();
			// Set the values from the input form

			book.setBook_id(Integer.parseInt(Id));
			book.setBook_name(bookname);
			book.setAuthor(authorname);

			// Create a DAO instance of the model
			BookDAO b = new BookDAO();
			// Use the DAO to persist the model to database
			b.create(book);

			lblStatus.setText("Book Added Successfully");

			// Close the stage after saving
			/* close(); */

		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// This method is called when admin deletes a record from database
	public void delete() {
		try {
			// Extract the data from the view elements

			String Id1 = this.txtBookID1.getText();

			// Validate the data
			if (Id1 == null || Id1.trim().equals("")) {
				return;
			}

			// Create the model object
			Book book1 = new Book();
			// Set the values from the input form

			book1.setBook_id1(Integer.parseInt(Id1));

			// Create a DAO instance of the model
			BookDAO b1 = new BookDAO();
			// Use the DAO to persist the model to database
			b1.delete(book1);

			lblStatus.setText("Book Deleted Successfully");

		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	// This method is called when admin updates the data related to book.
	public void update() {

		try {

			String Id = this.txtBookID.getText();
			String bookname = this.txtBookName.getText();
			String authorname = this.txtBookAuthor.getText();

			// Validate the data
			if (Id == null || Id.trim().equals("")) {
				return;
			}
			if (bookname == null || bookname.trim().equals("") || !bookname.matches("[a-zA-Z]*")) {
				return;
			}
			if (authorname == null || authorname.trim().equals("") || !authorname.matches("[a-zA-Z]*")) {
				return;
			}

			// Create the model object
			Book book2 = new Book();
			// Set the values from the input form

			book2.setBook_id(Integer.parseInt(Id));
			book2.setBook_name(bookname);
			book2.setAuthor(authorname);

			// Create a DAO instance of the model
			BookDAO b2 = new BookDAO();
			// Use the DAO to persist the model to database
			b2.update(book2);

			lblStatus.setText("Book Updated Successfully");

		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	// This method is called only when admin clicks on the logout button

	public void btnLogin(ActionEvent event) throws Exception {
		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/Login.fxml"));

		Stage primaryStage = new Stage();

		/* primaryStage.setTitle("Welcome"+rst.getString(uname)); */

		Scene scene = new Scene(root, 700, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();

		Stage stage = (Stage) btnLogin.getScene().getWindow();

		stage.close();
	}

}
