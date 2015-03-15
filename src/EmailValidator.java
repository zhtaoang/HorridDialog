import Logger.*;
import java.util.HashMap;

public class EmailValidator {
    public static final String ValidEmailRegularExpression = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.([A-Z]{2,4}|MUSEUM)$";
    private String email;
    private HashMap<String, String> blacklist;
    private Logger logger;

    public EmailValidator(String email) {
        this.blacklist = new HashMap<String, String>();
        this.email = email;
        this.logger = new NullLogger();
    }

    public void setBlacklist(String[] blacklist) {
        this.blacklist = new HashMap<String, String>();
        for (String invalidEmail : blacklist) {
            this.blacklist.put(invalidEmail.toUpperCase(), invalidEmail);
        }
    }

    public boolean isValid() {
        if (emailIsInBlacklist()) {
            logger.LogEmail(email);
            return false;
        }

        return emailFormatIsValid();
    }

    private boolean emailFormatIsValid() {
        return email.toUpperCase().matches(ValidEmailRegularExpression);
    }

    private boolean emailIsInBlacklist() {
        return blacklist.containsKey(email.toUpperCase());
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
