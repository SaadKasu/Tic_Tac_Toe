package Model;

import java.util.HashMap;

public class RowWinningStratergy implements WinningStratergy {
    private HashMap<Integer, HashMap<Symbol,Integer>> countMap = new HashMap();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!countMap.containsKey(row)){
            countMap.put(row,new HashMap<>());
        }

        HashMap<Symbol, Integer> rowMap = countMap.get(row);

        if (!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol,rowMap.get(symbol) + 1);

        return rowMap.get(symbol) == board.getSize();

    }

    @Override
    public void handleUndoWinningStratergy(Board board, Move move) {
        int row = move.getCell().getRow(), column = move.getCell().getColumn();
        HashMap <Symbol, Integer> currentMap = countMap.get(row);
        currentMap.put(move.getPlayer().getSymbol(),currentMap.get(move.getPlayer().getSymbol()) - 1);
    }
}
