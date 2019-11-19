package com.wawa.oms.controller;


import com.wawa.oms.exception.NotFoundException;
import com.wawa.oms.model.document.Sample;
import com.wawa.oms.service.SampleService;
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
@RequestMapping(value = "/api/v1/users")
@Api("Endpoint for SampleController")
public class SampleController {
    private static final Logger LOG = LoggerFactory.getLogger(SampleController.class);

    private SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping()
    public ResponseEntity<List<Sample>> getUsers() {
        List<Sample> users = sampleService.getAllUsers();
        if (CollectionUtils.isEmpty(users))
            throw  new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "No User(s) found" );
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(path = "/paging")
    public ResponseEntity<List<Sample>> getAllUsersPaged(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Sample> users = sampleService.getAllUsersPaged(pageNumber, pageSize);
        if (CollectionUtils.isEmpty(users))
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "No User(s) found");
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Sample> getUserById(@PathVariable("userId") String userId) {
        Sample user = Optional.ofNullable(sampleService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);

    }
    @PostMapping()
    public Sample addUser(@Valid @RequestBody Sample user) {
        return sampleService.addNewUser(user);
    }

    @PutMapping()
    public Sample updateUser(@Valid @RequestBody Sample user) {
        Optional.ofNullable(sampleService.getUserById(user.getUserId()))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + user.getUserId()));
        //else update
        return sampleService.addNewUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        Sample user = Optional.ofNullable(sampleService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else delete
        sampleService.deleteUser(user);
    }

    @GetMapping(path = "/settings/{userId}")
    public Object getAllUserSettings(@PathVariable("userId") String userId) {

        Optional.ofNullable(sampleService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else fetch
        return sampleService.getAllUserSettings(userId);
    }

    @GetMapping(path = "/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable("userId") String userId,
                                 @PathVariable("key") String key) {
        Optional.ofNullable(sampleService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
        //else fetch
        return sampleService.getUserSetting(userId, key);
    }

    @GetMapping(path = "/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable("userId") String userId,
                                 @PathVariable("key") String key,
                                 @PathVariable("value") String value) {
        Sample user = Optional.ofNullable(sampleService.getUserById(userId))
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "User not found for this id :: " + userId));
           //else Add key
            user.getUserSettings().put(key, value);
        sampleService.addNewUser(user);
            return "Key added";

    }

}
