public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка, что конь не остается на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что ход "буквой Г"
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);
        if (!((deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2))) {
            return false;
        }

        // Проверка, что целевая позиция либо пуста, либо занята фигурой противника
        return !chessBoard.isOccupied(toLine, toColumn) ||
                chessBoard.isOpponentPiece(toLine, toColumn, this.color);
    }
}
