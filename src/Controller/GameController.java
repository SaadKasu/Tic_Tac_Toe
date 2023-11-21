package Controller;

import Exceptions.IllegalNumberOfBotsException;
import Exceptions.PlayerCountException;
import Exceptions.TwoPlayerSameSymbol;
import Model.Game;
import Model.Player;
import Model.WinningStratergy;

import java.util.List;

public class GameController {
    public Game startGame (List<Player> players, List<WinningStratergy> winningStratergies, int Dimension ) throws PlayerCountException, TwoPlayerSameSymbol, IllegalNumberOfBotsException {
        Game gs = new Game.GameBuilder().setDimensions(10).setPlayers(null).setWinningStratergies(null).build();
        return gs;
    }

    public void makeMove(Game gs){
        gs.makeNextMove();
    }

    public void printBoard(Game gs){
        gs.getBoard().printBoard();
    }
}
