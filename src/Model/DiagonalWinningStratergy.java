package Model;

import java.util.HashMap;

public class DiagonalWinningStratergy implements WinningStratergy{
    HashMap<Symbol, Integer> leftDiagonalMap = new HashMap<>();
    HashMap<Symbol, Integer> rightDiagonalMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow(), column = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        if (row == column){
            if (!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol,0);
            }
            leftDiagonalMap.put(symbol,leftDiagonalMap.get(symbol) + 1);

            return leftDiagonalMap.get(symbol) == board.getSize();
        }
        else if (row == (board.getSize() - 1 - column)){
            if (!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);
            }
            rightDiagonalMap.put(symbol,rightDiagonalMap.get(symbol) + 1);

            return rightDiagonalMap.get(symbol) == board.getSize();
        }

        return false;
    }

    public void handleUndoWinningStratergy(Board board, Move move) {
        int row = move.getCell().getRow(), column = move.getCell().getColumn();
        Symbol sym = move.getPlayer().getSymbol();
        if (row == column){
            leftDiagonalMap.put(sym, leftDiagonalMap.get(sym) -1);
        } else if (row == (board.getSize() - 1 - column)) {
            rightDiagonalMap.put(sym, rightDiagonalMap.get(sym) -1);
        }

    }

}
