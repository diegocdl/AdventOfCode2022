import java.io.*;

public class Day00 {
    public static void main(String[] args) {
        String sample = (args.length > 0) ? args[0] : "";
        String filename = "input" + sample + ".txt";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
        }
    }
}
