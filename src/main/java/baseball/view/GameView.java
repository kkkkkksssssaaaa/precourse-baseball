package baseball.view;

import baseball.controller.GameController;
import baseball.model.ComputerPlayer;
import baseball.model.GameStatus;
import baseball.model.Player;
import nextstep.utils.Console;

public class GameView {
    GameController gameController = new GameController();
    PlayerView playerView = new PlayerView();
    boolean isThreeStrike = false;

    private final int maxSize;

    public GameView(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public static void printStart() {
        System.out.println("야구 게임 시작");
    }

    public static void printRestart() {
        System.out.println("게임을 재시작합니다.");
    }

    public static void printEnd() {
        System.out.println("프로그램을 종료합니다.");
    }

    public GameStatus playGame(ComputerPlayer computer) {
        isThreeStrike = false;

        Player player = playerView.initializePlayer();

        Boolean isNothingOrFourBall = checkAndPrintNothingOrFourBall(player, computer);

        if (!isNothingOrFourBall) {
            String writeHints = writeHintsAndCheckThreeStrike(player, computer);
            return checkGameEnd(writeHints);
        }

        return GameStatus.PLAYING;
    }

    private Boolean checkAndPrintNothingOrFourBall(Player player, ComputerPlayer computer) {
        return ifNothingWriteHint(player, computer) || ifFourBallWriteHint(player, computer);
    }

    private String writeHintsAndCheckThreeStrike(Player player, ComputerPlayer computer) {
        return checkStrike(player, computer) + checkBall(player, computer);
    }

    private Boolean ifNothingWriteHint(Player player, ComputerPlayer computer) {
        Boolean isNothing = gameController.isNothing(player, computer);

        if (isNothing) {
            System.out.println("낫싱");
            return true;
        }

        return false;
    }

    private Boolean ifFourBallWriteHint(Player player, ComputerPlayer computer) {
        Boolean isFourBall = gameController.isFourBall(player, computer);

        if (isFourBall) {
            System.out.println("포볼");
            return true;
        }

        return false;
    }

    private GameStatus checkGameEnd(String writeHints) {
        System.out.println(writeHints);

        if (isThreeStrike) {
            return gameEnd();
        }

        return GameStatus.PLAYING;
    }

    private GameStatus gameEnd() {
        printGameEnd();

        GameStatus selectGameStatus = selectGameStatus();

        checkGameStatusIsRestartOrEndGame(selectGameStatus);

        return selectGameStatus;
    }

    private void printGameEnd() {
        System.out.printf("%d개의 숫자를 모두 맞히셨습니다! 게임 끝 \n", maxSize);
        System.out.printf("게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.\n",
                GameStatus.RESTART.getValue(),
                GameStatus.END_GAME.getValue());
    }

    private GameStatus selectGameStatus() {
        String restartInput = Console.readLine();

        return gameController.checkRestart(restartInput);
    }

    private void checkGameStatusIsRestartOrEndGame(GameStatus gameStatus) {
        while (!gameStatus.equals(GameStatus.RESTART)
                && !gameStatus.equals((GameStatus.END_GAME))) {

            System.out.printf("[ERROR] 잘못된 값을 입력했습니다! " +
                            "게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.\n",
                    GameStatus.RESTART.getValue(),
                    GameStatus.END_GAME.getValue());

            gameStatus = selectGameStatus();
        }
    }

    private String checkStrike(Player player, ComputerPlayer computer) {
        Integer getStrikeCount = gameController.getStrikeCount(player, computer);

        isThreeStrike(computer.getMaxSize(), getStrikeCount);

        if (getStrikeCount > 0) {
            return getStrikeCount + "스트라이크 ";
        }

        return "";
    }

    private String checkBall(Player player, ComputerPlayer computer) {
        Integer getBallCount = gameController.getBallCount(player, computer);

        if (getBallCount > 0) {
            return getBallCount + "볼";
        }

        return "";
    }

    private void isThreeStrike(Integer maxSize, Integer getStrikeCount) {
        if (getStrikeCount.equals(maxSize)) {
            isThreeStrike = true;
        }
    }

}