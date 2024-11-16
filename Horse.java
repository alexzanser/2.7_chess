public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // �������� �� ����� �� ������� �����
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // ��������, ��� ���� �� �������� �� ��� �� �����
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ��������, ��� ��� "������ �"
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);
        if (!((deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2))) {
            return false;
        }

        // ��������, ��� ������� ������� ���� �����, ���� ������ ������� ����������
        return !chessBoard.isOccupied(toLine, toColumn) ||
                chessBoard.isOpponentPiece(toLine, toColumn, this.color);
    }
}
