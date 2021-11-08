package mif.projektavimas.lab3.controller;

import main.EmailValidator;
import main.PasswordChecker;
import main.PhoneValidator;
import mif.projektavimas.lab3.exception.InvalidEmailException;
import mif.projektavimas.lab3.exception.InvalidPasswordException;
import mif.projektavimas.lab3.exception.InvalidPhoneNumberException;
import mif.projektavimas.lab3.exception.UserNotFoundException;
import mif.projektavimas.lab3.model.User;
import mif.projektavimas.lab3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private UserRepository userRepository;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private PasswordChecker passwordChecker;
    @Autowired
    private PhoneValidator phoneValidator;

    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user")
    User addNewUser(@RequestBody User newUser) {
        if (!validateUserInfo(newUser)) {
            throw new IllegalStateException();
        }

        return userRepository.save(newUser);
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/user/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        if (!validateUserInfo(newUser)) {
            throw new IllegalStateException();
        }

        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmailAddress(newUser.getEmailAddress());
                    user.setHomeAddress(newUser.getHomeAddress());
                    user.setLastName(newUser.getLastName());
                    user.setPassword(newUser.getPassword());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    return userRepository.save(user);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    private boolean validateUserInfo(User user) {
        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        String emailAddress = user.getEmailAddress();

        boolean invalidPhoneNumber = phoneValidator.isNumbersOnly(phoneNumber) && phoneValidator.isValidForCountry(phoneNumber.substring(0, phoneNumber.length() - 8), phoneNumber);
        boolean invalidPassword = passwordChecker.isPasswordLengthValid(password) && passwordChecker.symbolExist(password) && passwordChecker.uppercaseExist(password);
        boolean invalidEmailAddress = emailValidator.etaExist(emailAddress) && emailValidator.isValidDomainTld(emailAddress) && emailValidator.symbolsValid(emailAddress);

        if (!invalidEmailAddress) {
            throw new InvalidEmailException(emailAddress);
        }

        if (!invalidPassword) {
            throw new InvalidPasswordException(password);
        }

        if (!invalidPhoneNumber) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }

        return true;
    }


}
