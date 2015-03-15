import Logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class EmailValidatorTester implements Logger {
    private String[] blacklist;
    private List<String> logEntries;

    @Override
    public void LogEmail(String email) {
        logEntries.add(email);
    }

    public EmailValidatorTester() {
        this.blacklist = new String[] {};
        this.resetLogEntries();
    }

    public void testValidEmail(String email) {
        EmailValidator validator = new EmailValidator(email);
        validator.setBlacklist(blacklist);

        assert (validator.isValid());
    }

    public void testInvalidEmail(String email) {
        EmailValidator validator = new EmailValidator(email);
        validator.setBlacklist(blacklist);
        validator.setLogger(this);

        assert(!validator.isValid());
    }

    public void resetLogEntries() {
        this.logEntries = new ArrayList<String>();
    }

    public static void main(String [] args) {
        EmailValidatorTester tester = new EmailValidatorTester();
        tester.testInvalidEmail("");
        tester.testValidEmail("paytonrules@paytonrules.com");
        tester.testValidEmail("paytonrules@museum.com");

        tester.blacklist = new String[]{"paytonrules@paytonrules.com"};
        tester.testInvalidEmail("paytonrules@paytonrules.com");
        assert(tester.logEntries.contains("paytonrules@paytonrules.com"));

        EmailValidator validator = new EmailValidator("paytonrules@paytonrules.com");
        validator.setBlacklist(new String[] {});
        validator.setLogger(tester);
        tester.resetLogEntries();
        assert(validator.isValid());
        assert(tester.logEntries.isEmpty());

        System.out.println("Success");
    }
}
