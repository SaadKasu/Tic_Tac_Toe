package Model;

public class Bot extends Player{
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStratergy playingStratergy;

    public Bot(Symbol s, String name,Long id, BotDifficultyLevel bTD){
        super(s,name,id);
        super.setPlayerType(PlayerType.BOT);
        this.difficultyLevel = bTD;
        playingStratergy = BotPlayingFactory.getBotPlayingStratergy(difficultyLevel);
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public BotPlayingStratergy getPlayingStratergy() {
        return playingStratergy;
    }

    public void setPlayingStratergy(BotPlayingStratergy playingStratergy) {
        this.playingStratergy = playingStratergy;
    }

    @Override
    public Move makeMove(Board board){
        Move ms = playingStratergy.makeMove(board);

        ms.setSymbol(this);

        return ms;
    }
}
