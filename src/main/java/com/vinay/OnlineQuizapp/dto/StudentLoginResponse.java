package com.vinay.OnlineQuizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentLoginResponse {

    private String jwtToken;
    private Long studentId;
}
