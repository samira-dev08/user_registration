package com.company.schedule;

import com.company.entity.User;
import com.company.enums.EnumStatus;
import com.company.repository.UserRepository;
import com.company.service.EmailService;
import com.company.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private final JavaMailSender javaMailSender;
    private final EmailService emailService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    public void sendEmailByThread() {
        List<User> listUsers =userRepository.findUserByStatus(EnumStatus.ACTIVE.value);
        if (listUsers.isEmpty()){
            throw new RuntimeException("There are no new registered users") ;
        }
        for (User user : listUsers) {
            user.setStatus(EnumStatus.IN_PROGRESS.value);
            userService.updateUser(user);
            SimpleMailMessage mailMessage = userService.createTokenAndMailMessage(user);
            emailService.sendEmail(mailMessage);
            user.setStatus(EnumStatus.SUCCESS.value);
            userService.updateUser(user);
        }
    }
}
