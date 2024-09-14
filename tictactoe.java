import java.util.Scanner;

public class tictactoe {
    private char[][] board;
    private char currentPlayer;
    private static final int SIZE = 3;

    public tictactoe() {
        board = new char[SIZE][SIZE];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with numbers 1-9
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = (char) ('1' + (i * SIZE + j));
            }
        }
    }

    // Display the current board state
    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
                if (j < SIZE - 1) System.out.print(" |");
            }
            if (i < SIZE - 1) System.out.println("\n-----------");
        }
        System.out.println();
    }

    // Make a move based on player's input
    public boolean makeMove(int position) {
        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;

        if (board[row][col] != 'X' && board[row][col] != 'O') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // Check if the current player has won
    public boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                    (board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                return true;
            }
        }

        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true;
        }

        return false;
    }

    // Check if the board is full (to determine a draw)
    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    // Switch to the other player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Play the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        System.out.println("Welcome to Tic Tac Toe!");
        displayBoard();

        while (!gameWon && !isBoardFull()) {
            System.out.println("Player " + currentPlayer + ", enter the number of the cell where you want to place your mark:");
            int move = scanner.nextInt();

            if (move < 1 || move > 9 || !makeMove(move)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            displayBoard();

            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                gameWon = true;
            } else if (isBoardFull()) {
                System.out.println("The game is a draw!");
            } else {
                switchPlayer();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        tictactoe game = new tictactoe();
        game.playGame();
    }
}
