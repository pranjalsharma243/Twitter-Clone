package com.fwitter.controllers;

import com.fwitter.exceptions.EmailAlreadyTakenException;
import com.fwitter.exceptions.UserDoesNotExistException;
import com.fwitter.models.ApplicationUser;
import com.fwitter.models.RegistrationObject;
import com.fwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private  final UserService userService;
    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }
    //got to http://localhost:8000/auth/register
    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken(){
        return new ResponseEntity<String>("The email you provided is already in use", HttpStatus.CONFLICT);
    }
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationObject ro)
    {
        return userService.registerUser(ro);

    }
    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesNotExist(){
        return new ResponseEntity<String>("The user you are looking for does not exist",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody LinkedHashMap<String,String> body) {

        String username=body.get("username");
        String phone=body.get("phone");

        ApplicationUser user=userService.getUserByUsername(username);
        user.setPhone(phone);
        return userService.updateUser(user);

    }

}
