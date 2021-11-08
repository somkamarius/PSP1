package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhoneValidator {

    private static final Map<String, String> countryPrefixMap = new HashMap<>();

    static {
        countryPrefixMap.put("LT", "+370");
        countryPrefixMap.put("LV", "+371");
        countryPrefixMap.put("RO", "+40");
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        if (phoneNumber.charAt(0) != '+' && phoneNumber.charAt(0) == '8') {
            phoneNumber = "+370" + phoneNumber.substring(1);
        }

        return numberOnlyWithDigits(phoneNumber) && validLength(phoneNumber);
    }

    private boolean validLength(String phoneNumber) {
        String phoneNoPrefix = phoneNumber.substring(phoneNumber.length() - 8);
        return phoneNoPrefix.length() <= 8 && phoneNumber.length() >10;
    }

    private boolean numberOnlyWithDigits(String number) {
        if (number.charAt(0) == '+') {
            number = number.substring(1);
        }
        for (char symbol : number.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }

    public boolean validatePhoneNumber(String phoneNumber, int minLength) {
        String prefix = phoneNumber.substring(0, phoneNumber.length() - minLength);
        return countryPrefixMap.containsValue(prefix);
    }

    public boolean validateOtherCountryPhoneNumber(String countryCode, String number, int length) {
        String prefixFromMap = countryPrefixMap.get(countryCode);
        String prefixFromNumber = number.substring(0, number.length() - length);

        return Objects.equals(prefixFromNumber, prefixFromMap);
    }

    public boolean validateOtherCountryPhoneNumber(String countryCode, String number) {
        String prefixFromMap = countryPrefixMap.get(countryCode);
        String prefixFromNumber = number.substring(0, number.length() - 8);

        return Objects.equals(prefixFromNumber, prefixFromMap);
    }

    public boolean validateOtherCountryPhoneNumber(String number) {
        String prefix = number.substring(0, number.length() - 8);
        return countryPrefixMap.containsValue(prefix);
    }
}
