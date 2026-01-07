package com.vinay.OnlineQuizapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

//    @Bean   // new Topic is created
    public NewTopic quizTopic() {
        return TopicBuilder.name("quizTopic")
                .build();
    }
}
