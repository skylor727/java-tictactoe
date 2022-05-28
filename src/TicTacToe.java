

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        int turn = 1;
        String board = "_________";
        printBoard(board);
        char winner = checkWinner(board);
        while (winner == 'Z' && !checkDraw(board)) {
            String[] newVals = handleMove(board, turn);
            board = newVals[0];
            turn = Integer.parseInt(newVals[1]);
            printBoard(board);
            winner = checkWinner(board);
        }

        if (!checkDraw(board)) System.out.println(winner + " wins");
        else System.out.println("Draw");
    }


    public static void printBoard(String board) {
        System.out.println("---------");
        System.out.println("| " + board.charAt(0) + " " + board.charAt(1) + " " + board.charAt(2) + " |");
        System.out.println("| " + board.charAt(3) + " " + board.charAt(4) + " " + board.charAt(5) + " |");
        System.out.println("| " + board.charAt(6) + " " + board.charAt(7) + " " + board.charAt(8) + " |");
        System.out.println("---------");

    }

    public static String[] handleMove(String board, int turn) {
        char[][] boardArr = new char[3][3];
        int[] coordinates;
        boolean validNums = false;
        int ctr = 0;
        int xCoord = 0;
        int yCoord = 0;
        //Break the string board into a 2d array
        for (int i = 0; i < boardArr.length; i++) {
            for (int x = 0; x < boardArr[i].length; x++) {
                boardArr[i][x] = board.charAt(ctr);
                ctr++;
            }
        }

        //Continue Looping for as long as the user enters invalid coordinates
        do {
            coordinates = getCoordinates(boardArr);
            xCoord = coordinates[0] - 1;
            yCoord = coordinates[1] - 1;
            if (coordinates[0] >= 1 && coordinates[0] < 4 && coordinates[1] >= 1 && coordinates[1] < 4)
                validNums = true;
        } while (!validNums);

        //Update board
        boardArr[xCoord][yCoord] = turn == 1 ? 'X' : 'O';
        turn *= -1;
        String updatedBoard = "";
        for (int i = 0; i < boardArr.length; i++) {
            for (int x = 0; x < boardArr.length; x++) {
                updatedBoard += boardArr[i][x];
            }
        }
        String[] returnVals = new String[]{updatedBoard, String.valueOf(turn)};
        return returnVals;
    }

    public static int[] getCoordinates(char[][] boardArr) {
        Scanner scanner = new Scanner(System.in);
        int[] coordinates = new int[2];
        System.out.print("Enter the coordinates: ");
        String[] coordinateString = (scanner.nextLine().split(" "));
        try {
            coordinates[0] = Integer.parseInt(coordinateString[0]);
            coordinates[1] = Integer.parseInt(coordinateString[1]);
            int xCoord = coordinates[0] - 1;
            int yCoord = coordinates[1] - 1;
            if (xCoord < 0 || xCoord > 2 || yCoord < 0 || yCoord > 2)
                System.out.println("Coordinates should be from 1 to 3!");
            else if (boardArr[xCoord][yCoord] == 'X' || boardArr[xCoord][yCoord] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                coordinates[0] = -1;
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers");
        }
        return coordinates;
    }

    public static char checkWinner(String boardStr) {
        char board[] = boardStr.toCharArray();
        int[][] winningCombos = {
                //Vertical
                {2, 5, 8},
                {1, 4, 7},
                {0, 3, 6},
                //Horizontal
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                //Diagonal
                {2, 4, 6},
                {0, 4, 8}
        };


        char[] winner1 = {'X', 'X', 'X'};
        char[] winner2 = {'O', 'O', 'O'};
        char winner = 0;
        char[][] twoDBoard = new char[8][3];
        for (int i = 0; i < twoDBoard.length; i++) {
            for (int x = 0; x < twoDBoard[i].length; x++) {
                twoDBoard[i][x] = board[winningCombos[i][x]];
            }
        }
        for (int i = 0; i < twoDBoard.length; i++) {
            if (Arrays.equals(twoDBoard[i], winner1)) {
                winner = 'X';
            } else if (Arrays.equals(twoDBoard[i], winner2)) {
                winner = 'O';
            }

        }
        return winner != 0 ? winner : 'Z';
    }

    public static boolean checkDraw(String board) {
        if (board.contains("_") || board.contains(" ")) {
            return false;
        }
        return checkWinner(board) == 'Z' ? true : false;
    }
}