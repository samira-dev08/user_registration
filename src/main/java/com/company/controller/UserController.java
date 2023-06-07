package com.company.controller;

import com.company.entity.User;
import com.company.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.DataInput;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping({"/", "/register"})
    public ModelAndView RegistrationForm() {
        ModelAndView mv=new ModelAndView("registrationForm");
        return mv;
    }


    @PostMapping(value = "/registration",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView registerUser(User user) {
        ResponseEntity<?> response= userService.saveUser(user);
        ModelAndView mv=new ModelAndView();
        if (response.getStatusCodeValue()!=200){
            mv.addObject("message", response.getStatusCode().getReasonPhrase());
            mv.setViewName("error");
        }else{
            mv.addObject("userName", user.getUserName());
            mv.setViewName("registrationSuccess");
        }
        return mv;
    }


    @RequestMapping(value = "/confirm-account",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam ("token") String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }

}
