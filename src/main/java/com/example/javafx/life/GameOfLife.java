package com.example.javafx.life;

import java.util.Arrays;
import java.util.Random;

public class GameOfLife {
    private boolean[][] grid;
    private final int rows;
    private final int cols;
    private int birthThreshold;
    private int survivalThresholdMin;
    private int survivalThresholdMax;
    private boolean wrappingEdges;

    public GameOfLife(int rows, int cols, int birthThreshold, int survivalThresholdMin, int survivalThresholdMax, boolean wrappingEdges) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.birthThreshold = birthThreshold;
        this.survivalThresholdMin = survivalThresholdMin;
        this.survivalThresholdMax = survivalThresholdMax;
        this.wrappingEdges = wrappingEdges;
    }

    public void init() {
        for(int i = 0; i < rows; i++) {
            Arrays.fill(grid[i], false);
        }
    }

    public void setCell(int row, int col, boolean state) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = state;
        }
    }

    public void toggleCell(int row, int col){ grid[row][col] = !grid[row][col]; }

    public void toggleWrapping() { wrappingEdges = !wrappingEdges; }

    public boolean[][] getGrid() {
        return grid;
    }

    public void nextGeneration() {
        boolean[][] newGrid = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col);
                if (grid[row][col]) {
                    newGrid[row][col] = liveNeighbors >= survivalThresholdMin && liveNeighbors <= survivalThresholdMax;
                } else {
                    newGrid[row][col] = liveNeighbors == birthThreshold;
                }
            }
        }
        grid = newGrid;
    }

    private int countLiveNeighbors(int row, int col) {
        int liveCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int newRow = wrappingEdges ? (row + i + rows) % rows : row + i;
                int newCol = wrappingEdges ? (col + j + cols) % cols : col + j;

                if (!wrappingEdges && (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols)) {
                    continue;
                }

                if (grid[newRow][newCol]) {
                    liveCount++;
                }
            }
        }
        return liveCount;
    }

    public void setBirthThreshold(int birthThreshold) {
        this.birthThreshold = birthThreshold;
    }

    public void setSurvivalThresholdMin(int survivalThresholdMin) {
        this.survivalThresholdMin = survivalThresholdMin;
    }

    public void setSurvivalThresholdMax(int survivalThresholdMax) {
        this.survivalThresholdMax = survivalThresholdMax;
    }

    public void randomizeGrid() {
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = rand.nextBoolean();
            }
        }
    }

    public void placePattern(boolean[][] pattern, int startX, int startY, boolean flipHoriz, boolean flipVert) {
        boolean[][] copyPattern = copyPattern(pattern);

        if (flipHoriz) {
            copyPattern = Patterns.flipHorizontally(copyPattern);
        }
        if (flipVert) {
            copyPattern = Patterns.flipVertically(copyPattern);
        }

        for (int i = 0; i < copyPattern.length; i++) {
            for (int j = 0; j < copyPattern[i].length; j++) {
                if (copyPattern[i][j]) {
                    setCell(startX + i, startY + j, true);
                }
            }
        }
    }

    private boolean[][] copyPattern(boolean[][] pattern) {
        boolean[][] temp = new boolean[pattern.length][];
        for (int i = 0; i < pattern.length; i++) {
            temp[i] = Arrays.copyOf(pattern[i], pattern[i].length);
        }
        return temp;
    }
}
