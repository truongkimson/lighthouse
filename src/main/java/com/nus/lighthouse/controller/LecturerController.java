package com.nus.lighthouse.controller;


import com.nus.lighthouse.domain.Enrolment;
import com.nus.lighthouse.domain.Student;
import com.nus.lighthouse.domain.utils.CourseDataByLecturerData;
import com.nus.lighthouse.domain.utils.EnrolDataByCourse;
import com.nus.lighthouse.domain.utils.EnrolDataByStudent;
import com.nus.lighthouse.service.CourseService;
import com.nus.lighthouse.service.CourseServiceImpl;
import com.nus.lighthouse.service.EnrolmentService;
import com.nus.lighthouse.service.EnrolmentServiceImpl;
import com.nus.lighthouse.service.StudentService;
import com.nus.lighthouse.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	private int lecturerid=14;
	private int courseid;
	@Autowired
	private CourseService cservice;
	@Autowired
	public void setLecturerService(CourseServiceImpl cserviceImpl) {
		this.cservice = cserviceImpl;
	}
	@Autowired
	private EnrolmentService eservice;
	@Autowired
	public void setEnrolmentService(EnrolmentServiceImpl eserviceImpl) {
		this.eservice = eserviceImpl;
	}
	@Autowired
	private StudentService sservice;
	@Autowired
	public void setStudentService(StudentServiceImpl sserviceImpl) {
		this.sservice = sserviceImpl;
	}
	@RequestMapping("/home")
    public String showhome(){
		return "lecturer";
	}

    @RequestMapping("/viewCoursesTaught")
    public String getCoursesTaught(Model model){
    	List<Object[]> coursedata=cservice.findCourseDataByLecturerId(lecturerid);
    	List<CourseDataByLecturerData> converteddata=new ArrayList<CourseDataByLecturerData>();
    	for (Object[]x:coursedata)
    	{
    		CourseDataByLecturerData todisplay=new CourseDataByLecturerData();
    		todisplay.setId(Integer.valueOf(x[0].toString()));
    		todisplay.setCoursename(String.valueOf(x[1]));
    		todisplay.setMaxCap(Integer.valueOf(x[2].toString()));
    		todisplay.setCount(Integer.valueOf(x[3].toString()));
    		converteddata.add(todisplay);
    	}
		model.addAttribute("courses", converteddata);
		return "showcoursesforlecturer";
    }
    @RequestMapping("/viewcourseenrollment")
    public String selectCoursesTaught(Model model){
    	List<Object[]> coursedata1=cservice.findCourseDataByLecturerId(this.lecturerid);
    	List<CourseDataByLecturerData> converteddata1=new ArrayList<CourseDataByLecturerData>();
    	for (Object[]x:coursedata1)
    	{
    		CourseDataByLecturerData todisplay=new CourseDataByLecturerData();
    		todisplay.setId(Integer.valueOf(x[0].toString()));
    		todisplay.setCoursename(String.valueOf(x[1]));
    		converteddata1.add(todisplay);
    	}
		model.addAttribute("courses1", converteddata1);
		return "selectenrollecturer";
    }
    @RequestMapping("/selectfromcoursesedit")
    public String editCoursesTaught(Model model){
    	List<Object[]> coursedata1=cservice.findCourseDataByLecturerId(this.lecturerid);
    	List<CourseDataByLecturerData> converteddata1=new ArrayList<CourseDataByLecturerData>();
    	for (Object[]x:coursedata1)
    	{
    		CourseDataByLecturerData todisplay=new CourseDataByLecturerData();
    		todisplay.setId(Integer.valueOf(x[0].toString()));
    		todisplay.setCoursename(String.valueOf(x[1]));
    		converteddata1.add(todisplay);
    	}
		model.addAttribute("courses1", converteddata1);
		return "selectenrolleditlecturer";
    }
    @RequestMapping("/enrolment/{id}")
    public String seeenrolledcourses(Model model,@PathVariable("id") Integer id){
    	this.courseid=id;
    	List<Object[]> allenrols=eservice.findenrolmentsBycourseId(id);
    	
    	List<EnrolDataByCourse> enroldata=new ArrayList<EnrolDataByCourse>();
    	for (Object[]x:allenrols)
    	{
    		EnrolDataByCourse row=new EnrolDataByCourse();
    		row.setCoursename(String.valueOf(x[0]));
    		row.setFirstname(String.valueOf(x[1]));
    		row.setLastname(String.valueOf(x[2]));
    		row.setGrade(String.valueOf(x[3]));
    		row.setEid((int)(x[4]));
    		enroldata.add(row);
    	}
		model.addAttribute("enrolments", enroldata);
		return "showenrolmentseditlecturer";
    }
    @RequestMapping("/enrolmentview/{id}")
    public String seeenrolledcoursesnoedit(Model model,@PathVariable("id") Integer id){
    	this.courseid=id;
    	List<Object[]> allenrols=eservice.findenrolmentsBycourseId(id);
    	
    	List<EnrolDataByCourse> enroldata=new ArrayList<EnrolDataByCourse>();
    	for (Object[]x:allenrols)
    	{
    		EnrolDataByCourse row=new EnrolDataByCourse();
    		row.setCoursename(String.valueOf(x[0]));
    		row.setFirstname(String.valueOf(x[1]));
    		row.setLastname(String.valueOf(x[2]));
    		row.setGrade(String.valueOf(x[3]));
    		row.setEid((int)(x[4]));
    		enroldata.add(row);
    	}
		model.addAttribute("enrolments", enroldata);
		return "showenrolmentslecturer";
    }

    @RequestMapping("/edit/{id}")
    public String editstudentgrade(Model model,@PathVariable("id") Integer id){
    	Enrolment toedit=eservice.findById(id);
		model.addAttribute("enrolment", toedit);
		return "editenrolmentlecturer";
    }
    @RequestMapping("/save")
    public String editstudentgrade(@ModelAttribute("enrolment") @Valid Enrolment enrolment, 
			BindingResult bindingResult,  Model model){
		if (bindingResult.hasErrors()) {
			return "editenrolmentlecturer";
		}
		Enrolment toedit=eservice.findById(enrolment.getId());
		toedit.setGrade(enrolment.getGrade());
		eservice.saveEnrolment(toedit);
		return "forward:/lecturer/enrolment/"+courseid;
    }
    @RequestMapping("/seeallstudents")
    public String seeallstudents(Model model){
		List<Student> allstudents=sservice.findAllStudents();
		model.addAttribute("students", allstudents);
		return "seestudentslecturer";
    }
    @RequestMapping("/seeperformance/{id}")
    public String seeperformance(Model model,@PathVariable("id") Integer id){
		List<Object[]> performance=sservice.findStudentEnrolmentsbyStudentIdd(id);
		List<EnrolDataByStudent> enroldata=new ArrayList<EnrolDataByStudent>();
		for(Object[] x:performance)
		{
			EnrolDataByStudent record=new EnrolDataByStudent();
			record.setFirstname(String.valueOf(x[0]));
			record.setLastname(String.valueOf(x[1]));
			record.setCoursename(String.valueOf(x[2]));
			record.setGrade(String.valueOf(x[3]));
			record.setCredits(Integer.valueOf(x[4].toString()));
			record.setStatus(String.valueOf(x[5]));
			enroldata.add(record);
		}
		model.addAttribute("performance", enroldata);
		return "seeperformancelecturer";
    }
}

