package mif.projektavimas.lab3.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("This email address is not valid! " + email);
    }
}
