package com.vinay.OnlineQuizapp.entity;

import com.vinay.OnlineQuizapp.CustomAnnotation.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank(message = "Enrollment number is required")
    @Pattern(
            regexp = "^[0-9]{4}[A-Z]{2}[0-9]{6}$",
            message = "Please enter valid enrollment number !!"
    )
    private String enrollmentNo;
    @Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters !!")
    @NotBlank(message = "Name must not be blank !!")
    private String fullName;
    @NotNull(message = "Age is required !!")
    @Min(value = 16, message = "Age must be above 16 !!")
    private Integer age;
    @Email(message = "Please enter a valid email address !!")
    @Column(nullable = false)
    private String email;
    @Phone(message = "contact number is not valid !!")
    @NotBlank(message = "contact can't be blank")
    private String contactNumber;
    @NotBlank(message = "Please select the branch")
    private String branch;
    @NotBlank
//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[!@#()]).+$"
//            , message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (!@#()).")
    @Size(min = 8, message = "Password must be at least 8 characters long!!")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_roles",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

    private Boolean sentimentAnalysis;
    private Boolean isLogin = false;
    private Boolean isEnable = true;

}
