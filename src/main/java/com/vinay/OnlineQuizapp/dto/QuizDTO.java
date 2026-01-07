package com.vinay.OnlineQuizapp.dto;

import com.vinay.OnlineQuizapp.entity.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {
    private String title;
    private List<Question> question;
}