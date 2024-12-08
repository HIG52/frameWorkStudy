package org.example.frameworkstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FrameWorkStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameWorkStudyApplication.class, args);
    }

}
