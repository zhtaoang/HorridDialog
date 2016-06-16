import Logger.EmailLogger;
import Logger.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static String email;
    private static Logger logger = new EmailLogger();

    public EmailValidator(String email) {
        this.email = email;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.length()>0 ? email :EmailValidator.email);
        boolean isValid = matcher.find();
        if(!isValid){
            logger.log(EmailValidator.email);
        }
        return isValid;
    }

}
