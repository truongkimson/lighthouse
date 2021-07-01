package com.nus.lighthouse.domain.utils;

public class EnrolDataByStudent extends EnrolDataByCourse {
	private int credits;
	private String status;
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EnrolDataByStudent(int credits, String status) {
		super();
		this.credits = credits;
		this.status = status;
	}
	public EnrolDataByStudent() {
		// TODO Auto-generated constructor stub
	}
	
}
