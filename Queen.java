public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Ферзь не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, ходит ли ферзь как слон или ладья
        if (Math.abs(toLine - line) != Math.abs(toColumn - column) && (line != toLine && column != toColumn)) {
            return false;
        }

        // Проверяем, что путь чист от фигур
        if (!chessBoard.isPathClear(line, column, toLine, toColumn)) {
            return false;
        }

        // Проверяем, есть ли на конечной позиции фигура того же цвета
        return !chessBoard.isOccupied(toLine, toColumn) ||
                chessBoard.isOpponentPiece(toLine, toColumn, this.color);
    }
}
