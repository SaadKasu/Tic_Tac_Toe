import Controller.GameController;
import Exceptions.IllegalNumberOfBotsException;
import Exceptions.PlayerCountException;
import Exceptions.TwoPlayerSameSymbol;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    public static void main(String[] args) throws PlayerCountException, TwoPlayerSameSymbol, IllegalNumberOfBotsException {
        GameController gSC = new GameController();
        Scanner inp = new Scanner(System.in);

        int dimension = 3;

        List<Player> players = new ArrayList<>();
        players.add(new Player(new Symbol('!'), "Saad",123l));
        List<WinningStratergy> winningStratergies = new ArrayList<>();
        winningStratergies.add(new RowWinningStratergy());
        winningStratergies.add(new ColumnWinningStratergy());

        Game currentGame = gSC.startGame(players,winningStratergies,dimension);

        while(currentGame.getStateOfGame() == GameState.IN_PROGRESS){
            currentGame.makeNextMove();
            System.out.println("Do you want to undo your move ? ");
        }
    }
}
