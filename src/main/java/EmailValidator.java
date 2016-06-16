import Logger.EmailLogger;
import Logger.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private String email;
    private Logger logger = new EmailLogger();

    public EmailValidator(String email) {
        this.email = email;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean isValid() {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this.email);
        boolean isValid = matcher.find();
        if(!isValid){
            logger.LogEmail(email);
        }
        return isValid;
    }

}
