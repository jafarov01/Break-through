package src;

public class Logic {
    private final int boardSize;
    private Player currentPlayer;
    private final FieldType[][] board;

    public Logic(int boardSize) {
        currentPlayer = Player.WHITE;
        this.boardSize = boardSize;
        board = new FieldType[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (i == 0 || i == 1) {
                    board[i][j] = FieldType.BLACK;
                } else if (i == boardSize - 2 || i == boardSize - 1) {
                    board[i][j] = FieldType.WHITE;
                } else {
                    board[i][j] = FieldType.NONE;
                }
            }
        }
    }

    public void move(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (isMoveValid(fromRow, fromColumn, toRow, toColumn)) {
            board[toRow][toColumn] = getFieldType(fromRow, fromColumn);
            board[fromRow][fromColumn] = FieldType.NONE;
            currentPlayer = currentPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
            checkWinner();
        }
    }

    public boolean isMoveValid(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (!isValidLoc(toRow, toColumn)) {
            return false;
        }
        if (Math.abs(toRow - fromRow) > 1 || Math.abs(toColumn - fromColumn) > 1) {
            return false;
        }

        if (currentPlayer == Player.BLACK) {
            if (toRow <= fromRow) {
                return false;
            }
            if (board[fromRow][fromColumn] == FieldType.WHITE) {
                return false;
            }
            if (board[toRow][toColumn] == FieldType.BLACK) {
                return false;
            }
            if ((fromRow + 1) == toRow && fromColumn == toColumn) {
                return false;
            }
        } else {
            if (toRow >= fromRow) {
                return false;
            }
            if (board[fromRow][fromColumn] == FieldType.BLACK) {
                return false;
            }
            if (board[toRow][toColumn] == FieldType.WHITE) {
                return false;
            }
            if ((fromRow - 1) == toRow && fromColumn == toColumn) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidLoc(int row, int column) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
    }

    public Player checkWinner() {
        Player winner = null;
        for (int j = 0; j < boardSize; j++) {
            if (getFieldType(boardSize - 1, j) == FieldType.BLACK) {
                winner = Player.BLACK;
            }
            if (getFieldType(0, j) == FieldType.WHITE) {
                winner = Player.WHITE;
            }
        }
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public FieldType getFieldType(int row, int column) {
        return board[row][column];
    }

    public FieldType[][] getBoard() {
        return board;
    }
}
