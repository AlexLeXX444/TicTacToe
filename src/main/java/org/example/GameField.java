package org.example;

import java.util.Arrays;
import java.util.Random;

public class GameField {

    private static GameField instance;

    // константы
    private final int ROW_X = 5;
    private final int COL_Y = 5;
    private final int WIN_COUNT = 4;
    private final char[][] field = new char[ROW_X][COL_Y];
    private final char DOT_HUMAN = 'X';
    private final char DOT_AI = 'O';
    private final char DOT_EMPTY = '•';

    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
        }
        return instance;
    }

    private GameField() {
        initializeField();
    }

    private void initializeField() {
        for (int i = 0; i < ROW_X; i++) {
            Arrays.fill(field[i], DOT_EMPTY);
        }
    }

    public void printField() {
        System.out.print("   ");
        for (int i = 1; i <= ROW_X; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < ROW_X; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < COL_Y; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // ход игрока
    public boolean humanTurn(int x, int y) {
        return makeMove(x, y, DOT_HUMAN);
    }

    // ход компьютера
    public void aiTurn() {
        int x, y;
        Random random = new Random();
        do
        {
            x = random.nextInt(ROW_X);
            y = random.nextInt(COL_Y);
        }
        while (!makeMove(x, y, DOT_AI));
    }

    // делаем ход
    private boolean makeMove(int x, int y, char c) {
        if (x < 1 || x > ROW_X || y < 1 || y > COL_Y || field[x - 1][y - 1] != DOT_EMPTY) {
            return false;
        }
        field[x - 1][y - 1] = c;
        return true;
    }

    // проверяем на выигрыш игрока
    public boolean isGamerWinHuman() {
        return isGameOver(DOT_HUMAN);
    }

    // проверяем на выигрыш компьютера
    public boolean isGamerWinAi() {
        return isGameOver(DOT_AI);
    }

    // проверка победы
    private boolean isGameOver(char gamer) {
        return checkRows(gamer) || checkColumns(gamer) || checkDiagonals(gamer);
    }

    // проверяем по вертикали
    private boolean checkRows(char gamer) {
        for (int i = 0; i < ROW_X; i++) {
            int counter = 0;
            for (int j = 0; j < COL_Y; j++) {
                if (field[i][j] == gamer) {
                    counter++;
                    if (counter == WIN_COUNT) {
                        return true;
                    }
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }

    // проверяем по горизонтали
    private boolean checkColumns(char gamer) {
        for (int i = 0; i < COL_Y; i++) {
            int counter = 0;
            for (int j = 0; j < ROW_X; j++) {
                if (field[j][i] == gamer) {
                    counter++;
                    if (counter == WIN_COUNT) {
                        return true;
                    }
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }

    // проверяем диагонали
    private boolean checkDiagonals(char gamer) {
        for (int i = 0; i <= ROW_X - WIN_COUNT; i++) {
            for (int j = 0; j <= COL_Y - WIN_COUNT; j++) {
                if (checkDiagonal(gamer, i, j) || checkAntiDiagonal(gamer, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // проверяем диагональ справа на лево
    private boolean checkDiagonal(char gamer, int row, int col) {
        int counter = 0;
        for (int i = 0; i < WIN_COUNT; i++) {
            if (field[row + i][col + i] == gamer) {
                counter++;
                if (counter == WIN_COUNT) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        return false;
    }

    //проверяем диагональ слева на право
    private boolean checkAntiDiagonal(char gamer, int row, int col) {
        int counter = 0;
        for (int i = 0; i < WIN_COUNT; i++) {
            if (field[row + i][col + WIN_COUNT - i - 1] == gamer) {
                counter++;
                if (counter == WIN_COUNT) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        return false;
    }

    // проверяем что еще осталось место для хода
    public boolean fieldIsFull() {
        for (int i = 0; i < ROW_X; i++) {
            for (int j = 0; j < COL_Y; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
