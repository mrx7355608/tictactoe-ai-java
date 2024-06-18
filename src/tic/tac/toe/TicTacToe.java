package tic.tac.toe;

public class TicTacToe {

    private static String gameboard[][] = {
        {"", "", ""},
        {"", "", ""},
        {"", "", ""}
    };
    private int turn = 1;
    private final String humanMark = "X";
    private final String computerMark = "O";
    private final int[] computerMove = new int[2];
    private static TicTacToe instance;
    
    public static TicTacToe getInstance() {
        if (instance == null) {
            instance = new TicTacToe();
        }
        
        return instance;
    }    

    public String[][] getGameboard() {
        return gameboard;
    }

    public int getTurn() {
        return turn;
    }

    public void switchTurn() {
        if (this.turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }
    }

    public void updateBoard(int row, int col, String mark) {
        gameboard[row][col] = mark;
    }

    public boolean isValidMove(int row, int col) {
        return gameboard[row][col].isBlank();
    }

    public String checkWinner() {
        // Horizontal checks
        for (int i = 0; i < 3; i++) {
            if (gameboard[i][0].isBlank() == false
                    && gameboard[i][0].equals(gameboard[i][1])
                    && gameboard[i][0].equals(gameboard[i][2])) {
                return gameboard[i][0];
            }
        }

        // Vertical checks
        for (int i = 0; i < 3; i++) {
            if (gameboard[0][i].isBlank() == false
                    && gameboard[0][i].equals(gameboard[1][i])
                    && gameboard[0][i].equals(gameboard[2][i])) {
                return gameboard[0][i];
            }
        }

        // Diagonal checks
        if (gameboard[0][0].isBlank() == false
                && gameboard[0][0].equals(gameboard[1][1])
                && gameboard[0][0].equals(gameboard[2][2])) {
            return gameboard[0][0];
        }
        if (gameboard[0][2].isBlank() == false
                && gameboard[0][2].equals(gameboard[1][1])
                && gameboard[0][2].equals(gameboard[2][0])) {
            return gameboard[0][2];
        }

        if (this.checkTie()) {
            return "tie";
        }

        return null;
    }

    private boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameboard[i][j].isBlank()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void computerMakeMove() {
        int bestScore = -1000;
        int bestMoveRow = 0;
        int bestMoveCol = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameboard[i][j].isBlank()) {
                    gameboard[i][j] = this.computerMark;
                    int score = this.minimax(0, false);
                    gameboard[i][j] = "";

                    if (score > bestScore) {
                        bestScore = score;
                        bestMoveRow = i;
                        bestMoveCol = j;
                    }
                }
            }
        }

        gameboard[bestMoveRow][bestMoveCol] = this.computerMark;
        this.computerMove[0] = bestMoveRow;
        this.computerMove[1] = bestMoveCol;
    }

    private int minimax(int depth, boolean isMaximizingPlayer) {
        // Check if algorithm has reached terminal condition
        String winner = this.checkWinner();
        if (winner != null) {
            return switch (winner) {
                case "O" ->
                    1;
                case "X" ->
                    -1;
                default ->
                    0;
            };
        }

        if (isMaximizingPlayer) {
            // Get best move of maximizer
            int bestScore = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameboard[i][j].isBlank()) {
                        gameboard[i][j] = "O";
                        int score = minimax(depth + 1, false);
                        gameboard[i][j] = " ";

                        // Compare scores
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }

            return bestScore;
        } else {
            // Get best move of minimizer
            int bestScore = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameboard[i][j].isBlank()) {
                        gameboard[i][j] = "X";
                        int score = minimax(depth + 1, true);
                        gameboard[i][j] = " ";

                        // Compare scores
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }

            return bestScore;
        }
    }

    public int[] getComputerMove() {
        return computerMove;
    }

    public void reset() {
        String[][] newGameboard = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
        };
        gameboard = newGameboard;
        this.turn = 1;
    }
}
