package library.controller;

// This method is used by student to display the list of books present in library 
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.dao.ReportDAO;
import library.dao.StudentDAO;
import library.model.Report;
import library.model.Student;

public class StudentViewReportController implements Initializable {

	@FXML
	private TableView<Report> data;
	/*
	 * @FXML private Button ViewStudent;
	 */

	@FXML
	private TableColumn<Report, String> book_name;

	@FXML
	private TableColumn<Report, Integer> book_id;

	@FXML
	private TableColumn<Report, Date> Issue_Date;

	@FXML
	private TableColumn<Report, Date> due_date;

	@FXML
	private ObservableList<Report> list1 = FXCollections.observableArrayList();

	ReportDAO r = new ReportDAO();

	@Override
	// TODO Auto-generated method stub
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// View.setOnAction(new EventHandler<ActionEvent>() {

		// public void View(ActionEvent event) {

		book_name.setCellValueFactory(new PropertyValueFactory<Report, String>("book_name"));
		book_id.setCellValueFactory(new PropertyValueFactory<Report, Integer>("book_id"));

		Issue_Date.setCellValueFactory(new PropertyValueFactory<Report, Date>("Issue_Date"));

		due_date.setCellValueFactory(new PropertyValueFactory<Report, Date>("due_date"));

		data.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		try {
			list1 = r.Reports();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setItems(list1);
		;
	}

}
