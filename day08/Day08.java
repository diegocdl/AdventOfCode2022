import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day08 {

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

    public static boolean visibilityValidation(int i, int j, int[][] heightTreeGrid) {
        // Check if it's an edge
        if (i == 0 || i == heightTreeGrid.length - 1 || j == 0 || j == heightTreeGrid[i].length - 1) {
            return true;
        }

        boolean leftVisible = true, rightVisible = true;
        boolean topVisible = true, bottomVisible = true;

        // top
        for (int k = i - 1; k >= 0; k--) {
            if (heightTreeGrid[i][j] <= heightTreeGrid[k][j]) {
                topVisible = false;
            }
        }

        // bottom
        for (int k = i + 1; k < heightTreeGrid.length; k++) {
            if (heightTreeGrid[i][j] <= heightTreeGrid[k][j]) {
                bottomVisible = false;
            }
        }

        // left
        for (int k = j - 1; k >= 0; k--) {
            if (heightTreeGrid[i][j] <= heightTreeGrid[i][k]) {
                leftVisible = false;
            }
        }

        // rigth
        for (int k = j + 1; k < heightTreeGrid.length; k++) {
            if (heightTreeGrid[i][j] <= heightTreeGrid[i][k]) {
                rightVisible = false;
            }
        }

        return topVisible || bottomVisible || leftVisible || rightVisible;
    }

    public static Boolean[][] buildVisibilityTreeGrid(int[][] heightTreeGrid) {
        Boolean[][] visibilityTreeGrid = new Boolean[heightTreeGrid.length][heightTreeGrid[0].length];
        for (int i = 0; i < heightTreeGrid.length; i++) {
            for (int j = 0; j < heightTreeGrid[i].length; j++) {
                visibilityTreeGrid[i][j] = visibilityValidation(i, j, heightTreeGrid);
            }
        }
        return visibilityTreeGrid;
    }

    public static void main(String[] args) throws Exception {
        String sample = (args.length > 0) ? args[0] : "";
        String filename = "input" + sample + ".txt";
        int[][] heightTreeGrid;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            heightTreeGrid = buildHeightTreeGrid(br.lines().toList());
            Boolean[][] visibilityTreeGrid = buildVisibilityTreeGrid(heightTreeGrid);

            Stream<Boolean> s = Stream.of(visibilityTreeGrid).flatMap(Arrays::stream);
            long result = s.filter(x -> x).count();
            System.out.println(result);
        }
    }
}
