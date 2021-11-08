package service;

public class PasswordChecker {

    public static String SPECIAL_SYMBOLS = "!@#$%^&*().";

    public boolean validatePassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        return usingSpecialCharacters(password) && hasDigit(password) && hasUppercaseLetters(password) && validLength(password);
    }

    private boolean validLength(String password) {
        return password.length() >= 7;
    }

    private boolean hasUppercaseLetters(String password) {
        return !password.toLowerCase().equals(password);
    }

    private boolean usingSpecialCharacters(String password) {
        boolean onlyAllowed;
        boolean specialSymbol = false;
        for (char symbol : password.toCharArray()) {
            if (SPECIAL_SYMBOLS.contains("" + symbol) && !specialSymbol) {
                specialSymbol = true;
            }
            onlyAllowed = Character.isLetterOrDigit(symbol) || SPECIAL_SYMBOLS.contains("" + symbol);
            if (!onlyAllowed) {
                return false;
            }
        }
        return specialSymbol;
    }

    private boolean hasDigit(String password) {
        for (char symbol : password.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    public boolean validatePassword(int length, String password) {
        return password.length() < length;
    }
}
