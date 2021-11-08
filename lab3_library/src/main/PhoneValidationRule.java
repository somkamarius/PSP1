package main;

public class PhoneValidationRule
{
    private String prefix;
    private int length;

    public PhoneValidationRule(String prefix, int length)
    {
        this.prefix=prefix;
        this.length=length;
    }

    public String getPrefix()
    {
        return this.prefix;
    }

    public int getLength()
    {
        return this.length;
    }
}
