package library.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import org.omg.CORBA.PUBLIC_MEMBER;

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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import library.Main;
import library.model.User;

//Admin home page which has other buttons
public class AdminController extends LoginController implements Initializable {

	// Textfield for entering book id
	@FXML
	private TextField txtBookID;

	// Textfield for entering book name
	@FXML
	private TextField txtBookName;

	// Textfield for entering author name
	@FXML
	private TextField txtBookAuthor;

	// Close the aplication
	@FXML
	private javafx.scene.control.Button closeButton;

	// Button to viewbook
	@FXML
	private Button ViewBook;

	// Username displaying label
	@FXML
	private Label lblAdminUserName;

	// Userid passed from Login Object.UserID displayed on application
	@FXML
	private Label lblAdminUserId;

	// Logout Hyperlink
	@FXML
	private Hyperlink btnLogin;

	// Database connection details
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// On click of logout link method is executed
	public void btnLogin(ActionEvent event) throws Exception {
		// Navigation of Page to main page on click of logout link
		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/Login.fxml"));

		// Primary Stage displayed
		Stage primaryStage = new Stage();

		// Length and Width os Scene is set
		Scene scene = new Scene(root, 700, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Set the scene to the stage
		primaryStage.setScene(scene);

		primaryStage.show();
		// Primary Stage is shown
		Stage stage = (Stage) btnLogin.getScene().getWindow();

		stage.close();
	}

	public void btnbook(ActionEvent event) throws IOException {

		// Navigation of page on click on clck of button
		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/Book.fxml"));
		// Primary Stage displayed
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Set the scene to the stage
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	// On click of button screen navigates to Student screen
	public void btnstudent(ActionEvent event) throws IOException {

		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/Student.fxml"));
		// Primary Stage displayed
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	public void CreateStudent(ActionEvent event) throws IOException {

		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/StudentCreate.fxml"));
		// Primary Stage displayed
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();
		Stage stage = (Stage) closeButton.getScene().getWindow();

		stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		User us = new User();
		lblAdminUserName.setText((String) us.getUsername());

		String str = String.valueOf(us.getId());
		lblAdminUserId.setText(str);

	}

	// On click of the button TableView method is displayed which shows list of
	// books
	public void ViewBook(ActionEvent event) throws IOException {

		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/bookstable.fxml"));
		// Primary Stage displayed
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();
		/*
		 * Stage stage = (Stage) closeButton.getScene().getWindow();
		 * 
		 * stage.close();
		 */

	}

	// Admin navigates to Student scren to complete the CRUD operations for
	// Student
	public void ViewStudent(ActionEvent event) throws IOException {
		//
		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/studentstable.fxml"));
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();
		/*
		 * Stage stage = (Stage) closeButton.getScene().getWindow();
		 * 
		 * stage.close();
		 */

	}

	// Admin is dispayed the report which shows the list of students who have
	// taken books
	public void ViewReport(ActionEvent event) throws IOException {
		//
		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/AdminViewReport.fxml"));
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	// Admin is displayed the fine view on navigation to the ink.
	public void ViewFine(ActionEvent event) throws IOException {

		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/Penalty.fxml"));
		Stage primaryStage = new Stage();
		// Length and Width os Scene is set
		Scene scene = new Scene(root, 800, 500);
		// Application.css file loaded
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	// List of Books is viewed by Admin

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bookview(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;

		// CONNECTION DATABASE
		Connection c;
		data = FXCollections.observableArrayList();
		try {
			c = DriverManager.getConnection(url, username, password);
			;
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "select * from gaurav_report";
			// ResultSet
			ResultSet rs = c.createStatement().executeQuery(SQL);

			// Code for table view display
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// Using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);
			}

			// FINALLY ADD TO TableView
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

		// Create Main Scene (pop up)
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Scene scene = new Scene(tableview);
		Stage stage = new Stage();
		stage.setWidth(500);
		stage.setScene(scene);
		stage.show();

	}

}
