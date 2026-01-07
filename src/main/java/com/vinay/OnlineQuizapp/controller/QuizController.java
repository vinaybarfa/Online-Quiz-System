package com.vinay.OnlineQuizapp.controller;

import com.vinay.OnlineQuizapp.dto.QuestionDTO;
import com.vinay.OnlineQuizapp.entity.Response;
import com.vinay.OnlineQuizapp.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Tag(
        name = "Quiz APIs",
        description = "Provides APIs for creating quizzes, fetching quiz questions, and submitting quiz responses."
)
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new quiz",
            description = "Creates a quiz by selecting random questions from a specific category. "
                    + "You need to provide category name, number of questions, and quiz title."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Quiz created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or insufficient questions in category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get quiz questions",
            description = "Fetches all questions belonging to a specific quiz using the quiz ID. "
                    + "Correct answers are hidden for security."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quiz questions fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Quiz not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestion(@PathVariable Long id){
        return quizService.getQuizQuestion(id);
    }

    @Operation(
            summary = "Submit quiz answers",
            description = "Submits a list of user responses for a given quiz ID and returns the user's score."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quiz submitted successfully and score calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid answer format"),
            @ApiResponse(responseCode = "404", description = "Quiz not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }

}
