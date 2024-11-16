public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // создаем поле для игры
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (board[startLine][startColumn] == null ||
                    !nowPlayer.equals(board[startLine][startColumn].getColor())) {
                return false;
            }

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                // Если фигура — король или ладья, устанавливаем check = false
                if (board[startLine][startColumn].getSymbol().equals("K") ||
                        board[startLine][startColumn].getSymbol().equals("R")) {
                    board[startLine][startColumn].check = false;
                }

                board[endLine][endColumn] = board[startLine][startColumn]; // перемещаем фигуру
                board[startLine][startColumn] = null; // освобождаем предыдущую ячейку
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White"; // переключаем ход

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Метод для рокировки по 0 столбцу
    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            // Проверка условий рокировки для белого короля и ладьи на 0 столбце
            if (board[0][0] != null && board[0][4] != null &&
                    board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") &&
                    board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                    board[0][0].check && board[0][4].check &&
                    board[0][1] == null && board[0][2] == null && board[0][3] == null &&
                    !new King("White").isUnderAttack(this, 0, 2)) {

                // Выполнение рокировки
                board[0][4] = null;
                board[0][2] = new King("White");
                board[0][2].check = false;
                board[0][0] = null;
                board[0][3] = new Rook("White");
                board[0][3].check = false;

                nowPlayer = "Black"; // следующий ход
                return true;
            }
        } else {
            // Проверка условий рокировки для черного короля и ладьи на 0 столбце
            if (board[7][0] != null && board[7][4] != null &&
                    board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                    board[7][0].check && board[7][4].check &&
                    board[7][1] == null && board[7][2] == null && board[7][3] == null &&
                    !new King("Black").isUnderAttack(this, 7, 2)) {

                // Выполнение рокировки
                board[7][4] = null;
                board[7][2] = new King("Black");
                board[7][2].check = false;
                board[7][0] = null;
                board[7][3] = new Rook("Black");
                board[7][3].check = false;

                nowPlayer = "White"; // следующий ход
                return true;
            }
        }
        return false;
    }

    // Метод для рокировки по 7 столбцу
    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            // Проверка условий рокировки для белого короля и ладьи на 7 столбце
            if (board[0][7] != null && board[0][4] != null &&
                    board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") &&
                    board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                    board[0][7].check && board[0][4].check &&
                    board[0][5] == null && board[0][6] == null &&
                    !new King("White").isUnderAttack(this, 0, 6)) {

                // Выполнение рокировки
                board[0][4] = null;
                board[0][6] = new King("White");
                board[0][6].check = false;
                board[0][7] = null;
                board[0][5] = new Rook("White");
                board[0][5].check = false;

                nowPlayer = "Black"; // следующий ход
                return true;
            }
        } else {
            // Проверка условий рокировки для черного короля и ладьи на 7 столбце
            if (board[7][7] != null && board[7][4] != null &&
                    board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                    board[7][7].check && board[7][4].check &&
                    board[7][5] == null && board[7][6] == null &&
                    !new King("Black").isUnderAttack(this, 7, 6)) {

                // Выполнение рокировки
                board[7][4] = null;
                board[7][6] = new King("Black");
                board[7][6].check = false;
                board[7][7] = null;
                board[7][5] = new Rook("Black");
                board[7][5].check = false;

                nowPlayer = "White"; // следующий ход
                return true;
            }
        }
        return false;
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    // Проверяет, занята ли позиция фигуры
    public boolean isOccupied(int line, int column) {
        return board[line][column] != null;
    }

    // Проверяет, занята ли позиция фигурой противоположного цвета
    public boolean isOpponentPiece(int line, int column, String color) {
        return board[line][column] != null && !board[line][column].getColor().equals(color);
    }

    public boolean isPathClear(int startLine, int startColumn, int endLine, int endColumn) {
        int dLine = Integer.signum(endLine - startLine);
        int dColumn = Integer.signum(endColumn - startColumn);

        int currentLine = startLine + dLine;
        int currentColumn = startColumn + dColumn;

        while (currentLine != endLine || currentColumn != endColumn) {
            if (isOccupied(currentLine, currentColumn)) {
                return false;
            }
            currentLine += dLine;
            currentColumn += dColumn;
        }
        return true;
    }
}
