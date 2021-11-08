package test;

import main.EmailValidator;
import main.PasswordChecker;
import main.PhoneValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ValidationTest {


    EmailValidator emailValidator = new EmailValidator();
    PasswordChecker passwordChecker = new PasswordChecker();
    PhoneValidator phoneValidator = new PhoneValidator();



    // ------------Password validation--------------
    @Test
    void testPasswordLengthValidation() {
        Assertions.assertTrue(passwordChecker.isPasswordLengthValid("Slaptazodziukas1."));
    }

    @Test
    void testUppercaseValidation() {
        Assertions.assertTrue(passwordChecker.uppercaseExist("Slaptazodziukas1."));
    }

    @Test
    void testSymbolValidation() {
        Assertions.assertTrue(passwordChecker.symbolExist("Slaptazodziukas1."));
    }

    // ------------Phone number validation--------------

    @Test
    void testNumbersValidation() {
        Assertions.assertTrue(phoneValidator.isNumbersOnly("86930345"));
    }

    @Test
    void testAdditionCountryPrefix() {
        Assertions.assertEquals("+3706930345",phoneValidator.addCountryPrefix("86930345"));
    }

    @Test
    void testCountryValid() {
        Assertions.assertTrue(phoneValidator.isValidForCountry("+370","86930345")); // checks for specific country requirements
    }

    // ------------Email validation--------------

    @Test
    void testEtaValidation() {
        Assertions.assertTrue(emailValidator.etaExist("aaaa@gmail.com"));
    }

    @Test
    void testEmailAllowedSymbolsValidation() {
        Assertions.assertTrue(emailValidator.symbolsValid("aaaa@gmail.com"));
    }

    @Test
    void testEmailDomainTldValidation() {
        Assertions.assertTrue(emailValidator.isValidDomainTld("aaaa@gmail.com"));
    }

}