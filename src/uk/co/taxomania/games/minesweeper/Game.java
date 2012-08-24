package uk.co.taxomania.games.minesweeper;

import java.util.Random;
import java.util.TreeSet;

class Game {
    boolean player1Turn = true;
    final Cell[] game = new Cell[256];

    Game() {
        initGrid();
    } // Game()

    private void initGrid() {
        final Cell[][] grid = new Cell[16][16];
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
        calcMines(grid, 0, 1);
        calcMines(grid, 15, 14);

        // middle
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                int count = 0;
                count += calcIAdjacent(grid, i, j);
                count += calcJAdjacent(grid, i, j);
                count += calcIAdjacent(grid, i - 1, j);
                count += calcIAdjacent(grid, i + 1, j);
                grid[i][j].noSurroundingMines = count;
            } // for
        } // for

        for (int i = 0; i < 256; i++) {
            game[i] = grid[i / 16][i % 16];
        }

    } // initGrid()

    private int calcIAdjacent(final Cell[][] grid, final int i, final int j) {
        int count = 0;
        if (grid[i][j - 1].isMine) {
            count++;
        } // if
        if (grid[i][j + 1].isMine) {
            count++;
        } // if
        return count;
    } // calcIAdjacent(int, int)

    private int calcJAdjacent(final Cell[][] grid, final int i, final int j) {
        int count = 0;
        if (grid[i - 1][j].isMine) {
            count++;
        } // if
        if (grid[i + 1][j].isMine) {
            count++;
        } // if
        return count;
    } // calcJAdjacent(int, int)

    private void calcColMines(final Cell[][] grid, final int j, final int adj) {
        for (int i = 1; i < 15; i++) {
            int count = 0;
            count += calcJAdjacent(grid, i, j);
            count += calcJAdjacent(grid, i, adj);
            if (grid[i][adj].isMine) {
                count++;
            } // if
            grid[i][j].noSurroundingMines = count;
        } // for
    } // calcColMines(int, int)

    private void calcRowMines(final Cell[][] grid, final int i, final int adj) {
        for (int j = 1; j < 15; j++) {
            int count = 0;
            count += calcIAdjacent(grid, i, j);
            count += calcIAdjacent(grid, adj, j);
            if (grid[adj][j].isMine) {
                count++;
            } // if
            grid[i][j].noSurroundingMines = count;
        } // for
    } // calcRowMines(int, int)

    private void calcCornerMines(final Cell[][] grid, final int i, final int j, final int a,
            final int b) {
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

    void calcTopCornerMines(final Cell[][] grid, final int j, final int b) {
        calcCornerMines(grid, 0, j, 1, b);
    } // calcTopCornerMines(int, int)

    private void calcBottomCornerMines(final Cell[][] grid, final int j, final int b) {
        calcCornerMines(grid, 15, j, 14, b);
    } // calcBottomCornerMines(int, int)

    private void calcMines(final Cell[][] grid, final int pos, final int adj) {
        calcTopCornerMines(grid, adj, pos);
        calcBottomCornerMines(grid, adj, pos);
        calcRowMines(grid, pos, adj);
        calcColMines(grid, pos, adj);
    } // calcMines(int, int)

    static final class Cell {
        boolean isMine = false;
        int noSurroundingMines = 0;
        boolean clicked = false;

        @Override
        public String toString() {
            return "Mine = " + isMine + ", surrounding=" + noSurroundingMines;
        } // toString()
    } // class Cell
} // class Game
