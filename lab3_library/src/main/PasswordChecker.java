package main;

public class PasswordChecker
{
    private static String specialSymbols = "!”#$%^&*()_+.,/";

    public boolean isPasswordLengthValid(String password)
    {
        if(password.length() >= 8)
        {
            return true;
        }
        else
            return false;
    }

    public boolean uppercaseExist(String password)
    {
        for (int i = 0; i< password.length(); i++)
        {
            char symbol = password.charAt(i);
            if(Character.isUpperCase(symbol))
            {
                return true;
            }
        }
        return false;
    }

    public boolean symbolExist(String password)
    {
        for (int i = 0; i<specialSymbols.length(); i++)
        {
            for (int z = 0; z<password.length(); z++)
            {
                if(specialSymbols.charAt(i) == password.charAt(z))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
