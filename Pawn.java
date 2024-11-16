public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // ���������, ��� ������� ��������� � �������� �����
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // ����� �� ����� �������� �� �����
        if (line == toLine && column == toColumn) {
            return false;
        }

        // �������� ����� ������� �������� � ������� �� ������� � ��������
        int rowDiff = toLine - line;
        int colDiff = Math.abs(toColumn - column);

        // ���������� ������� ���� ��� ����� �����
        if (color.equals("White")) {
            // ����� ��������� ������ ������ �� ����� (���������� line)
            if (column == toColumn) {
                // ������ ���: ����� �� 2 ������ ������ � ������� line == 1
                if (line == 1 && rowDiff == 2 && chessBoard.board[line + 1][column] == null && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
                // ������� ���: �� 1 ������ ������
                if (rowDiff == 1 && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
            }
            // ������: ������������ ��� �� ���� ������ �� �������
            if (colDiff == 1 && rowDiff == 1 && chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }

        // ���������� ������� ���� ��� ������ �����
        if (color.equals("Black")) {
            // ����� ��������� ������ ������ �� ����� (���������� line)
            if (column == toColumn) {
                // ������ ���: ����� �� 2 ������ ������ � ������� line == 6
                if (line == 6 && rowDiff == -2 && chessBoard.board[line - 1][column] == null && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
                // ������� ���: �� 1 ������ ������
                if (rowDiff == -1 && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
            }
            // ������: ������������ ��� �� ���� ������ �� �������
            if (colDiff == 1 && rowDiff == -1 && chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }

        return false; // ���� �� ������� ������ �� ���� �� �������, ��� ����������
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
