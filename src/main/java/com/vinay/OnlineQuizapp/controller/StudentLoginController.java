package com.vinay.OnlineQuizapp.controller;

import com.vinay.OnlineQuizapp.dto.StudentLoginRequest;
import com.vinay.OnlineQuizapp.dto.StudentLoginResponse;
import com.vinay.OnlineQuizapp.service.LoginStudentService;
import com.vinay.OnlineQuizapp.service.LogoutStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Tag(name = "Student Login APIs",description = "Allows a registered user to securely log in using their username and password.")
public class StudentLoginController {

    private final LoginStudentService loginStudentService;
    private final LogoutStudentService logoutStudentService;

    @Operation(
            summary = "Student Login",
            description = "Authenticates a student using email and password. If the credentials are valid, the student is logged in and the login status is updated."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<StudentLoginResponse> createLogin(StudentLoginRequest studentLoginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(loginStudentService.login(studentLoginRequest));
    }

}
