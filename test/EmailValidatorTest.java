import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    @Test
    public void ItValidatesAStandardEmail() {
        EmailValidator validator = new EmailValidator("VALID@EMAIL.COM");

        assertTrue(validator.isValid());
    }

    @Test
    public void ItValidatesALowerCaseEmail() {
        EmailValidator validator = new EmailValidator("valid@email.com");

        assertTrue(validator.isValid());
    }

    @Test public void ItValidatesMuseum() {
        EmailValidator validator = new EmailValidator("valid@email.museum");

        assertTrue(validator.isValid());
    }

    @Test
    public void ItDoesntValidateInvalidEmails() {
        String[] badEmails = {
                "please @gmail.com",
                "invalid@invalid",
                "invalid@invalid.thisisnotavaliddomain",
                ";;;",
                "noatsign",
                "no.com"
        };

        for(String badEmail : badEmails) {
            EmailValidator validator = new EmailValidator(badEmail);
            assertFalse(validator.isValid());
        }
    }

    @Test
    public void ItDoesntValidateEmailsInTheBlacklist() {
        EmailValidator validator = new EmailValidator("please@gmail.com");
        validator.setBlacklist(new String[] {"PLEASE@GMAIL.COM"});

        assertFalse(validator.isValid());
    }
}
