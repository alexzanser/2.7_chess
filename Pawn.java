public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Пешка не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Различие между текущей позицией и целевой по строкам и столбцам
        int rowDiff = toLine - line;
        int colDiff = Math.abs(toColumn - column);

        // Определяем правила хода для белой пешки
        if (color.equals("White")) {
            // Пешка двигается только вперед по линии (увеличение line)
            if (column == toColumn) {
                // Первый ход: можно на 2 клетки вперед с позиции line == 1
                if (line == 1 && rowDiff == 2 && chessBoard.board[line + 1][column] == null && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
                // Обычный ход: на 1 клетку вперед
                if (rowDiff == 1 && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
            }
            // Взятие: диагональный ход на одну клетку по столбцу
            if (colDiff == 1 && rowDiff == 1 && chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }

        // Определяем правила хода для черной пешки
        if (color.equals("Black")) {
            // Пешка двигается только вперед по линии (уменьшение line)
            if (column == toColumn) {
                // Первый ход: можно на 2 клетки вперед с позиции line == 6
                if (line == 6 && rowDiff == -2 && chessBoard.board[line - 1][column] == null && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
                // Обычный ход: на 1 клетку вперед
                if (rowDiff == -1 && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
            }
            // Взятие: диагональный ход на одну клетку по столбцу
            if (colDiff == 1 && rowDiff == -1 && chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }

        return false; // Если не удалось пройти ни одно из условий, ход недопустим
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
