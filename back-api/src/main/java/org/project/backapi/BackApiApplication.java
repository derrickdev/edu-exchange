package org.project.backapi;

import org.project.backapi.dto.request.RegisterRequest;
import org.project.backapi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.project.backapi.enums.UserRole.*;

@SpringBootApplication
@ComponentScan(basePackages = "org.project.backapi")
public class BackApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthService service) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .fullname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(String.valueOf(ADMIN))
                    .pseudo("super-user")
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());

            var teacher = RegisterRequest.builder()
                    .fullname("Teacher")
                    .email("teacher@mail.com")
                    .password("password")
                    .role(String.valueOf(TEACHER))
                    .teacherSpeciality("Mathematics")
                    .pseudo("first-teacher")
                    .build();
            System.out.println("Teacher token: " + service.register(teacher).getToken());

            var student = RegisterRequest.builder()
                    .fullname("Student")
                    .email("student@mail.com")
                    .password("password")
                    .role(String.valueOf(STUDENT))
                    .pseudo("first-student")
                    .build();
            System.out.println("Student token: " + service.register(student).getToken());
        };
    }
}
