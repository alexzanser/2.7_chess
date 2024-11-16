public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Ладья не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Ладья может двигаться только по прямой (либо по той же строке, либо по тому же столбцу)
        if (line != toLine && column != toColumn) {
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
