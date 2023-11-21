package Model;

import java.util.HashMap;

public class ColumnWinningStratergy implements WinningStratergy {
    private HashMap<Integer, HashMap<Symbol,Integer>> countMap = new HashMap();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int column = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!countMap.containsKey(column)){
            countMap.put(column,new HashMap<>());
        }

        HashMap<Symbol, Integer> columnMap = countMap.get(column);

        if (!columnMap.containsKey(symbol)){
            columnMap.put(symbol,0);
        }
        columnMap.put(symbol,columnMap.get(symbol) + 1);

        return columnMap.get(symbol) == board.getSize();

    }

    public void handleUndoWinningStratergy(Board board, Move move) {
        int row = move.getCell().getRow(), column = move.getCell().getColumn();
        HashMap <Symbol, Integer> currentMap = countMap.get(column);
        currentMap.put(move.getPlayer().getSymbol(),currentMap.get(move.getPlayer().getSymbol()) - 1);
    }
}
