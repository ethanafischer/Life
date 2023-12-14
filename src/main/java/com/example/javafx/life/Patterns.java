package com.example.javafx.life;

public class Patterns {
    public static boolean[][] getPattern(String patternName) {

        boolean x = false;
        boolean o = true;
        return switch (patternName) {
            case "Acorn" -> new boolean[][]{
                    {x, o, x, x, x, x, x},
                    {x, x, x, o, x, x, x},
                    {o, o, x, x, o, o, o}
            };
            case "Butterfly" -> new boolean[][]{
                    {o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {o, x, x, x, x, o, x, x, x, x, x, x, x, o, o, x, x, x},
                    {o, x, x, x, x, x, x, x, x, x, x, x, o, o, x, o, o, o},
                    {x, o, x, x, x, x, x, x, x, x, x, o, o, x, o, o, o, o},
                    {x, x, x, o, o, x, x, x, o, o, x, o, o, x, x, o, o, x},
                    {x, x, x, x, x, o, x, x, x, x, o, x, x, o, x, x, x, x},
                    {x, x, x, x, x, x, o, x, o, x, o, x, o, x, x, x, x, x},
                    {x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, o, x, o, x, o, x, o, x, x, x, x, x},
                    {x, x, x, x, x, o, x, x, x, x, o, x, x, o, x, x, x, x},
                    {x, x, x, o, o, x, x, x, o, o, x, o, o, x, x, o, o, x},
                    {x, o, x, x, x, x, x, x, x, x, x, o, o, x, o, o, o, o},
                    {o, x, x, x, x, x, x, x, x, x, x, x, o, o, x, o, o, o},
                    {o, x, x, x, x, o, x, x, x, x, x, x, x, o, o, x, x, x},
                    {o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x},
            };
            case "Diehard" -> new boolean[][]{
                    {x, x, x, x, x, x, o, x},
                    {o, o, x, x, x, x, x, x},
                    {x, o, x, x, x, o, o, o}
            };
            case "Gosper glider gun" -> new boolean[][]{
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, o, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, o, o, x, x, x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, o, o},
                    {x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, o, x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, o, o},
                    {o, o, x, x, x, x, x, x, x, x, o, x, x, x, x, x, o, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {o, o, x, x, x, x, x, x, x, x, o, x, x, x, o, x, o, o, x, x, x, x, o, x, o, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, o, x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x}
            };
            case "Simkin glider gun" -> new boolean[][]{
                    {o, o, x, x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {o, o, x, x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, x, o, o, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, o, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, x, o, x, x, o, o},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, x, x, x, o, x, x, x, o, o},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, x, x, x, x, x, x, x, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x}
            };
            case "Glider" -> new boolean[][]{
                    {x, o, x},
                    {x, x, o},
                    {o, o, o}
            };
            case "LWSS" -> new boolean[][]{
                    {x, o, x, x, o},
                    {o, x, x, x, x},
                    {o, x, x, x, o},
                    {o, o, o, o, x}
            };
            case "MWSS" -> new boolean[][]{
                    {x, x, x, o, x, x},
                    {x, o, x, x, x, o},
                    {o, x, x, x, x, x},
                    {o, x, x, x, x, o},
                    {o, o, o, o, o, x}
            };
            case "HWSS" -> new boolean[][]{
                    {x, x, x, o, o, x, x},
                    {x, o, x, x, x, x, o},
                    {o, x, x, x, x, x, x},
                    {o, x, x, x, x, x, o},
                    {o, o, o, o, o, o, x}
            };
            case "Pulsar" -> new boolean[][]{
                    {x, x, o, o, o, x, x, x, o, o, o, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {o, x, x, x, x, o, x, o, x, x, x, x, o},
                    {o, x, x, x, x, o, x, o, x, x, x, x, o},
                    {o, x, x, x, x, o, x, o, x, x, x, x, o},
                    {x, x, o, o, o, x, x, x, o, o, o, x, x},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, o, o, o, x, x, x, o, o, o, x, x},
                    {o, x, x, x, x, o, x, o, x, x, x, x, o},
                    {o, x, x, x, x, o, x, o, x, x, x, x, o},
                    {o, x, x, x, x, o, x, o, x, x, x, x, o},
                    {x, x, x, x, x, x, x, x, x, x, x, x, x},
                    {x, x, o, o, o, x, x, x, o, o, o, x, x},
            };
            case "Penta-decathlon" -> new boolean[][]{
                    {x, x, x, o, o, o, x, x, x},
                    {x, x, o, x, x, x, o, x, x},
                    {x, o, x, x, x, x, x, o, x},
                    {x, x, x, x, x, x, x, x, x},
                    {o, x, x, x, x, x, x, x, o},
                    {o, x, x, x, x, x, x, x, o},
                    {x, x, x, x, x, x, x, x, x},
                    {x, o, x, x, x, x, x, o, x},
                    {x, x, o, x, x, x, o, x, x},
                    {x, x, x, o, o, o, x, x, x}
            };
            case "R-pentomino" -> new boolean[][]{
                    {x, o, o},
                    {o, o, x},
                    {x, o, x}
            };
            case "Switch engine 1" -> new boolean[][]{
                    {x, x, x, x, x, x, o, x},
                    {x, x, x, x, o, x, o, o},
                    {x, x, x, x, o, x, o, x},
                    {x, x, x, x, o, x, x, x},
                    {x, x, o, x, x, x, x, x},
                    {o, x, o, x, x, x, x, x}
            };
            case "Switch engine 2" -> new boolean[][]{
                    {o, o, o, x, o},
                    {o, x, x, x, x},
                    {x, x, x, o, o},
                    {x, o, o, x, o},
                    {o, x, o, x, o}
            };
            case "Switch engine 3" -> new boolean[][]{
                    {o, o, o, o, o, o, o, o, x, o, o, o, o, o, x, x, x, o, o, o, x, x, x, x, x, x, o, o, o, o, o, o, o, x, o, o, o, o, o}
            };
            default -> null;
        };
    }

    public static boolean[][] flipHorizontally(boolean[][] pattern) {
        int height = pattern.length;
        int width = pattern[0].length;
        boolean[][] flipped = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                flipped[i][j] = pattern[i][width - 1 - j];
            }
        }
        return flipped;
    }

    public static boolean[][] flipVertically(boolean[][] pattern) {
        int height = pattern.length;
        boolean[][] flipped = new boolean[height][];

        for (int i = 0; i < height; i++) {
            flipped[i] = pattern[height - 1 - i];
        }
        return flipped;
    }

    public static boolean[][] rotate90Deg(boolean[][] pattern) {
        if (pattern == null || pattern.length == 0 || pattern[0].length == 0) {
            return null;
        }

        int originalRows = pattern.length;
        int originalCols = pattern[0].length;
        boolean[][] rotatedPattern = new boolean[originalCols][originalRows];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols; j++) {
                rotatedPattern[j][originalRows - 1 - i] = pattern[i][j];
            }
        }

        return rotatedPattern;
    }
}
