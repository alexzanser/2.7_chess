public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Слон не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Слон может ходить только по диагонали
        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) {
            return false;
        }

        // Проверяем, что путь чист от фигур
        if (!chessBoard.isPathClear(line, column, toLine, toColumn)) {
            return false;
        }

        // Проверяем, есть ли на конечной позиции фигура того же цвета
        return !chessBoard.isOccupied(toLine, toColumn) ||
                chessBoard.isOpponentPiece(toLine, toColumn, this.color);

        // Все условия выполнены, ход возможен
    }
}
