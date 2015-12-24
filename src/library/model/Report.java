package library.model;

//This model basically consists of setters and getters of report
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javafx.fxml.FXML;

public class Report {

	// Instance variable declaration
	private static String student_name;
	private String book_name;
	private static Integer student_id;
	private Integer student_id1;

	private Integer book_id;
	private Date Issue_date;
	private Date Due_Date;
	private Boolean isavailable;
	@FXML
	private LocalDate recd_date;

	// getters and setters for each attribute of the table

	public Integer getStudent_id1() {
		return student_id1;
	}

	public void setStudent_id1(Integer student_id1) {
		this.student_id1 = student_id1;
	}

	public LocalDate getRecd_date() {
		return recd_date;
	}

	public void setRecd_date(LocalDate recd_date) {
		this.recd_date = recd_date;
	}

	public Boolean getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(Boolean isavailable) {
		this.isavailable = isavailable;
	}

	public Date getIssue_date() {
		return Issue_date;
	}

	public void setIssue_date(Date issue_date) {
		Issue_date = issue_date;
	}

	public Date getDue_Date() {
		return Due_Date;
	}

	public void setDue_Date(Date due_Date) {
		Due_Date = due_Date;
	}

	public static String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	// Constructor for passing values to DAO object
	public Report(String book_name, Integer student_id, Integer book_id, Date issue_date, Date due_Date,
			LocalDate recd_date) {

		this.book_name = book_name;
		this.student_id = student_id;
		this.book_id = book_id;
		this.Issue_date = issue_date;
		this.Due_Date = due_Date;
		this.recd_date = recd_date;

	}

	// getter and setters for each attributes of class
	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
		System.out.println(student_id);
	}

	public static Integer getStudent_id() {
		return student_id;
	}

	public Integer getBook_id() {

		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	// default empty constructors
	public Report() {
		// TODO Auto-generated constructor stub
	}

}
