package library.model;

import java.time.LocalDate;
import java.util.Date;

import javafx.fxml.FXML;

//Model object created for book 
public class Book {
	// Variables initialized for book
	private Integer book_id;
	private String book_name;
	private String author;
	private Integer book_id1;
	private Integer isavailable;
	private Integer student_id;

	// gtters and setters
	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public Integer getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(Integer isavailable) {
		this.isavailable = isavailable;
	}

	// Paremeterized constructor of book model
	public Book(Integer book_id, String book_name, String author) {
		this.book_id = book_id;
		this.book_name = book_name;
		this.author = author;
	}

	// Default constructor for Book Model
	public Book() {

	}

	// Constructor used for deletion of a book
	public Book(Integer book_id1) {
		this.book_id1 = book_id1;
	}

	// getter and setter
	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	// Getters and setters for deletion of book
	public Integer getBook_id1() {
		return book_id1;
	}

	public void setBook_id1(Integer book_id1) {
		this.book_id1 = book_id1;
	}

	// getters and setters for Book

	public Integer getBook_id(int parseInt) {
		// TODO Auto-generated method stub
		return book_id;

	}

	public void setBook_id(int parseInt) {
		// TODO Auto-generated method stub

		this.book_id = parseInt;

	}

	public void getBook_name(String bookname) {
		// TODO Auto-generated method stub

		this.book_name = bookname;

	}

	public String setBook_name1(String bookname) {
		// TODO Auto-generated method stub
		return book_name;
	}

	public void getAuthor(String authorname) {
		// TODO Auto-generated method stub

		this.author = authorname;
	}

	public String setAuthor1(String authorname) {
		// TODO Auto-generated method stub
		return author;

	}

}
