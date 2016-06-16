import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {


    @Test
    public void ItValidatesALowerCaseEmail() {
        assertTrue(EmailValidator.isValid("valid@email.com"));
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
            assertFalse(EmailValidator.isValid(badEmail));
        }
    }

}
