package com.nus.lighthouse.domain.utils;

public class CourseDataByLecturerData {
	private int id;
	private String coursename;
	private int maxCap;
	private int count;
	public CourseDataByLecturerData() {};
	public CourseDataByLecturerData(int id, String coursename, int maxCap, int count) {
		super();
		this.id = id;
		this.coursename = coursename;
		this.maxCap = maxCap;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getMaxCap() {
		return maxCap;
	}
	public void setMaxCap(int maxCap) {
		this.maxCap = maxCap;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
