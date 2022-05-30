package com.example.customerrorpage.controller;

import com.example.customerrorpage.exception.ForbiddenException;
import com.example.customerrorpage.exception.InternalServerErrorException;
import com.example.customerrorpage.exception.NotFoundException;
import com.example.customerrorpage.exception.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebController implements ErrorController {

    // Trả về trang chủ
    @GetMapping("/")
    public String getHome() {
        return "index";
    }
    
//    @RequestMapping("/error")
//    public String handleError() {
//        return "error/error";
//    }

    @GetMapping("/401")
    public void return401Page() {
        throw new UnauthorizedException("Lỗi Unauthorized xảy ra");
    }

    @GetMapping("/403")
    public void return403Page() {
        throw new ForbiddenException("Lỗi Forbidden xảy ra");
    }

    @GetMapping("/404")
    public String return404Page() {
        throw new NotFoundException("Lỗi NotFound xảy ra");
    }

    @GetMapping("/500")
    public void return500Page() {
        throw new InternalServerErrorException("Lỗi InternalServer xảy ra");
    }

    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            }
        }

        return "error/error";
    }
}
