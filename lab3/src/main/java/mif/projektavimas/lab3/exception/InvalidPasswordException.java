package mif.projektavimas.lab3.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String password) {
        super("This password is not valid! " + password);
    }
}
