package com.vinay.OnlineQuizapp.controller;

import com.vinay.OnlineQuizapp.dto.LoginStudentDTO;
import com.vinay.OnlineQuizapp.dto.StudentDTO;
import com.vinay.OnlineQuizapp.entity.Student;
import com.vinay.OnlineQuizapp.service.LogoutStudentService;
import com.vinay.OnlineQuizapp.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Tag(name = "Register Student APIs", description = "Provides APIs for student registration, login, logout, and account management.")
public class StudentController {

    private final StudentService studentService;
    private final LogoutStudentService logoutStudentService;

    @Operation(
            summary = "Register a new student",
            description = "Creates a new student account by saving enrollment number, name, age, email, contact, branch, and password."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student registered successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed or invalid student data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.registerStudent(studentDTO));
    }

    @Operation(
            summary = "Get all students",
            description = "Fetches a list of all students who have registered in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fetched all students successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allStudent")
    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudent());
    }

    @Operation(
            summary = "Delete a student",
            description = "Deletes a student from the system using the student's ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Login student",
            description = "Authenticates a student using email and password, and marks login status as active."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid login credentials"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/loginStudent")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<String> loginStudent(@Valid @RequestBody LoginStudentDTO loginStudentDTO){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.loginStudent(loginStudentDTO));
    }

    @Operation(
            summary = "Logout student",
            description = "Logs out a student by updating the login status to false using student ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student logged out successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found or not logged in"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/logout/{studentId}")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<String> logoutStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(logoutStudentService.logoutStudentAccount(studentId));
    }
}
