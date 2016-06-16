package Logger;

/**
 * Created by tao.zhang on 6/16/16.
 */
public class ErrorLog implements Logger {
    @Override
    public void log(String err) {
        System.err.println(err);
    }

}