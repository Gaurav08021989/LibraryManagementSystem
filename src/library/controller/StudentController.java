package library.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import library.Main;
import library.dao.BookDAO;
import library.dao.StudentDAO;
import library.model.Book;
import library.model.Student;

//This class is the student controller class which basically consists of the list of actions performed by admin on student like addition deletion and updation of student
public class StudentController extends LoginController {

	// Textfield and button declarations which are used in student console for
	// admin to perform necessary actions
	@FXML
	private TextField txtStudentID;

	@FXML
	private TextField txtStudentID1;
	@FXML
	private TextField userid;
	@FXML
	private TextField userid1;
	@FXML
	private Label lblStatus;

	@FXML
	private TextField txtStudentName;

	@FXML
	TableView tblTableView;
	@FXML
	private Button save;
	@FXML
	private Button delete;
	@FXML
	private Stage dialogStage;

	// Database connection settings
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	// Method to save the form to database

	public void studentsave() {

		try {

			// Extract the data from the view elements
			String Id = this.txtStudentID.getText();
			String studentname = this.txtStudentName.getText();
			String userid = this.userid.getText();

			// Validate the data
			if (Id == null || Id.trim().equals("")) {
				return;
			}
			if (studentname == null || studentname.trim().equals("") || !studentname.matches("[a-zA-Z]*")) {
				return;
			}
			if (userid == null || userid.trim().equals("")) {
				return;
			}

			// Create the model object
			Student stu = new Student();
			// Set the values from the input form

			stu.setStudent_id(Integer.parseInt(Id));

			stu.setStudent_name(studentname);
			stu.setUserid(Integer.parseInt(userid));

			// Create a DAO instance of the model
			StudentDAO s = new StudentDAO();
			// Use the DAO to persist the model to database
			s.create(stu);

			lblStatus.setText("Student Added Successfully");

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

	// This method is called when admin deletes a record of a student
	public void studentdelete() {
		try {

			// Extract the data from the view elements

			String userid1 = this.userid1.getText();

			// Validate the data

			if (userid1 == null || userid1.trim().equals("")) {
				return;
			}

			// Create the model object
			Student stu1 = new Student();
			// Set the values from the input form

			stu1.setUserid1(Integer.parseInt(userid1));

			// Create a DAO instance of the model
			StudentDAO s = new StudentDAO();
			// Use the DAO to persist the model to database
			s.delete(stu1);

			lblStatus.setText("Student Deleted Successfully");

		} catch (NumberFormatException e) {

			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);

		}

	}

	// This method is invoked when admin wants to update student details
	public void update() {

		try {

			String Id = this.txtStudentID.getText();
			String studentname = this.txtStudentName.getText();
			String userid = this.userid.getText();

			// Validate the data
			if (Id == null || Id.trim().equals("")) {
				return;
			}
			if (studentname == null || studentname.trim().equals("") || !studentname.matches("[a-zA-Z]*")) {
				return;
			}
			if (userid == null || userid.trim().equals("")) {
				return;
			}
			// Create the model object
			Student stu2 = new Student();
			// Set the values from the input form

			stu2.setStudent_id(Integer.parseInt(Id));
			stu2.setStudent_name(studentname);
			stu2.setUserid(Integer.parseInt(userid));

			// Create a DAO instance of the model
			StudentDAO stdao = new StudentDAO();
			// Use the DAO to persist the model to database
			stdao.update(stu2);

			lblStatus.setText("Student Updated Successfully");

		} catch (NumberFormatException e) {

			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);

		}

	}

}
