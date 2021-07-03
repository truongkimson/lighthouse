package com.nus.lighthouse.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/error/error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            mav.addObject("errorMsg", errorCodeHandler((Integer)status));
            mav.addObject("returnUrl", "/");
            return mav;
        }
        mav.addObject("errorMsg", "Unknown error, Please contact our support");
        return mav;
    }

    public String errorCodeHandler(Integer statusCode) {
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return "Page not found";
        }
        else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            return "You do not have access to this page";
        }
        else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
            return "There is something wrong with hour request";
        }
        else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "Something went wrong. We're looking into it";
        }

        return "Unknown error, Please contact our support";
    }
}
