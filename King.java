public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка границ доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Король не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что король ходит на одну клетку в любом направлении
        int rowDiff = Math.abs(toLine - line);
        int colDiff = Math.abs(toColumn - column);
        if (rowDiff > 1 || colDiff > 1) {
            return false; // Слишком далеко для короля
        }

        // Проверка, что конечная позиция не находится под атакой
        if (isUnderAttack(chessBoard, toLine, toColumn)) {
            return false;
        }

        // Проверка конечной позиции на наличие фигуры того же цвета
        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    // Метод для проверки, находится ли указанная позиция под атакой
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        // Проход по всем фигурам доски, чтобы проверить, может ли какая-либо из них атаковать короля
        for (int i = 0; i < chessBoard.board.length; i++) {
            for (int j = 0; j < chessBoard.board[i].length; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    // Если фигура противоположного цвета может атаковать позицию короля, возвращаем true
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false; // Ни одна фигура не может атаковать позицию
    }
}
