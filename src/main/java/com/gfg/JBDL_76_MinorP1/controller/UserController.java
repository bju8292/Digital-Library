package com.gfg.JBDL_76_MinorP1.controller;

import com.gfg.JBDL_76_MinorP1.dto.request.UserCreationRequest;
import com.gfg.JBDL_76_MinorP1.dto.response.UserCreationResponse;
import com.gfg.JBDL_76_MinorP1.enums.Operator;
import com.gfg.JBDL_76_MinorP1.enums.UserFilter;
import com.gfg.JBDL_76_MinorP1.model.User;
import com.gfg.JBDL_76_MinorP1.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //to deal with tomcat get path and return some values
@RequestMapping("/user")
//@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/addStudent")
    public UserCreationResponse  addStudent(@RequestBody @Validated UserCreationRequest request) {
        return userService.addStudent(request);
    }

    @GetMapping("/filter")
    public List<User> filteredByPhone(@RequestParam("filterBy") UserFilter filterBy,
                                      @RequestParam("operator") Operator operator,
                                      @RequestParam("value") String value) {
         return userService.filter(filterBy, operator, value);
    }

}
