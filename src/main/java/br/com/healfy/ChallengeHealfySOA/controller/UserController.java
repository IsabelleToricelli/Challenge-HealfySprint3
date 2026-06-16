package br.com.healfy.ChallengeHealfySOA.controller;

import br.com.healfy.ChallengeHealfySOA.dto.UserRegistrationData;
import br.com.healfy.ChallengeHealfySOA.model.User;
import br.com.healfy.ChallengeHealfySOA.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserRegistrationData userRegistrationData) {
        if (this.userRepository.findByUsername(userRegistrationData.username())!= null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegistrationData.password());
        User newUser = new User(userRegistrationData.username(),
                encryptedPassword, userRegistrationData.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();

    }

}
