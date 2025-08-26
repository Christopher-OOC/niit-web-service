package com.javalord.niit_web_service.niit_web_service.security;

import com.javalord.niit_web_service.niit_web_service.model.entity.Role;
import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import com.javalord.niit_web_service.niit_web_service.repository.StudentRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private StudentRepository studentRepository;

    public AuthorizationFilter(AuthenticationManager authenticationManager, StudentRepository studentRepository) {
        super(authenticationManager);
        this.studentRepository = studentRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    new ArrayList<>()
            ));

            chain.doFilter(request, response);
        } else {
            String token = authHeader.replace("Bearer ", "");

            String key = "dfghjksldSLKJLKJDLIKFJDLFUIDILFHDF" +
                    "JHHJDILUJDFDFLKDFJDKJFDDFDFFDJHGFUDJGFDGJFGFDJGFDGFFmwKJHHGJDUGYHJDFJDFDJFGF" +
                    "retfyhujikl;kjhgfdghjkl;kjhgfhjkl;'lkjkhgfghjhkl;'lkljkhjghfg" +
                    "hgjkl;'lkjhgfgjhjkkll;;';kjkhjgfgghjhjkkl;likujhyuhgfghjhkjlk;lolkhjghfgchvgjkhjlk;" +
                    "cgvhgjhkjl;kkjhjgfgfvgnjmk;l;kjkhjghffhgjhkjlk;lkhjghgfhjkl;ljkhjghfghgjhkl;likujyhygfhhjj" +
                    "fghjkl;kjhgfrtgfhjjklk;lkjkhjFJGNFKJGFGF";

            SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());

            try {
                JwtParser jwtParser = Jwts
                        .parser()
                        .setSigningKey(secretKey)
                        .decryptWith(secretKey)
                        .build();

                Claims body = (Claims) jwtParser.parse(token).getBody();
                String email = body.getSubject();
                Student student = studentRepository.findByEmail(email);

                if (student == null) {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            null,
                            null,
                            new ArrayList<>()
                    ));

                    chain.doFilter(request, response);
                }
                else {

                    List<Role> roles = student.getRoles();
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    for (Role role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role.getName()));
                    }

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            student.getEmail(),
                            student.getPassword(),
                            authorities
                    ));

                    chain.doFilter(request, response);
                }
            }
            catch (Exception ex) {
                throw new RuntimeException("Error occurs");
            }
        }
    }
}
