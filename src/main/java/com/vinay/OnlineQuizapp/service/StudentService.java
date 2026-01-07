package com.vinay.OnlineQuizapp.service;

import com.vinay.OnlineQuizapp.dto.LoginStudentDTO;
import com.vinay.OnlineQuizapp.dto.StudentDTO;
import com.vinay.OnlineQuizapp.entity.Student;
import com.vinay.OnlineQuizapp.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository registerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public Student registerStudent(@Valid StudentDTO studentDTO) {
        Student createdStudent = modelMapper.map(studentDTO, Student.class);
        createdStudent.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        return registerRepository.save(createdStudent);
    }

    public List<Student> getAllStudent() {
        List<Student> student = registerRepository.findAll();
        if (student.isEmpty()) {
            throw new EntityNotFoundException("No students found.");
        }
        return student;
    }

    public String loginStudent(@Valid LoginStudentDTO loginStudentDTO) {
        Student Student = registerRepository.findByEmail(loginStudentDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("login Student is not found"));
        if (passwordEncoder.matches(loginStudentDTO.getPassword(), Student.getPassword())) {
            return "Login is successful.";
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }
    }

    public void deleteStudentById(Long studentId) {
        registerRepository.deleteById(studentId);
    }
}
