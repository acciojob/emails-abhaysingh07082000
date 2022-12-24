package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals("Accio@123"))
        {
            boolean validNewpass=isValidNewPassword(newPassword);

            if(validNewpass==true)
            {
                System.out.println(newPassword);
            }
        }
    }
    public boolean isValidNewPassword(String inputpassword)
    {
        int count_upperCase=0;
        int count_lowerCase=0;
        int count_digits=0;
        int count_specialCharacter=0;

        for(int i=0;i<inputpassword.length();i++)
        {
            char ch=inputpassword.charAt(i);

            if(isLower(ch))count_lowerCase++;
            else if(isUpper(ch))count_upperCase++;
            else if(isDigit(ch))count_digits++;
            else if(isSpecial(ch))count_specialCharacter++;

        }

        return (inputpassword.length()>=8 && count_lowerCase>=1 && count_upperCase>=1 && count_digits>=1 && count_specialCharacter>=1);
    }

    public static boolean isLower(char ch)
    {
        return (ch >= 'a' && ch <= 'z');
    }
    public static boolean isUpper(char ch)
    {
        return (ch >= 'A' && ch <= 'Z');
    }
    public static boolean isDigit(char ch)
    {
        return (ch >= '0' && ch <= '9');
    }
    public static boolean isSpecial(char ch)
    {
        return (ch=='@' || ch=='#' || ch=='&' || ch=='*' ||  ch=='$' || ch=='!' || ch=='%');
    }
}
