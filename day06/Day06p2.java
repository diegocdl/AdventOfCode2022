import java.io.*;

public class Day06p2 {
    public static void main(String[] args) throws Exception {
        String sample = (args.length > 0) ? args[0] : "";
        String filename = "input" + sample + ".txt";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            String code = br.lines().reduce("", (s1, s2) -> s1 + s2);

            for (int i = 13; i < code.length(); i++) {
                long count = code.substring(i - 13, i + 1).chars()
                        .distinct()
                        .count();

                if (count == 14) {
                    System.out.println("Result: " + (i + 1));
                    break;
                }
            }
        }
    }
}