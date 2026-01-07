package com.vinay.OnlineQuizapp.scheduler;

import com.vinay.OnlineQuizapp.entity.Student;
import com.vinay.OnlineQuizapp.repository.StudentRepository;
import com.vinay.OnlineQuizapp.service.EmailService;
import com.vinay.OnlineQuizapp.service.SentimentAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentScheduler {

    private final StudentRepository studentRepository;
    private final EmailService emailService;
    private final SentimentAnalysisService sentimentAnalysisService;

//    @Scheduled(cron = "* 0 0 ? * WED")
    public void fetchStudentAndSendSaMail() {

        List<Student> students = studentRepository.findBySentimentAnalysis(false);

        for (Student student : students) {

            String message =
                    "Hello Sir/Madam,\n\n" +
                            "I hope you are doing well. I am writing this email regarding the GD round conducted yesterday. " +
                            "I wanted to know the status and further steps of the selection process.\n\n" +
                            "Kindly let me know if any additional information or documents are required from my side.\n\n" +
                            "Thank you for your time and consideration.\n\n" +
                            "Regards,\n" +
                            student.getFullName();

            emailService.sendEmail(
                    student.getEmail(),
                    "Request for Update on GD Round Status",
                    message
            );

            student.setSentimentAnalysis(true);
            studentRepository.save(student);
        }
    }

}
