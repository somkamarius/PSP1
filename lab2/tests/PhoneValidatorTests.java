import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import service.PhoneValidator;

class PhoneValidatorTests {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();

    }

    @Test
    void testIfPhoneNumberIsCorrect_shouldReturnTrue() {
        boolean isValid = phoneValidator.validatePhoneNumber("+37065134987");
        assertTrue(isValid);
    }

    @Test
    void testIfPhoneNumberWithoutPlusIsCorrect_shouldReturnTrue() {
        boolean isValid = phoneValidator.validatePhoneNumber("865134987");
        assertTrue(isValid);
    }

    @Test
    void testIfOtherCountryPhoneNumberIsCorrect_shouldReturnTrue() {
        boolean isValid = phoneValidator.validateOtherCountryPhoneNumber("+37165134987");
        assertTrue(isValid);
    }

    @Test
    void testIfPhoneNumberIsNotEmpty_shouldReturnFalse() {
        boolean isValid = phoneValidator.validatePhoneNumber("");
        assertFalse(isValid);
    }

    @Test
    void testIfPhoneNumberIsNotNull_shouldReturnFalse() {
        boolean isValid = phoneValidator.validatePhoneNumber(null);
        assertFalse(isValid);
    }

    @Test
    void testIfPhoneNumberContainsOnlyNumbers_shouldReturnFalse() {
        boolean isValid = phoneValidator.validatePhoneNumber("+3706423479B");
        assertFalse(isValid);
    }

    @Test
    void testIfPhoneNumberIsTooLong_shouldReturnFalse() {
        int minLength = 8;
        boolean isValid = phoneValidator.validatePhoneNumber("+3706884394242", minLength);
        assertFalse(isValid);
    }

    @Test
    void testIfPhoneNumberIsTooShort_shouldReturnFalse() {
        boolean isValid = phoneValidator.validatePhoneNumber("+37064242");
        assertFalse(isValid);
    }

    @Test
    void testIfPhoneNumberCountryIsCorrect_shouldReturnFalse() {
        String country = "LT";
        boolean isValid = phoneValidator.validateOtherCountryPhoneNumber(country, "+37168843942");
        assertFalse(isValid);
    }

    @Test
    void testIfOtherCountryPhoneNumberContainsOnlyNumbers_shouldReturnFalse() {
        String country = "RO";
        boolean isValid = phoneValidator.validateOtherCountryPhoneNumber(country, "+402134793427B");
        assertFalse(isValid);
    }

    @Test
    void testIfOtherCountryPhoneNumberIsTooLong_shouldReturnFalse() {
        String country = "LV";
        int minLength = 8;
        boolean isValid = phoneValidator.validateOtherCountryPhoneNumber(country, "+371688439234242", minLength);
        assertFalse(isValid);
    }

    @Test
    void testIfOtherCountryPhoneNumberIsTooShort_shouldReturnFalse() {
        String country = "LV";
        boolean isValid = phoneValidator.validateOtherCountryPhoneNumber(country, "+37164242");
        assertFalse(isValid);
    }

}