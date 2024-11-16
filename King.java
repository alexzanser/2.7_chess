public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Король не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Король ходит на 1 клетку в любом направлении
        if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) {
            return false;
        }

        // Проверяем, есть ли на конечной позиции фигура того же цвета
        if (chessBoard.isOccupied(toLine, toColumn) &&
                !chessBoard.isOpponentPiece(toLine, toColumn, this.color)) {
            return false;
        }

        return !isUnderAttack(chessBoard, toLine, toColumn);
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
