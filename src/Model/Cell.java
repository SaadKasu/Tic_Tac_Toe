package Model;

public class Cell {
    private int row;
    private int column;

    private Player player;
    private CellState cellState;

    public Cell(int r, int c){
        this.cellState = CellState.EMPTY;
        this.row = r;
        this.column = c;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void printCell(){
        System.out.println(player.getSymbol());
    }
}
