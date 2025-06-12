package org.blacksage.learn.easyschool.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice(basePackages = "org.blacksage.learn.easyschool.controllers")
public class MVCExceptionController {

        @ExceptionHandler(Exception.class)
        public ModelAndView exceptionHandler(Exception ex) {
                log.info("exceptionHandler caught: {}", ex.getMessage());
                ModelAndView errorPage = new ModelAndView();
                errorPage.setViewName("error");
                errorPage.addObject("errormsg", ex.getMessage());
                return errorPage;
        }
}
