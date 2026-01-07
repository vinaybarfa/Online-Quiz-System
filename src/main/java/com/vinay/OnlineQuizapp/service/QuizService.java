package com.vinay.OnlineQuizapp.service;

import com.vinay.OnlineQuizapp.dto.QuestionDTO;
import com.vinay.OnlineQuizapp.entity.Question;
import com.vinay.OnlineQuizapp.entity.Quiz;
import com.vinay.OnlineQuizapp.entity.Response;
import com.vinay.OnlineQuizapp.repository.QuestionRepository;
import com.vinay.OnlineQuizapp.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public String createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = Quiz.builder().title(title).questions(questions).build();
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestion(questions);
        quizRepository.save(quiz);
        return "Success";
    }


    public ResponseEntity<List<QuestionDTO>> getQuizQuestion(Long id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if(quizOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
        }
        List<QuestionDTO> questionDTOS = quizOptional.get()
                .getQuestions()
                .stream()
                .map(q -> modelMapper.map(q, QuestionDTO.class)).toList();

        return ResponseEntity.ok(questionDTOS);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(Long.valueOf(id)).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0, i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
