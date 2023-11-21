package Model;

public class EasyPlayingStratergy implements BotPlayingStratergy{
    @Override
    public Move makeMove(Board board) {
        for (int i =0;i<board.getSize();i++)
            for (int j = 0;j< board.getSize();j++){
                Cell currentCell = board.getBoardState().get(i).get(j);
                if (currentCell.getCellState() == CellState.EMPTY){
                    return new Move(new Cell(i,j), null);
                }
            }
        return null;
    }
}
