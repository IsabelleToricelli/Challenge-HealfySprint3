package br.com.healfy.ChallengeHealfySOA.controller;

import br.com.healfy.ChallengeHealfySOA.dto.AuthenticationData;
import br.com.healfy.ChallengeHealfySOA.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import br.com.healfy.ChallengeHealfySOA.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationData data ){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);
        return ResponseEntity.ok(tokenJWT);

    }

}
