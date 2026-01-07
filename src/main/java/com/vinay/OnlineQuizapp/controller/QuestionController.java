package com.vinay.OnlineQuizapp.controller;

import com.vinay.OnlineQuizapp.Kafka.KafkaProducer;
import com.vinay.OnlineQuizapp.dto.AnswerSubmitionDTO;
import com.vinay.OnlineQuizapp.dto.QuestionDTO;
import com.vinay.OnlineQuizapp.entity.Question;
import com.vinay.OnlineQuizapp.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Tag(
        name = "Question APIs",
        description = "Provides APIs to manage quiz questions, including adding, retrieving, deleting, and submitting answers."
)
public class QuestionController {

    private final QuestionService questionService;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/allQuestion")
    @Operation(
            summary = "Get all questions",
            description = "Fetches a complete list of all questions available in the system."
    )
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
    }

    @Operation(
            summary = "Get questions by category",
            description = "Fetches all questions that belong to the specified category."
    )
    @GetMapping("/category/{cate}")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<List<QuestionDTO>> getByCategory(@PathVariable String cate) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getByCategory(cate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionService.getByCategory(cate), HttpStatus.BAD_REQUEST);
    }

    @Operation(
            summary = "Add a new question",
            description = "Creates and saves a new quiz question in the system."
    )
    @PostMapping("/addQuestion")
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(question));
    }

    @Operation(
            summary = "Add multiple questions",
            description = "Saves a list of new quiz questions into the system."
    )
    @PostMapping("/addAllQuestion")
    public ResponseEntity<List<QuestionDTO>> addAllQuestion(@RequestBody List<Question> question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addAllQuestion(question));
    }

    @Operation(
            summary = "Delete a question",
            description = "Deletes a question from the system using its ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeQuestionById(@PathVariable Integer id) {
        questionService.deleteQuestionById(id);
        try {
            return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The give information is not found");
    }

    @Operation(
            summary = "Submit an answer",
            description = "Submits the user's answer for evaluation and returns the result message."
    )
    @PostMapping("/submitAnswer")
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerSubmitionDTO answerSubmitionDTO){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.submitAnswer(answerSubmitionDTO));
    }

//    @PatchMapping("/update/{attribute})


    // Kafka api
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("question") String question) {
        kafkaProducer.sendQuestion(question);
        return ResponseEntity.ok("Question is send to the topic");
    }

}
