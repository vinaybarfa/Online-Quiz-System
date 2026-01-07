package com.vinay.OnlineQuizapp.security;

import com.vinay.OnlineQuizapp.entity.Student;
import com.vinay.OnlineQuizapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements UserDetailsService {
    
    private final StudentRepository StudentRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = StudentRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Student not found"));
        CustomStudentService studentService = new CustomStudentService(student);
        return studentService;
    }
}
