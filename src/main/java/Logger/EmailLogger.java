package Logger;

public class EmailLogger implements Logger {
    @Override
    public void log(String email) {
        System.out.println(email);
    }
}
