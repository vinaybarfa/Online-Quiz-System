package com.vinay.OnlineQuizapp.testService;

import com.vinay.OnlineQuizapp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendEmail("vinaybarfa9@gmail.com","Hello Good Morning Vinay Barfa"," this mail is just for testing purpose");
    }
}
