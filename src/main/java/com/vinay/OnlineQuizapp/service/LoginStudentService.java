package com.vinay.OnlineQuizapp.service;

import com.vinay.OnlineQuizapp.dto.LoginStudentDTO;
import com.vinay.OnlineQuizapp.dto.StudentLoginRequest;
import com.vinay.OnlineQuizapp.dto.StudentLoginResponse;
import com.vinay.OnlineQuizapp.entity.Student;
import com.vinay.OnlineQuizapp.repository.StudentRepository;
import com.vinay.OnlineQuizapp.security.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginStudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository StudentRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    public StudentLoginResponse login(StudentLoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Student student = (Student) authentication.getPrincipal();
        String token = authUtil.generatedAccessToken(student);
        return new StudentLoginResponse(token, student.getStudentId());
    }


    public String loginStudent(@Valid LoginStudentDTO loginStudentDTO) {
        Student student = StudentRepository.findByEmail(loginStudentDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("login Student is not found"));
        if (passwordEncoder.matches(loginStudentDTO.getPassword(), student.getPassword())) {

            student.setIsLogin(true);
            StudentRepository.save(student);
            return "Login is successful.";
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }
    }
}
