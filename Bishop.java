public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Слон не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что движение происходит по диагонали
        int rowDiff = Math.abs(toLine - line);
        int colDiff = Math.abs(toColumn - column);

        if (rowDiff != colDiff) {
            return false; // Если разница по строкам и столбцам не совпадает, движение не по диагонали
        }

        // Проверка, что путь свободен
        int rowDirection = (toLine - line) > 0 ? 1 : -1; // Направление по строкам
        int colDirection = (toColumn - column) > 0 ? 1 : -1; // Направление по столбцам

        int currentLine = line + rowDirection;
        int currentColumn = column + colDirection;

        // Проверка на наличие фигур на пути к целевой позиции
        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // Если на пути есть фигура, слон не может пройти
            }
            currentLine += rowDirection;
            currentColumn += colDirection;
        }

        // Проверка на наличие фигуры того же цвета на конечной позиции
        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
