package baseball;

import baseball.model.ComputerPlayer;
import baseball.model.GameStatus;
import baseball.view.PlayerView;
import baseball.view.GameView;

public class Application {
    public static void main(String[] args) {
        GameView.printStart();

        PlayerView playerView = new PlayerView();

        GameStatus status = GameStatus.PLAYING;
        ComputerPlayer computer = playerView.initializeComputer();

        GameView gameView = new GameView(computer.getMaxSize());

        while(status.equals(GameStatus.PLAYING)) {
            status = gameView.playGame(computer);
        }

        checkGameStatus(status);
    }

    private static void checkGameStatus(GameStatus gameStatus) {
        if (gameStatus.equals(GameStatus.RESTART)) {
            GameView.printRestart();
            main(new String[] {});
        }

        if (gameStatus.equals(GameStatus.END_GAME)) {
            GameView.printEnd();
        }
    }
}
