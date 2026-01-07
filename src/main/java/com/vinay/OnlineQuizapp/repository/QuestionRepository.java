package com.vinay.OnlineQuizapp.repository;

import com.vinay.OnlineQuizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query("SELECT q FROM Question q where q.category = :cate")
    List<Question> findByCategory(@Param("cate") String cate);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQuestion", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(@Param("category") String category, @Param("noOfQuestion") int numQ);

    @Query(value = "SELECT * FROM question q WHERE  q.right_answer = :rightAnswer" , nativeQuery = true)
    String findByRightAnswer(@Param("rightAnswer") String rightAnswer);

}
