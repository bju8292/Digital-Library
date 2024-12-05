package com.gfg.JBDL_76_MinorP1.service.impl;

import com.gfg.JBDL_76_MinorP1.dto.request.UserCreationRequest;
import com.gfg.JBDL_76_MinorP1.dto.response.UserCreationResponse;
import com.gfg.JBDL_76_MinorP1.enums.Operator;
import com.gfg.JBDL_76_MinorP1.enums.UserFilter;
import com.gfg.JBDL_76_MinorP1.model.User;
import com.gfg.JBDL_76_MinorP1.enums.UserType;
import com.gfg.JBDL_76_MinorP1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserCreationResponse addStudent(UserCreationRequest request) {
        User user = request.toUser();
        user.setUsertype(UserType.STUDENT);
        User userFromDb = userRepository.save(user);
        return  UserCreationResponse.builder().
                userName(userFromDb.getName()).
                userAddress(userFromDb.getAddress()).
                userEmail(userFromDb.getEmail()).
                userPhone(userFromDb.getPhoneNo()).build();
    }

    public List<User> filter(UserFilter filterBy, Operator operator, String value) {
        switch (filterBy) {
            case NAME :
                switch (operator) {
                    case EQUALS :
                        return userRepository.findByName(value);

                    case LIKE :
                        return userRepository.findByNameLike(value);
                }
        }
        return new ArrayList<>();
    }

    public User checkIfUserIsValid(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
}
