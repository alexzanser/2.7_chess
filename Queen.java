public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // �������� ������ �����
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // ����� �� ����� �������� �� �����
        if (line == toLine && column == toColumn) {
            return false;
        }

        // �������� ���������� ����������� �������� (���� �� ������, ���� �� ���������)
        int rowDiff = Math.abs(toLine - line);
        int colDiff = Math.abs(toColumn - column);
        if (line != toLine && column != toColumn && rowDiff != colDiff) {
            return false; // �� �� ��������� � �� �� ������
        }

        // ����������� ����������� ��������
        int rowDirection = Integer.compare(toLine, line);
        int colDirection = Integer.compare(toColumn, column);

        int currentLine = line + rowDirection;
        int currentColumn = column + colDirection;

        // �������� �� ������� ����� �� ���� � �������� �������
        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // �� ���� ���� ������
            }
            currentLine += rowDirection;
            currentColumn += colDirection;
        }

        // �������� �������� �������
        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
