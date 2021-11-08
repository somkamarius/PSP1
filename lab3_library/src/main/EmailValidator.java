package main;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class EmailValidator
{
    private static String validCharsEmailPart = "!#$%&'*+-/=?^_`{|}~.";
    private static String validCharsDomainPart = ".-";


    public boolean etaExist(String email)
    {
        for (int i = 0; i< email.length(); i++)
        {
            char symbol = email.charAt(i);
            if(symbol == '@')
            {
                return true;
            }
        }
        return false;
    }

    public boolean symbolsValid(String email)
    {
        return getEmailPart(email).chars().allMatch(a -> Character.isLetterOrDigit(a) || validCharsEmailPart.indexOf(a) != -1)
                && getDomainPart(email).chars().allMatch(a -> Character.isLetterOrDigit(a) || validCharsDomainPart.indexOf(a) != -1);
    }

    public boolean isValidDomainTld(String email)
    {
        String domain = getDomainPart(email);
        return domain.contains(".") && (domain.length() - domain.indexOf('.')) > 2;
    }

    public String getEmailPart(String email)
    {
        return email.substring(0, email.indexOf('@')); //Get email part
    }

    public String getDomainPart(String email)
    {
        return email.substring(email.indexOf('@')+1); //Get email part
    }

}
