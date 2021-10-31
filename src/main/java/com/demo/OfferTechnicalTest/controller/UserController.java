package com.demo.OfferTechnicalTest.controller;

import com.demo.OfferTechnicalTest.exception.NotAllowedUserException;
import com.demo.OfferTechnicalTest.exception.UserNotFoundException;
import com.demo.OfferTechnicalTest.model.User;
import com.demo.OfferTechnicalTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/offerTechnicalTest")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long id)
            throws UserNotFoundException {
        User user =
                userService
                        .findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user)
            throws NotAllowedUserException {
        User userToBeSaved = userService.createUser(user);
        return ResponseEntity.ok().body(userToBeSaved);
    }
}
