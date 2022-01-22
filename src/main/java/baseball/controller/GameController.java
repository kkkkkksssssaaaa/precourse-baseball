package baseball.controller;

import baseball.model.ComputerPlayer;
import baseball.model.GameStatus;
import baseball.model.Player;

public class GameController extends AbstractGameController {

    @Override
    public Boolean isNothing(Player player, ComputerPlayer computer) {
        if (!super.isNotNull(player, computer)) {
            return false;
        }

        return super.checkNothing(player, computer);
    }

    @Override
    public Boolean isFourBall(Player player, ComputerPlayer computer) {
        if (!super.isNotNull(player, computer)) {
            return false;
        }

       return super.checkFourBall(player, computer);
    }

    @Override
    public Integer getStrikeCount(Player player, ComputerPlayer computer) {
        if (!super.isNotNull(player, computer)) {
            return 0;
        }

        return super.checkStrike(player, computer);
    }

    @Override
    public Integer getBallCount(Player player, ComputerPlayer computer) {
        if (!super.isNotNull(player, computer)) {
            return 0;
        }

        return super.checkBall(player, computer);
    }

    @Override
    public GameStatus checkRestart(String input) {
        Boolean isIntegerString = isIntegerString(input);

        if (isIntegerString) {
            return GameStatus.findStatus(Integer.parseInt(input));
        }

        return GameStatus.NOTHING;
    }

    private Boolean isIntegerString(String input) {
        try {
            Integer toInteger = Integer.valueOf(input);

            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
