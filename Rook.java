public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, что начальная и конечная позиции находятся в пределах доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Ладья не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Ладья может двигаться только по вертикали или горизонтали
        if (line != toLine && column != toColumn) {
            return false; // Если изменение происходит и по строкам, и по столбцам, ход недопустим
        }

        // Определение направления движения
        int rowDirection = Integer.compare(toLine, line);
        int colDirection = Integer.compare(toColumn, column);

        int currentLine = line + rowDirection;
        int currentColumn = column + colDirection;

        // Проверка на наличие фигур на пути к целевой позиции
        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // Если на пути есть фигура, ладья не может пройти
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
        return "R";
    }
}
