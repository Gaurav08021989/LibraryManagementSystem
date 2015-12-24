package library.model;

import java.time.LocalDate;

//Model created for calculation of penalty
public class Penalty {

	private Integer penalty_id; //
	private String penalty_amount;
	private Integer student_id;
	private LocalDate issue_date;
	private LocalDate recd_date;

	public LocalDate getIssue_date() {
		return issue_date;
	}

	// getters and setters for each attribute of the table
	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public LocalDate getRecd_date() {
		return recd_date;
	}

	public void setRecd_date(LocalDate recd_date) {
		this.recd_date = recd_date;
	}

	public Integer getPenalty_id() {
		return penalty_id;
	}

	public void setPenalty_id(Integer penalty_id) {
		this.penalty_id = penalty_id;
	}

	public String getPenalty_amount() {
		return penalty_amount;
	}

	public void setPenalty_amount(String penalty_amount) {
		this.penalty_amount = penalty_amount;
	}

	public Integer getstudent_id() {
		return student_id;
	}

	public void setstudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	// empty default constructor
	public Penalty() {

	}

	// Choice box display in UI
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
