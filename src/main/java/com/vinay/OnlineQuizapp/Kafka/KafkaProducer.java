package com.vinay.OnlineQuizapp.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendQuestion(String question) {
        log.info(String.format("Question send %s",question));
        kafkaTemplate.send("quizTopic",question);
    }
}
