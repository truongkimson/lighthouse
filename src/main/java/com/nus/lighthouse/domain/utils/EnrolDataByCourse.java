package com.nus.lighthouse.domain.utils;

public class EnrolDataByCourse {
	private String coursename;
	private String firstname;
	private String lastname;
	private String grade;
	private int eid;
	public EnrolDataByCourse() {};
	public EnrolDataByCourse(String coursename, String firstname, String lastname, String grade, int eid) {
		super();
		this.coursename = coursename;
		this.firstname = firstname;
		this.lastname = lastname;
		this.grade = grade;
		this.eid = eid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	
}
