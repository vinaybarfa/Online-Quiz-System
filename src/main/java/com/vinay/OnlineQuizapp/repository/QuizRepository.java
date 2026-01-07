package com.vinay.OnlineQuizapp.repository;

import com.vinay.OnlineQuizapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
