package Model;

public class Move {
    private Cell cell;
    private Player player;

    public Move(Cell c, Player s){
        this.cell = c;
        this.player = s;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setSymbol(Player player) {
        this.player = player;
    }
}
