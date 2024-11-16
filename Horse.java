public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка, что фигура не остается на том же месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Определение возможных ходов "Г" для коня
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        // Ход коня возможен, если перемещение соответствует форме "Г"
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
