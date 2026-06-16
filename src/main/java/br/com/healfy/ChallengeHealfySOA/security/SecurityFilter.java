package br.com.healfy.ChallengeHealfySOA.security;

import br.com.healfy.ChallengeHealfySOA.repository.UserRepository; // Verifique se este é o caminho correto do seu repo
import br.com.healfy.ChallengeHealfySOA.service.TokenService;       // Verifique se este é o caminho do seu service
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT); // Extrai o login do token
            UserDetails user = repository.findByUsername(subject); // Busca o usuário no BD

            // Cria o objeto de autenticação do Spring Security
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            // "Loga" o usuário no contexto do Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}