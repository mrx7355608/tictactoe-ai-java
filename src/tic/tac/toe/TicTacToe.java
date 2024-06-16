package tic.tac.toe;


public class TicTacToe {

    private final String gameboard[][] = {
        {"", "", ""},
        {"", "", ""},
        {"", "", ""}
    };
    private String turn = "human";
    private final String humanMark = "X";
    private final String computerMark = "O";
    private final int[] computerMove = new int[2];
    
    public String[][] getGameboard() {
        return gameboard;
    }

    public String getTurn() {
        return turn;
    }

    public String getData(int row, int col) {
        return this.gameboard[row][col];
    }
    
    public void switchTurn() {
        if (this.turn.equals("human")) {
            this.turn = "computer";
        } else {
            this.turn = "human";
        }
    }

    public void makeMove(int row, int col, String mark) {
        if (this.isValidMove(row, col)) {
            this.gameboard[row][col] = mark;
        }
    }

    private boolean isValidMove(int row, int col) {
        return this.gameboard[row][col].isBlank();
    }

    public String checkWinner() {
        // Horizontal checks
        for (int i = 0; i < 3; i++) {
            if (this.gameboard[i][0].isBlank() == false
                    && this.gameboard[i][0].equals(this.gameboard[i][1])
                    && this.gameboard[i][0].equals(this.gameboard[i][2])) {
                return this.gameboard[i][0];
            }
        }

        // Vertical checks
        for (int i = 0; i < 3; i++) {
            if (this.gameboard[0][i].isBlank() == false
                    && this.gameboard[0][i].equals(this.gameboard[1][i])
                    && this.gameboard[0][i].equals(this.gameboard[2][i])) {
                return this.gameboard[0][i];
            }
        }

        // Diagonal checks
        if (this.gameboard[0][0].isBlank() == false
                && this.gameboard[0][0].equals(this.gameboard[1][1])
                && this.gameboard[0][0].equals(this.gameboard[2][2])) {
            return this.gameboard[0][0];
        }
        if (this.gameboard[0][2].isBlank() == false
                && this.gameboard[0][2].equals(this.gameboard[1][1])
                && this.gameboard[0][2].equals(this.gameboard[2][0])) {
            return this.gameboard[0][2];
        }

        if (this.checkTie()) {
            return "tie";
        }

        return null;
    }

    private boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.gameboard[i][j].isBlank()) {
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
                if (this.gameboard[i][j].isBlank()) {
                    this.gameboard[i][j] = this.computerMark;
                    int score = this.minimax(0, false);
                    this.gameboard[i][j] = "";

                    if (score > bestScore) {
                        bestScore = score;
                        bestMoveRow = i;
                        bestMoveCol = j;
                    }
                }
            }
        }
        
        this.gameboard[bestMoveRow][bestMoveCol] = this.computerMark;
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
                    if (this.gameboard[i][j].isBlank()) {
                        this.gameboard[i][j] = "O";
                        int score = minimax(depth + 1, false);
                        this.gameboard[i][j] = " ";

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
                    if (this.gameboard[i][j].isBlank()) {
                        this.gameboard[i][j] = "X";
                        int score = minimax(depth + 1, true);
                        this.gameboard[i][j] = " ";

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
}
