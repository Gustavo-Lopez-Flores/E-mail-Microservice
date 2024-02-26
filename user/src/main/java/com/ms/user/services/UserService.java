package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserProducer userProducer;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel= userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
