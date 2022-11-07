package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;

@Controller
//@RequestMapping("/user")
public class UsersController {

    @GetMapping("/api/user")
    @ResponseBody
    public ResponseEntity<User> showUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();

        return new ResponseEntity<>(myUserDetails.getUser(), HttpStatus.OK);
    }
    @GetMapping("/user")
    public String userPage() {
        return "users";
    }
}
