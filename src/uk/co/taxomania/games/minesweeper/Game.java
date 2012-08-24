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

    private int calcIAdjacent(final int i, final int j) {
        int count = 0;
        if (grid[i][j - 1].isMine) {
            count++;
        } // if
        if (grid[i][j + 1].isMine) {
            count++;
        } // if
        return count;
    } // calcIAdjacent(int, int)

    private int calcJAdjacent(final int i, final int j) {
        int count = 0;
        if (grid[i - 1][j].isMine) {
            count++;
        } // if
        if (grid[i + 1][j].isMine) {
            count++;
        } // if
        return count;
    } // calcJAdjacent(int, int)

    private void calcColMines(final int j, final int adj) {
        for (int i = 1; i < 15; i++) {
            int count = 0;
            count += calcJAdjacent(i, j);
            count += calcJAdjacent(i, adj);
            if (grid[i][adj].isMine) {
                count++;
            } // if
            grid[i][j].noSurroundingMines = count;
        } // for
    } // calcColMines(int, int)

    private void calcRowMines(final int i, final int adj) {
        for (int j = 1; j < 15; j++) {
            int count = 0;
            count += calcIAdjacent(i, j);
            count += calcIAdjacent(adj, j);
            if (grid[adj][j].isMine) {
                count++;
            } // if
            grid[i][j].noSurroundingMines = count;
        } // for
    } // calcRowMines(int, int)

    private void calcCornerMines(final int i, final int j, final int a, final int b) {
        int mines = 0;
        if (grid[i][b].isMine) {
            mines++;
        } // if
        if (grid[a][b].isMine) {
            mines++;
        } // if
        if (grid[a][j].isMine) {
            mines++;
        } // if
        grid[i][j].noSurroundingMines = mines;
    } // calcCornerMines(int, int, int, int)

    void calcTopCornerMines(final int j, final int b) {
        calcCornerMines(0, j, 1, b);
    } // calcTopCornerMines(int, int)

    private void calcBottomCornerMines(final int j, final int b) {
        calcCornerMines(15, j, 14, b);
    } // calcBottomCornerMines(int, int)

    private void calcMines(final int pos, final int adj) {
        calcTopCornerMines(adj, pos);
        calcBottomCornerMines(adj, pos);
        calcRowMines(pos, adj);
        calcColMines(pos, adj);
    } // calcMines(int, int)

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
        calcMines(0, 1);
        calcMines(15, 14);

        // middle
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                int count = 0;
                count += calcIAdjacent(i, j);
                count += calcJAdjacent(i, j);
                count += calcIAdjacent(i - 1, j);
                count += calcIAdjacent(i + 1, j);
                grid[i][j].noSurroundingMines = count;
            } // for
        } // for
    } // Game()
} // class Game
