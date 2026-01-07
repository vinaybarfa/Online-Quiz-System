package com.vinay.OnlineQuizapp.service;

import com.vinay.OnlineQuizapp.entity.Student;
import com.vinay.OnlineQuizapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutStudentService {

    private final StudentRepository studentRepository;

    public String logoutStudentAccount(Long studentId) {
        Student loginRequest = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student is not logged in"));
        loginRequest.setIsLogin(false);
        studentRepository.save(loginRequest);
        return "logout Successful";
    }
}
