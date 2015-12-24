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
import library.model.Book;

//This method is used by admin to view the list of books available in library
public class ViewBookController implements Initializable {
	// Fields declaration for displaying of data in table view format
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
	// BookDAO object calling
	BookDAO b = new BookDAO();

	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// Displaying each column in table view
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
