package Model;

public interface WinningStratergy {
    public boolean checkWinner(Board board, Move move);
    public void handleUndoWinningStratergy(Board board, Move move);
}
