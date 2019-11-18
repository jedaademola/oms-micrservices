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

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
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

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        User user = Optional.ofNullable(userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else delete
        userService.deleteUser(user);
    }

    @GetMapping(path = "/settings/{userId}")
    public Object getAllUserSettings(@PathVariable("userId") String userId) {

        Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else fetch
        return userService.getAllUserSettings(userId);
    }

    @GetMapping(path = "/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable("userId") String userId,
                                 @PathVariable("key") String key) {
        Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else fetch
        return userService.getUserSetting(userId, key);
    }

    @GetMapping(path = "/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable("userId") String userId,
                                 @PathVariable("key") String key,
                                 @PathVariable("value") String value) {
            User user =  Optional.ofNullable( userService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
           //else Add key
            user.getUserSettings().put(key, value);
            userService.addNewUser(user);
            return "Key added";

    }

}
