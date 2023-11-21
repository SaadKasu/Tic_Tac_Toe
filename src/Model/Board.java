package Model;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> boardState;

    public Board(int dimensions){
        this.size = dimensions;
        this.boardState = new ArrayList<>();
        for (int i = 0;i<size;i++){
            boardState.add(new ArrayList<>());
            for (int j =0;j<size;j++)
                boardState.get(i).add(new Cell(i,j));
        }
    }

    public int getSize(){
        return this.size;
    }

    public List<List<Cell>> getBoardState(){
        return this.boardState;
    }

    public void printBoard(){
        for (int i = 0;i<size;i++)
            for (int j = 0;j<size;j++){
                if (boardState.get(i).get(j).getCellState() == CellState.EMPTY) {
                    System.out.println();
                } else {
                    boardState.get(i).get(j).printCell();
                }
                ;
            }
    }
}
