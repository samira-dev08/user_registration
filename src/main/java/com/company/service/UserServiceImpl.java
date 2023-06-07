package com.company.service;


import com.company.entity.ConfirmationToken;
import com.company.entity.User;
import com.company.enums.EnumStatus;
import com.company.repository.ConfirmationTokenRepository;
import com.company.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public ResponseEntity<?> saveUser(User user) {

        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            return ResponseEntity.badRequest().body("Error: This email already exists!");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User added!");
    }

    public SimpleMailMessage createTokenAndMailMessage(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8085/user/confirm-account?token=" + confirmationToken.getConfirmationToken());


        return ResponseEntity.ok(mailMessage).getBody();
    }


    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByUserEmailIgnoreCase(token.getUser().getUserEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public ResponseEntity<?> updateUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("Updated!");
    }
}
