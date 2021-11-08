package mif.projektavimas.lab3.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String phoneNumber) {
        super("This phone Number is not valid! " + phoneNumber);
    }
}
