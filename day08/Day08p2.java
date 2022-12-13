import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day08p2 {

    public static int[][] buildHeightTreeGrid(List<String> fileLines) {
        int gridSize = fileLines.get(0).length();
        int[][] treeGrid = new int[fileLines.size()][gridSize];

        for (int i = 0; i < fileLines.size(); i++) {
            for (int j = 0; j < fileLines.get(i).length(); j++) {
                treeGrid[i][j] = Integer.parseInt(fileLines.get(i).substring(j, j + 1));
            }
        }
        return treeGrid;
    }

    public static Long calculateScenicScore(int i, int j, int[][] heightTreeGrid) {
        long score = 1;

        long count = 0;
        // top
        for (int k = i - 1; k >= 0; k--) {
            count++;
            if (heightTreeGrid[i][j] <= heightTreeGrid[k][j]) {
                break;
            } else {
            }
        }
        score *= count;
        count = 0;

        // bottom
        for (int k = i + 1; k < heightTreeGrid.length; k++) {
            count++;
            if (heightTreeGrid[i][j] <= heightTreeGrid[k][j]) {
                break;
            } else {
            }
        }
        score *= count;
        count = 0;

        // left
        for (int k = j - 1; k >= 0; k--) {
            count++;
            if (heightTreeGrid[i][j] <= heightTreeGrid[i][k]) {
                break;
            } else {
            }
        }
        score *= count;
        count = 0;

        // rigth
        for (int k = j + 1; k < heightTreeGrid.length; k++) {
            count++;
            if (heightTreeGrid[i][j] <= heightTreeGrid[i][k]) {
                break;
            } else {
            }
        }
        score *= count;

        return score;
    }

    public static Long[][] buildVisibilityTreeGrid(int[][] heightTreeGrid) {
        Long[][] scenicScores = new Long[heightTreeGrid.length][heightTreeGrid[0].length];
        for (int i = 0; i < heightTreeGrid.length; i++) {
            for (int j = 0; j < heightTreeGrid[i].length; j++) {
                scenicScores[i][j] = calculateScenicScore(i, j, heightTreeGrid);
            }
        }
        return scenicScores;
    }

    public static void main(String[] args) throws Exception {
        String sample = (args.length > 0) ? args[0] : "";
        String filename = "input" + sample + ".txt";
        int[][] heightTreeGrid;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            heightTreeGrid = buildHeightTreeGrid(br.lines().toList());
            Long[][] scenicScoresGrid = buildVisibilityTreeGrid(heightTreeGrid);

            if (!sample.isEmpty()) {
                Arrays.stream(heightTreeGrid).map(Arrays::toString).forEach(System.out::println);
                System.out.println();
                Arrays.stream(scenicScoresGrid).map(Arrays::toString).forEach(System.out::println);
                System.out.println();
            }
            Stream<Long> s = Stream.of(scenicScoresGrid).flatMap(Arrays::stream);
            long result = s.max((x, y) -> (int) (x - y)).orElseThrow();
            System.out.println(result);
        }
    }
}
