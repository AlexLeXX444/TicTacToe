package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameField gameField = GameField.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        gameField.printField();

        while (!gameOver) {
            System.out.print("Введите строку и столбец через пробел (например, '1 2'): ");
            String input = scanner.nextLine();
            String[] coordinates = input.split(" ");

            if (coordinates.length != 2) {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(coordinates[0]);
                col = Integer.parseInt(coordinates[1]);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
                continue;
            }

            if (!gameField.humanTurn(row, col)) {
                System.out.println("Некорректный ход. Попробуйте еще раз.");
                continue;
            }

            if (gameField.isGamerWinHuman()) {
                System.out.println("Игрок победил!");
                gameOver = true;
            }

            gameField.aiTurn();

            gameField.printField();

            if (gameField.isGamerWinAi()) {
                System.out.println("Компьютер победил!");
                gameOver = true;
            }

            if (gameField.fieldIsFull()) {
                System.out.println("Ничья!");
                gameOver = true;
            }
        }
    }
}