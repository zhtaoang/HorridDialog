import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {


    @Test
    public void ItValidatesALowerCaseEmail() {
        EmailValidator validator = new EmailValidator("valid@email.com");
        assertTrue(validator.isValid());
    }

    @Test
    public void InvalidEmails() {
        String[] badEmails = {
                "notValid @ssi.com",
                "notValid@ ssi.com",
                "notValid@ssi. com",
                "notValid@ssi .com",
                "@ssi.com",
                "notValid@.com",
                "notValid@com",
                "@",
                "ssi.com",
                "ssi",
                "."
        };

        for(String badEmail : badEmails) {
            EmailValidator validator = new EmailValidator(badEmail);
            assertFalse(validator.isValid());
        }
    }

}
