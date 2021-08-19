package com.myapi.controller;

import com.myapi.DTO.LoginFormDTO;
import com.myapi.DTO.RegisterFormDTO;
import com.myapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String,String>> register(@RequestBody RegisterFormDTO form){
        Map<String,String > result=userService.register(form.getEmail(),form.getFullname(),form.getPassword());
        if(result.get("status").equals("0")){
            return  new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }else return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String,String>> register(@RequestBody LoginFormDTO form){
        Map<String,String > result=userService.login(form.getEmail(),form.getPassword());
        if(result.get("status").equals("0")){
            return  new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }else return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
