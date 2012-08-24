package uk.co.taxomania.games.minesweeper;

import java.util.Random;
import java.util.TreeSet;

class Game {
    private static final class Cell {
        boolean isMine = false;
        int noSurroundingMines = 0;
        boolean clicked = false;

        @Override
        public String toString() {
            return "Mine = " + isMine + ", surrounding=" + noSurroundingMines;
        } // toString()
    } // class Cell

    private final Cell[][] grid = new Cell[16][16];
    boolean player1Turn = true;

    private void calcEdgeMines(int a, int b, int c, int d, int e, int f, int g, int h) {
        int mines = 0;
        if (grid[a][b].isMine) {
            mines++;
        }
        if (grid[c][d].isMine) {
            mines++;
        }
        if (grid[e][f].isMine) {
            mines++;
        }
        grid[g][h].noSurroundingMines = mines;
    }

    Game() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell();
            } // for
        } // for

        final TreeSet<Integer> mines = new TreeSet<Integer>();
        final Random r = new Random();
        for (int i = 0; i < 51; i++) {
            int n = r.nextInt(256);
            while (!mines.add(n)) {
                n = ++n % 256;
            } // while
            grid[n / 16][n % 16].isMine = true;
        } // for

        // corners
        calcEdgeMines(0, 1, 1, 0, 1, 1, 0, 0);
        calcEdgeMines(0, 14, 1, 14, 1, 15, 0, 15);
        calcEdgeMines(14, 0, 14, 1, 15, 1, 15, 0);
        calcEdgeMines(14, 14, 14, 15, 15, 14, 15, 15);

        // edges
        for (int j = 1; j < 15; j++) {
            int count = 0;
            if (grid[0][j - 1].isMine) {
                count++;
            }
            if (grid[0][j + 1].isMine) {
                count++;
            }
            if (grid[1][j - 1].isMine) {
                count++;
            }
            if (grid[1][j].isMine) {
                count++;
            }
            if (grid[1][j + 1].isMine) {
                count++;
            }
            grid[0][j].noSurroundingMines = count;
        }

        for (int j = 1; j < 15; j++) {
            int count = 0;
            if (grid[14][j - 1].isMine) {
                count++;
            }
            if (grid[14][j].isMine) {
                count++;
            }
            if (grid[14][j + 1].isMine) {
                count++;
            }
            if (grid[15][j - 1].isMine) {
                count++;
            }
            if (grid[15][j + 1].isMine) {
                count++;
            }
            grid[15][j].noSurroundingMines = count;
        }

        for (int i = 1; i < 15; i++) {
            int count = 0;
            if (grid[i - 1][0].isMine) {
                count++;
            }
            if (grid[i + 1][0].isMine) {
                count++;
            }
            if (grid[i - 1][1].isMine) {
                count++;
            }
            if (grid[i][1].isMine) {
                count++;
            }
            if (grid[i + 1][1].isMine) {
                count++;
            }
            grid[i][0].noSurroundingMines = count;
        }

        for (int i = 1; i < 15; i++) {
            int count = 0;
            if (grid[i - 1][14].isMine) {
                count++;
            }
            if (grid[i][14].isMine) {
                count++;
            }
            if (grid[i + 1][14].isMine) {
                count++;
            }
            if (grid[i - 1][15].isMine) {
                count++;
            }
            if (grid[i + 1][15].isMine) {
                count++;
            }
            grid[i][15].noSurroundingMines = count;
        }

        // middle
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                int count = 0;
                if (grid[i - 1][j - 1].isMine) {
                    count++;
                }
                if (grid[i - 1][j].isMine) {
                    count++;
                }
                if (grid[i - 1][j + 1].isMine) {
                    count++;
                }
                if (grid[i][j - 1].isMine) {
                    count++;
                }
                if (grid[i][j + 1].isMine) {
                    count++;
                }
                if (grid[i + 1][j - 1].isMine) {
                    count++;
                }
                if (grid[i + 1][j].isMine) {
                    count++;
                }
                if (grid[i + 1][j + 1].isMine) {
                    count++;
                }
                grid[i][j].noSurroundingMines = count;
            } // for
        } // for
    } // Game()
} // class Game
