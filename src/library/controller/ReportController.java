package library.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import library.Main;
import library.borrowable;
import library.dao.ReportDAO;
import library.model.Book;
import library.model.Report;

//This controller consists of those methods which are rlated to the student usage like he can add and view his cart accordingly.
public class ReportController implements Initializable, borrowable {
	// Textfield declarations for whcih are used in the student console.
	@FXML
	private TextField txtStudentID;
	@FXML
	private TextField txtBookID;
	@FXML
	private TextField txtStudentName;
	@FXML
	private TextField txtBookName;
	@FXML
	private Label lblStudentName;
	@FXML
	private Label lblStudentId;

	private static Integer student_id1;
	@FXML
	private DatePicker recd_date;

	@FXML
	private javafx.scene.control.Button closeButton;

	@FXML
	private Button ViewStudent;

	@FXML
	private Hyperlink btnLogin;
	
	@FXML
	private Label lblStatus;

	@FXML
	private TableView<Book> data;
	@FXML
	private Button ViewAllBook;

	@FXML
	private TableColumn<Book, Integer> book_id;

	@FXML
	private TableColumn<Book, String> book_name;

	@FXML
	private TableColumn<Book, String> author;

	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// This method is used for passing userid and username from Login class to
	// report class once the student logs in
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		Report rep = new Report();
		lblStudentName.setText((String) rep.getStudent_name());
		String str = String.valueOf(rep.getStudent_id());
		lblStudentId.setText(str);

	}

	// This is an interface which has a method called update is called here
	@Override
	public void borrowbook() {
		// TODO Auto-generated method stub
		update();
	}

	// This method is called when student clicks on logout button
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

	// This method is invokde when student clicks on add button to add book to
	// his cart.
	public void update() {
		try {
			String Id = this.txtBookID.getText();
			String bookname = txtBookName.getText();
			String studentID = lblStudentId.getText();
			LocalDate recddate = this.recd_date.getValue();

			// Validate the data
			if (Id == null || Id.trim().equals("")) {
				return;
			}
			if (bookname == null || bookname.trim().equals("") || !bookname.matches("[a-zA-Z]*")) {
				return;
			}
			if (studentID == null || studentID.trim().equals("")) {
				return;
			}

			// Create the model object
			Report rep = new Report();
			// Set the values from the input form

			rep.setBook_id(Integer.parseInt(Id));
			rep.setBook_name(bookname);
			rep.setStudent_id(Integer.parseInt(studentID));
			rep.setRecd_date(recd_date.getValue());

			// Create a DAO instance of the model
			ReportDAO repdao = new ReportDAO();
			// Use the DAO to persist the model to database
			repdao.update(rep);
			
			lblStatus.setText("Book Added Successfully");
			
			

		} catch (NumberFormatException e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	// This method is invoked only when user wants to view the books which he
	// has taken
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void bookselectedview(ActionEvent event) throws IOException {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
		String studentID1 = lblStudentId.getText();
		// CONNECTION DATABASE

		ReportController rep1 = new ReportController();
		// Set the values from the input form

		rep1.setStudent_id(Integer.parseInt(studentID1));
		System.out.println(studentID1);

		Connection c = null;
		data = FXCollections.observableArrayList();

		try {
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		try {
			Statement statement = c.createStatement();

			ResultSet rs1 = statement.executeQuery("select * from gaurav_report where student_id ="
					+ rep1.getStudent_id());

			for (int i = 0; i < rs1.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs1.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			while (rs1.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs1.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);

			}

			// FINALLY ADD TO TableView
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data" + e);
		}

		// Create Main Scene (pop up)
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Scene scene = new Scene(tableview);
		Stage stage = new Stage();
		stage.setWidth(500);
		stage.setScene(scene);
		stage.show();
	}

	// getters and setters used for student id to be fetched
	private int getStudent_id() {
		// TODO Auto-generated method stub
		return student_id1;
	}

	private void setStudent_id(Integer student_id1) {
		// TODO Auto-generated method stub
		this.student_id1 = student_id1;
	}

	// This method is invoked whn the user wants to view the list of books
	// available in the library
	public void bookview(ActionEvent event) throws Exception {

		Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/StudentViewBook.fxml"));
		Stage primaryStage = new Stage();

		Scene scene = new Scene(root, 700, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();

	}

}
