package br.com.healfy.ChallengeHealfySOA;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class GeradorDeSenha {
        public static void main(String[] args) {
            System.out.println(new BCryptPasswordEncoder().encode("123456"));
        }
}
