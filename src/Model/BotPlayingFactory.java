package Model;

public class BotPlayingFactory {
    public static BotPlayingStratergy getBotPlayingStratergy (BotDifficultyLevel difficultyLevel){
        if (difficultyLevel == BotDifficultyLevel.EASY)
            return new EasyPlayingStratergy();
        return null;
    }
}
