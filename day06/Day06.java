import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day06 {
    public static void main(String[] args) throws Exception {
        String sample = (args.length > 0) ? args[0] : "";
        String filename = "input" + sample + ".txt";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            String l = br.lines().reduce("", (s1, s2) -> s1 + s2);

            for (int i = 3; i < l.length(); i++) {
                List<Character> list = Stream.of(l.charAt(i - 3), l.charAt(i - 2), l.charAt(i - 1), l.charAt(i))
                        .distinct()
                        .toList();
                int x = list.size();

                if (x == 4) {
                    System.out.println("Result: " + (i + 1));
                    break;
                }
            }
        }
    }
}