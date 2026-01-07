package com.vinay.OnlineQuizapp.repository;

import com.vinay.OnlineQuizapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String username);
    List<Student> findByEmailAndSentimentAnalysis(String email, Boolean sentimentAnalysis);

    List<Student> findBySentimentAnalysis(boolean b);
}