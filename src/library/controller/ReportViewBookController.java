package library.controller;

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
import library.dao.BookDAO;
import library.dao.ReportDAO;
import library.model.Book;
import library.model.Report;

//This code is a table view display used for students to see the list of books in library.

public class ReportViewBookController implements Initializable {
	@FXML
	private TableView<Book> data;
	@FXML
	private Button ViewBook;

	@FXML
	private TableColumn<Book, Integer> book_id;

	@FXML
	private TableColumn<Book, String> book_name;

	@FXML
	private TableColumn<Book, String> author;

	@FXML
	private ObservableList<Book> list1 = FXCollections.observableArrayList();
	// Object of BookDAO is created
	BookDAO b = new BookDAO();

	// This method consists of the table view display of the list of books to
	// the student
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// View.setOnAction(new EventHandler<ActionEvent>() {

		// public void View(ActionEvent event) {

		book_id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("book_id"));
		book_name.setCellValueFactory(new PropertyValueFactory<Book, String>("book_name"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));

		data.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		try {
			list1 = b.Books();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setItems(list1);
	}

}
