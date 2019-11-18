package com.wawa.oms.controller;


import com.wawa.oms.exception.NotFoundException;
import com.wawa.oms.model.document.User;
import com.wawa.oms.service.UserService;
import com.wawa.oms.util.CustomResponseCode;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
@Api("Endpoint for UserController")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users =  userService.getAllUsers();
        if (CollectionUtils.isEmpty(users))
            throw  new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "No User(s) found" );
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        User user = Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);

    }
    @PostMapping()
    public User addUser(@Valid @RequestBody User user) {
        return userService.addNewUser(user);
    }

    @PutMapping()
    public User updateUser(@Valid @RequestBody User user) {
         Optional.ofNullable( userService.getUserById(user.getUserId()))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + user.getUserId()));
        //else update
        return userService.addNewUser(user);
    }

    @GetMapping(path = "/settings/{userId}")
    public Object getAllUserSettings(@PathVariable String userId) {

        Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else fetch
        return userService.getAllUserSettings(userId);
    }

    @GetMapping(path = "/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
        Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else fetch
        return userService.getUserSetting(userId, key);
    }

    @GetMapping(path = "/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
            User user =  Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
           //else Add key
            user.getUserSettings().put(key, value);
            userService.addNewUser(user);
            return "Key added";

    }

}
