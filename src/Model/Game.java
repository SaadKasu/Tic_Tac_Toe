package Model;

import Exceptions.IllegalNumberOfBotsException;
import Exceptions.PlayerCountException;
import Exceptions.TwoPlayerSameSymbol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private List<WinningStratergy> winningStratergies;
    private GameState stateOfGame;

    private int nextPlayerIndex;

    private Game (List<Player> players, List<WinningStratergy> winningStratergies, int dimensions){
        this.players = players;
        this.stateOfGame = GameState.IN_PROGRESS;
        this.winningStratergies = winningStratergies;
        this.moves = new ArrayList<>();
        this.board = new Board(dimensions);
    }

    public GameState getStateOfGame() {
        return stateOfGame;
    }

    public void setStateOfGame(GameState stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    public List<WinningStratergy> getWinningStratergies() {
        return winningStratergies;
    }

    public void setWinningStratergies(List<WinningStratergy> winningStratergies) {
        this.winningStratergies = winningStratergies;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static class GameBuilder{
        private List<Player> players;
        private List<WinningStratergy> winningStratergies;
        private int dimensions;

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStratergy> getWinningStratergies() {
            return winningStratergies;
        }

        public GameBuilder setWinningStratergies(List<WinningStratergy> winningStratergies) {
            this.winningStratergies = winningStratergies;
            return this;
        }

        public int getDimensions() {
            return dimensions;
        }

        public GameBuilder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }
        private void validateBotCount() throws IllegalNumberOfBotsException{
            int botSize = 0;
            for(Player currentPlayer : players)
                if ( currentPlayer.getPlayerType() == PlayerType.BOT )
                    botSize++;
            if (botSize > 1)
                throw new IllegalNumberOfBotsException();
        }
        private void validateDimensionAndPlayerCount() throws PlayerCountException{
            if (players.size() != dimensions)
                throw new PlayerCountException();
        }
        private void validateSymbolUniqueness() throws TwoPlayerSameSymbol {
            HashSet<Character> symbols = new HashSet<>();
            for (Player p : players){
                if (symbols.contains(p.getSymbol()))
                    throw new TwoPlayerSameSymbol();
                symbols.add(p.getSymbol().getSymbol());
            }
        }
        private void validate() throws TwoPlayerSameSymbol, PlayerCountException, IllegalNumberOfBotsException {
            validateSymbolUniqueness();
            validateBotCount();
            validateDimensionAndPlayerCount();
        }
        public Game build() throws PlayerCountException, TwoPlayerSameSymbol, IllegalNumberOfBotsException {
            validate();
            return new Game(players,winningStratergies,dimensions);
        }
    }

    public void makeNextMove(){
        Player currentMovePlayer = players.get(nextPlayerIndex);
        Move move = currentMovePlayer.makeMove(board);
        if (!validateTheMove(move))
            return;

        int row = move.getCell().getRow(), col = move.getCell().getRow();

        Cell cell = getBoard().getBoardState().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentMovePlayer);
        //moves.add(move); // If we use this move object we will have a new cell object not the same object
                        // used in the board so instead we should create a new Move object and pass
                        // The board cell object in there
        moves.add(new Move(cell,currentMovePlayer));
        nextPlayerIndex = (nextPlayerIndex  + 1)% getBoard().getSize();
        checkForWinner(move);
        if (moves.size() == board.getSize()* board.getSize()){
            stateOfGame = GameState.DRAW;
        }

    }

    private void checkForWinner(Move move){
        for (WinningStratergy stratergy : winningStratergies)
            stratergy.checkWinner(board,move);
    }

    private boolean validateTheMove(Move mv){
        int row = mv.getCell().getRow();
        int col = mv.getCell().getColumn();

        if (row >= board.getSize() || col >= board.getSize())
            return false;

        if (getBoard().getBoardState().get(row).get(col).getCellState() == CellState.FILLED)
                return false;
        return true;
    }

    public void undo(){
        if (moves.size() == 0)
                return;
        Move lastMove = moves.get(moves.size() - 1);
        moves.remove(moves.size() - 1);
        Cell lastCell = lastMove.getCell();
        lastCell.setCellState(CellState.EMPTY);
        lastCell.setPlayer(null);
        nextPlayerIndex = (nextPlayerIndex -1 + getBoard().getSize())%getBoard().getSize();
        for (WinningStratergy winStrat : winningStratergies)
            winStrat.handleUndoWinningStratergy(board, lastMove);

    }
}
