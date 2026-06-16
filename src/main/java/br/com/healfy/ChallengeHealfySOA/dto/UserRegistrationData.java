package br.com.healfy.ChallengeHealfySOA.dto;

import br.com.healfy.ChallengeHealfySOA.enums.Role;
import org.springframework.security.core.GrantedAuthority;

public record UserRegistrationData(String username, String password, Role role)  {
}
