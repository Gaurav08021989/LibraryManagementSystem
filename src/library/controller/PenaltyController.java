package library.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.dao.PenaltyDAO;
import library.model.Penalty;

public class PenaltyController {

	// List of String which stores the penalty amount and displays in combo box
	@FXML
	ObservableList<String> fineamount = FXCollections.observableArrayList(" $5 ", " $10 ", " $15 ", " No Fine");
	// Textfield declarations which are used in the console.
	@FXML
	private TextField penalty_id;
	@FXML
	private TextField student_id;

	@FXML
	private ChoiceBox penalty_amount;
	
	@FXML
	private Label lblStatus;
	@FXML
	private DatePicker issue_date;
	@FXML
	private DatePicker recd_date;

	@FXML
	private void initialize() {

		penalty_amount.setItems(fineamount);
	}

	// Method to save the form to database
	public void AddFine() {
		// Extract the data from the view elements
		try {
			String STUDENT_ID = this.student_id.getText();
			String FINE_ID = this.penalty_id.getText();

			// int ISSUE_DATE1 = Integer.parseInt(ISSUE_DATE);

			int STUDENT_ID1 = Integer.parseInt(STUDENT_ID);
			// Create the model object
			Penalty fine = new Penalty();
			// Set the values from the input form
			System.out.println(FINE_ID);
			fine.setPenalty_id(Integer.parseInt(FINE_ID));
			fine.setstudent_id(STUDENT_ID1);
			fine.setRecd_date(recd_date.getValue());
			fine.setIssue_date(issue_date.getValue());

			fine.setPenalty_amount((String) penalty_amount.getValue());

			// Create a DAO instance of the model
			PenaltyDAO b = new PenaltyDAO();
			// Use the DAO to persist the model to database
			b.create(fine);
			// Close the stage after saving
			
			lblStatus.setText("Penalty Added Successfully");

			
		} catch (NumberFormatException e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "Please enter data in correct  format", "Result",
					JOptionPane.PLAIN_MESSAGE);
		}

		
	}

}
