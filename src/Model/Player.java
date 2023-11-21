package Model;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private Long id;
    private PlayerType playerType;

    public Player(Symbol s, String n, Long id){
        this.symbol = s;
        this.name = n;
        this.id = id;
        this.playerType = PlayerType.HUMAN;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Move makeMove(Board board){
        Scanner inp = new Scanner(System.in);
        int row = inp.nextInt(), col = inp.nextInt();
        return new Move(new Cell(row,col),this);
    }
}
