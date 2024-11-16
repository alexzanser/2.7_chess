public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // ��������, ��� ��������� � �������� ������� ��������� � �������� �����
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // ��������, ��� ������ �� �������� �� ��� �� �����
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ����������� ��������� ����� "�" ��� ����
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        // ��� ���� ��������, ���� ����������� ������������� ����� "�"
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
