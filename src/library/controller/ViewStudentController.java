package library.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.dao.BookDAO;
import library.dao.StudentDAO;
import library.model.Student;

//This method is used for displaying of the list of students available for admin 
public class ViewStudentController implements Initializable {

	@FXML
	private TableView<Student> data;
	@FXML
	private Button ViewStudent;

	@FXML
	private TableColumn<Student, Integer> student_id;

	@FXML
	private TableColumn<Student, String> student_name;

	@FXML
	private TableColumn<Student, Integer> userid;

	@FXML
	private ObservableList<Student> list1 = FXCollections.observableArrayList();

	StudentDAO s = new StudentDAO();

	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// These are the fields displayed in table view.

		student_id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("student_id"));
		student_name.setCellValueFactory(new PropertyValueFactory<Student, String>("student_name"));
		userid.setCellValueFactory(new PropertyValueFactory<Student, Integer>("userid"));

		data.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		try {
			list1 = s.Students();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setItems(list1);
	}

}
