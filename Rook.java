public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // �������� �� ����� �� ������� �����
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // ����� �� ����� �������� �� �����
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ����� ����� ��������� ������ �� ������ (���� �� ��� �� ������, ���� �� ���� �� �������)
        if (line != toLine && column != toColumn) {
            return false;
        }

        // ���������, ��� ���� ���� �� �����
        if (!chessBoard.isPathClear(line, column, toLine, toColumn)) {
            return false;
        }

        // ���������, ���� �� �� �������� ������� ������ ���� �� �����
        return !chessBoard.isOccupied(toLine, toColumn) ||
                chessBoard.isOpponentPiece(toLine, toColumn, this.color);
    }
}
