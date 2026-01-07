package com.vinay.OnlineQuizapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginStudentDTO {
    @NotBlank(message = "Username is required!!")
    private String email;
    @NotBlank(message = "Password is required!!")
//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "Please Enter correct password!!"
//    )
    private String password;
}
