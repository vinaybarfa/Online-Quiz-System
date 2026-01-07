package com.vinay.OnlineQuizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentimentAnalysisService {

//    private final RegisterStudent registerStudent;

    public String getResult(String text) {
        if (text == null || text.isEmpty()) {
            return "neutral";
        }

        String lower = text.toLowerCase();
        String[] positiveWords = {"good", "great", "nice", "happy", "excellent", "well done", "awesome"};
        String[] negativeWords = {"sad", "bad", "worried", "upset", "stress", "angry", "depressed"};

        int score = 0;
        for (String word : positiveWords) {
            if (lower.contains(word)) {
                score++;
            }
        }
        for (String word : negativeWords) {
            if (lower.contains(word)) {
                score--;
            }
        }

        if (score > 0) {
            return "positive";
        } else if (score < 0) {
            return "negative";
        } else {
            return "neutral";
        }

    }

    public String getSentiment(String email) {
        return "Thank you for your participation. Your feedback is valuable to us.";
    }
}
