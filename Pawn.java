public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "P";
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

        int direction = this.color.equals("White") ? 1 : -1; // ����������� ��������

        // �������� ���� �� ���� ������ ������
        if (column == toColumn && toLine - line == direction &&
                !chessBoard.isOccupied(toLine, toColumn)) {
            return true;
        }

        // �������� ������� ���� �� ��� ������ ������
        if (column == toColumn && (line == 1 && this.color.equals("White") || line == 6 && this.color.equals("Black")) &&
                toLine - line == 2 * direction && !chessBoard.isOccupied(toLine, toColumn) &&
                !chessBoard.isOccupied(line + direction, column)) {
            return true;
        }

        // �������� ������ ������ ��������� �� ���������
        return Math.abs(toColumn - column) == 1 && toLine - line == direction &&
                chessBoard.isOpponentPiece(toLine, toColumn, this.color);
    }
}
