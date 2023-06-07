package com.company.service;

import com.company.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;

public interface UserService {

    ResponseEntity<?> saveUser(User user);
    ResponseEntity<?> updateUser(User user);


    ResponseEntity<?> confirmEmail(String confirmationToken);
    SimpleMailMessage createTokenAndMailMessage(User user);
}
