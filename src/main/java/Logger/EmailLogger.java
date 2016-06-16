package Logger;

public class EmailLogger implements Logger {
    @Override
    public void LogEmail(String email) {
        System.out.println(email);
    }
}
