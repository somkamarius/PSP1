package mif.projektavimas.lab3;

import main.EmailValidator;
import main.PasswordChecker;
import main.PhoneValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({EmailValidator.class, PasswordChecker.class, PhoneValidator.class})
public class Lab3Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }

}
