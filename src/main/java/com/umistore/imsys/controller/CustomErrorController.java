package com.umistore.imsys.controller;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Log4j2
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/api/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        // You can retrieve the error from the request and log it
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        // Log the error details for debugging
        logger.error("Error with status code " + status + " and exception " + exception);

        // Return an appropriate response entity
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            return new ResponseEntity<>("Error Code: " + statusCode, HttpStatus.valueOf(statusCode));
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getErrorPath() {
        return "/error";
    }
}
