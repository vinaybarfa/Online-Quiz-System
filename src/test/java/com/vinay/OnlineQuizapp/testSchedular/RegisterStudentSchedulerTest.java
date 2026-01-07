package com.vinay.OnlineQuizapp.testSchedular;

import com.vinay.OnlineQuizapp.scheduler.StudentScheduler;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class RegisterStudentSchedulerTest {

    private final StudentScheduler studentScheduler;
    @Test
    public void fetchStudentAndSendSaMail() {
        studentScheduler.fetchStudentAndSendSaMail();
    }
}
