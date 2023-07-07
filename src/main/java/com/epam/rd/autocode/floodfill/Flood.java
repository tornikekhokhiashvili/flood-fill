package com.epam.rd.autocode.floodfill;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Flood implements FloodFill {
    private static final Flood instance = new Flood();

    private Flood() {
    }

    public static FloodFill getInstance() {
        return instance;
    }

    @Override
    public void flood(String map, FloodLogger logger) {
        char[][] grid = parseMap(map);
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == WATER) {
                    floodFill(grid, visited, i, j);
                }
            }
        }

        logger.log(getFloodState(grid));
    }

    private void floodFill(char[][] grid, boolean[][] visited, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check if the start cell is within bounds and is water
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols || grid[startRow][startCol] != WATER) {
            return;
        }

        // Create a queue for iterative flood fill
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            // Skip if out of bounds or cell is not water or already visited
            if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != WATER || visited[row][col]) {
                continue;
            }

            // Mark as visited and replace the cell with a space character
            visited[row][col] = true;
            grid[row][col] = ' ';

            // Add the neighboring cells to the queue
            queue.offer(new int[]{row - 1, col}); // up
            queue.offer(new int[]{row + 1, col}); // down
            queue.offer(new int[]{row, col - 1}); // left
            queue.offer(new int[]{row, col + 1}); // right
        }
    }

    private char[][] parseMap(String map) {
        String[] rows = map.split("\n");
        int rowCount = rows.length;
        int colCount = Arrays.stream(rows).mapToInt(String::length).max().orElse(0);

        char[][] grid = new char[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            char[] chars = rows[i].toCharArray();
            Arrays.fill(grid[i], ' ');
            System.arraycopy(chars, 0, grid[i], 0, chars.length);
        }

        return grid;
    }

    private String getFloodState(char[][] grid) {
        StringBuilder sb = new StringBuilder();

        for (char[] row : grid) {
            for (char cell : row) {
                sb.append(cell);
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}
