package tic.tac.toe;

public class TicTacToe {
    private static TicTacToe instance;
    private static String gameboard[][] = {
        {"", "", ""},
        {"", "", ""},
        {"", "", ""}
    };
    private int turn = 1;
    private final int[] computerMove = new int[2];
    
    /**
     * This method returns an existing instance of TicTacToe class.
     * If instance does not exist, then it creates a new instance and returns it.
     * Otherwise, returns the existing instance
     * 
     * @return The TicTacToe class instance
     */
    public static TicTacToe getInstance() {
        if (instance == null) {
            instance = new TicTacToe();
        }
        
        return instance;
    }    

    public String[][] getGameboard() {
        return gameboard;
    }

    /**
     * A getter method for "turn" variable
     * @return An integer that indicates whose turn it is. 1 -> player one, 2 -> player two
     */
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

    /**
     * Updates board value at specified row and col with players marks "X" or "O"
     * @param row Row number
     * @param col Column number
     * @param mark Player's mark (X/O)
     */
    public void updateBoard(int row, int col, String mark) {
        gameboard[row][col] = mark;
    }

    /**
     *
     * @return
     * 
     * null value if there is no winner.
     * 
     * "tie" if the is a tie
     * 
     * X/O if player won
     */
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
                    gameboard[i][j] = "O";
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

        gameboard[bestMoveRow][bestMoveCol] = "O";
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
        // Reset board
        String[][] newGameboard = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
        };
        gameboard = newGameboard;
        
        // Reset turn
        this.turn = 1;
    }
}
