package service;

public class EmailValidator {

    public static String SPECIAL_SYMBOLS = "!@#$%^&*().";

    public boolean validateEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        return hasEtaSymbol(email) && usingAllowedSpecialCharacters(email) && localPartValid(email) && domainValid(email);
    }

    private boolean hasEtaSymbol(String email) {
        long count = email.chars().filter(cchar -> cchar == '@').count();
        int indexOf = email.indexOf("@");
        return count <= 1 && indexOf != -1;
    }

    private boolean usingAllowedSpecialCharacters(String email) {
        boolean onlyAllowed;
        for (char symbol : email.toCharArray()) {
            onlyAllowed = Character.isLetterOrDigit(symbol) || SPECIAL_SYMBOLS.contains("" + symbol);
            if (!onlyAllowed) {
                return false;
            }
        }
        return true;
    }

    private boolean domainValid(String email) {
        int etaSymbolPlace = email.indexOf("@");
        String domainName = email.substring(etaSymbolPlace + 1);
        if (domainName.endsWith(".")) {
            return false;
        }
        String[] domainNameSplits = domainName.split("\\.");
        if (domainNameSplits.length == 0) {
            return false;
        }

        for (String splitDomain : domainNameSplits) {
            if (splitDomain.isEmpty()) {
                return false;
            }
            if (splitDomain.length() < 2 || splitDomain.length() > 63) {
                return false;
            }
        }
        return true;
    }

    private boolean localPartValid(String email) {
        int etaSymbolPlace = email.indexOf("@");
        String localPart = email.substring(0, etaSymbolPlace);
        if (!Character.isLetterOrDigit(localPart.charAt(0)) || localPart.length() > 64) {
            return false;
        }
        return true;
    }
}
