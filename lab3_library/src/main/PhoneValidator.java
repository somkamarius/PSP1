package main;

import java.util.ArrayList;
import java.util.List;

public class PhoneValidator
{
    private List<PhoneValidationRule> phoneRules = new ArrayList<PhoneValidationRule>()
    {
        {
            add(new PhoneValidationRule("+370", 8));
        }
    };

    public boolean isNumbersOnly(String phoneNumber)
    {
        int index = 0;
        if(phoneNumber.charAt(0) == '+')
        {
            index = 1;
        }
        else
            index = 0;
        for (int i = index ; i<phoneNumber.length(); i++)
        {
            char temp = phoneNumber.charAt(i);
            if(!Character.isDigit(temp))
            {
                return false;
            }
        }
        return true;
    }

    public String addCountryPrefix(String phoneNumber)
    {
        if(phoneNumber.charAt(0) == '8')
        {
            return "+370" + phoneNumber.substring(1);
        }
        return phoneNumber;
    }

    public boolean isValidForCountry(String countryCode, String phoneNumber)
    {
        for (PhoneValidationRule val : phoneRules)
        {
            if (val.getPrefix() == countryCode)
            {
                if(phoneNumber.length() == val.getLength())
                {
                    return true;
                }
            }
        }
        return false;
    }
}
