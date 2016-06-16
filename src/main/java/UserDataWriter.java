import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class UserDataWriter {
    private static String FILE_PATH = "./build/userdata.csv";

    public static void clear() {
        new File(FILE_PATH).delete();
        write("default");
    }

    public static String getStatus() {
        File file = new File(FILE_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Probably need regex here
        String status = scanner.nextLine();
        scanner.close();
        return status;
    }
    
    public static void write(String newStatus){

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_PATH, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(newStatus);
        writer.close();
    }
}