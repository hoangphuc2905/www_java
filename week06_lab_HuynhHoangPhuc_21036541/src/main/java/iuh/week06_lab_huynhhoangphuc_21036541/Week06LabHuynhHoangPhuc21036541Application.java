package iuh.week06_lab_huynhhoangphuc_21036541;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Week06LabHuynhHoangPhuc21036541Application {

    public static void main(String[] args) {
        SpringApplication.run(Week06LabHuynhHoangPhuc21036541Application.class, args);
    }

}
