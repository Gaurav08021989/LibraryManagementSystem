package library.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
import library.dao.LoginDAO;
import library.dao.StudentDAO;
import library.model.Report;
import library.model.Student;
import library.model.User;

public class LoginController {

	// Textfield for username
	@FXML
	private TextField txtUsername;

	// Textfield for password
	@FXML
	private TextField txtPassword;
	
	@FXML
	private TextField userid;
	
	@FXML
	private TextField userid1;

	// Displays the status on click of login
	@FXML
	private Label lblStatus;
	
	@FXML
	private Label lblStatus1;

	// Button name used for login
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button btnLogin1;

	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method used for logging into application
	public void Login(ActionEvent event) throws Exception {

		// username value entered in textfield area is stored in uname.
		String uname = txtUsername.getText();

		// Password value is stored password1
		String password1 = txtPassword.getText();

		// Connection to database.
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		// Query for fetching usertype,userid and name from Gaurav_Login
		PreparedStatement pst = connection
				.prepareStatement("Select usertype,userid,name from Gaurav_Login where name = ? and password = ? ");

		// Username and Password is set at respective fields
		pst.setString(1, uname);
		pst.setString(2, password1);

		// Result of Query executed is stored in resultset
		ResultSet rst = pst.executeQuery();

		// Fetches the next value from Gaurav_Login
		if (rst.next()) {

			// Username and userID is fetched here and displayed on login
			String uname1 = rst.getString("name");
			String type = rst.getString("usertype");
			String type1 = rst.getString("userid");

			// Admin user details is displayed when admin logs in
			User user = new User();
			user.setUsername(uname1);
			user.setId(Integer.parseInt(type1));

			/*
			 * Student stu = new Student(); stu.setStudent_name(uname1);
			 * stu.setStudent_id(Integer.parseInt(type1));
			 */

			// Student details fetched here and displayed when student logs in
			Report rep = new Report();
			rep.setStudent_name(uname1);
			rep.setStudent_id(Integer.parseInt(type1));

			// Checks if usertype is student or admin
			if ("Student".equals(type)) {

				// Displays Student View page when student logs in
				Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/StudentView.fxml"));

				Stage primaryStage = new Stage();

				// Length and width of Anchor tag set here
				Scene scene = new Scene(root, 700, 500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);

				primaryStage.show();
				// Screen closes when user/Admin navigates to a new screen
				Stage stage = (Stage) btnLogin.getScene().getWindow();

				stage.close();
			}
			// Checks if usertype is admin
			else if ("Admin".equals(type)) {
				// redirect to seller page
				// Displays Student View page when admin logs in
				Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/Admin.fxml"));
				Stage primaryStage = new Stage();

				// Length and width of Anchor tag set here
				Scene scene = new Scene(root, 700, 500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);

				primaryStage.show();
				// Screen closes when user/Admin navigates to a new screen
				Stage stage = (Stage) btnLogin.getScene().getWindow();

				stage.close();
			}

		}
		
				

		lblStatus.setText("Enter Username and Password");

		// Displays message if userid/password is invalid
		

	}
	public void btnLogin1(ActionEvent event) {
		try {

			System.out.println(userid1.getText());
			//Extract the data from the view elements
			String username = this.txtUsername.getText();
			String password = this.txtPassword.getText();
			String userid = this.userid1.getText();

			
			
			//Validate the data
			if(username == null || username.trim().equals("") || !username.matches("[a-zA-Z]*")) {
				return;
			}
			if(password == null || password.trim().equals("") || !password.matches("[a-zA-Z]*")) {
				return;
			}
			if(userid == null || userid.trim().equals("")) {
				return;
			}
			
			//Create the model object
			User user = new User();
			
			
			
			user.setUsername(username);
			user.setPassword(password);
			user.setId(Integer.parseInt(userid));
			
			
			
			
			//Create a DAO instance of the model
			LoginDAO login = new LoginDAO();
			//Use the DAO to persist the model to database
			login.create(user);
			

			lblStatus1.setText("Student Created Sucessfully");
			

			
		} catch (NumberFormatException e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);
		}
				


	}
	
	
	

}
