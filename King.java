public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // �������� ������ �����
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // ������ �� ����� �������� �� �����
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ��������, ��� ������ ����� �� ���� ������ � ����� �����������
        int rowDiff = Math.abs(toLine - line);
        int colDiff = Math.abs(toColumn - column);
        if (rowDiff > 1 || colDiff > 1) {
            return false; // ������� ������ ��� ������
        }

        // ��������, ��� �������� ������� �� ��������� ��� ������
        if (isUnderAttack(chessBoard, toLine, toColumn)) {
            return false;
        }

        // �������� �������� ������� �� ������� ������ ���� �� �����
        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    // ����� ��� ��������, ��������� �� ��������� ������� ��� ������
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        // ������ �� ���� ������� �����, ����� ���������, ����� �� �����-���� �� ��� ��������� ������
        for (int i = 0; i < chessBoard.board.length; i++) {
            for (int j = 0; j < chessBoard.board[i].length; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    // ���� ������ ���������������� ����� ����� ��������� ������� ������, ���������� true
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false; // �� ���� ������ �� ����� ��������� �������
    }
}
