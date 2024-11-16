public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка границ доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Ферзь не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка допустимых направлений движения (либо по прямой, либо по диагонали)
        int rowDiff = Math.abs(toLine - line);
        int colDiff = Math.abs(toColumn - column);
        if (line != toLine && column != toColumn && rowDiff != colDiff) {
            return false; // Не по диагонали и не по прямой
        }

        // Определение направления движения
        int rowDirection = Integer.compare(toLine, line);
        int colDirection = Integer.compare(toColumn, column);

        int currentLine = line + rowDirection;
        int currentColumn = column + colDirection;

        // Проверка на наличие фигур на пути к конечной позиции
        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // На пути есть фигура
            }
            currentLine += rowDirection;
            currentColumn += colDirection;
        }

        // Проверка конечной позиции
        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
