package com.nus.lighthouse.controller.ControllerAdvice;

import com.nus.lighthouse.domain.User;
import com.nus.lighthouse.exception.CourseFullException;
import com.nus.lighthouse.exception.CourseNotFoundException;
import com.nus.lighthouse.exception.LecturerNotFoundException;
import com.nus.lighthouse.exception.StudentNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("currentUser")
    public User getCurrentUser(HttpSession session) {
        return (User)session.getAttribute("currentUser");
    }

    @ExceptionHandler(CourseFullException.class)
    public ModelAndView handleCourseFullError(Exception ex) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorMsg", ex.getMessage());
        mav.addObject("returnUrl", "/admin/enrolment");
        return mav;
    }

    @ExceptionHandler( {StudentNotFoundException.class, LecturerNotFoundException.class, CourseNotFoundException.class} )
    public ModelAndView handleStudentNotFound(Exception ex) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorMsg", ex.getMessage());
        mav.addObject("returnUrl", "/");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorMsg", ex.getMessage());
        mav.addObject("returnUrl", "/");
        return mav;
    }
}
