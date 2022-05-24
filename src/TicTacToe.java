import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cells:");
        String board = scanner.next().toUpperCase();
        char[] boardArr = board.toCharArray();
        System.out.println("---------");
        System.out.println("| " + board.charAt(0) + " " + board.charAt(1) + " " + board.charAt(2) + " |");
        System.out.println("| " + board.charAt(3) + " " + board.charAt(4) + " " + board.charAt(5) + " |");
        System.out.println("| " + board.charAt(6) + " " + board.charAt(7) + " " + board.charAt(8) + " |");
        System.out.println("---------");

        char winner = checkWinner(boardArr);
        if (isImpossible(boardArr, winner)) System.out.println("Impossible");
        if (!isFinished(boardArr) && (winner != 'X' && winner != 'O') && (!isImpossible(boardArr, winner)))
            System.out.println("Game not finished");
        if (isFinished(boardArr) && !isImpossible(boardArr, winner) && winner != 'X' && winner != 'O')
            System.out.println("Draw");
        if (winner == 'X') System.out.println("X wins");
        if (winner == 'O') System.out.println("O wins");
    }

    public static boolean isImpossible(char[] board, char winner) {
        int differenceCtr = 0;
        int xCtr = 0;
        int circleCtr = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'X') xCtr++;
            if (board[i] == 'O') circleCtr++;
            differenceCtr = xCtr - circleCtr;
        }
        if (differenceCtr < 0) differenceCtr *= -1;

        return (differenceCtr > 1 || winner == 'Z') ? true : false;
    }

    public static boolean isFinished(char[] board) {
        return new String(board).contains("_") || new String(board).contains(" ") ? false : true;
    }

    public static char checkWinner(char[] board) {
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
        int winnerCtr = 0;
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
                winnerCtr++;
            } else if (Arrays.equals(twoDBoard[i], winner2)) {
                winner = 'O';
                winnerCtr++;
            }

        }

        return winnerCtr < 2 ? winner : 'Z';
    }
}



