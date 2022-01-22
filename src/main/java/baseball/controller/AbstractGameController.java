package baseball.controller;

import baseball.model.ComputerPlayer;
import baseball.model.GameStatus;
import baseball.model.Player;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGameController {

    public abstract Boolean isNothing(Player player, ComputerPlayer computer);
    public abstract Boolean isFourBall(Player player, ComputerPlayer computer);
    public abstract Integer getStrikeCount(Player player, ComputerPlayer computer);
    public abstract Integer getBallCount(Player player, ComputerPlayer computer);
    public abstract GameStatus checkRestart(String input);

    /**
     * Player 정보의 null 검사
     * @return null 이 포함 되어 있을 경우 false 반환
     * */
    protected Boolean isNotNull(Player player, ComputerPlayer computer) {
        if (!isNotNullPlayer(player, computer)) {
            return false;
        }

        if (!isNotNullPlayersNumber(player.getNumbers())) {
            return false;
        }

        if (!isNotNullPlayersNumber(computer.getNumbers())) {
            return false;
        }

        return true;
    }

    /**
     * Strike 를 체크 후 그 개수를 반환
     * @return strike 의 개수
     * */
    protected Integer checkStrike(Player player, ComputerPlayer computer) {
        int getCount = 0;

        for (int i = 0; i < computer.getSize(); i++) {
            getCount += checkSamePlaceCount(player.getNumbers().get(i), computer.getNumbers().get(i));
        }

        return getCount;
    }

    /**
     * Ball 을 체크 후 그 개수를 반환
     * @return ball 의 개수
     * */
    protected Integer checkBall(Player player, ComputerPlayer computer) {
        if (!isContainsAtLeastOne(player, computer)) {
            return 0;
        }

        return checkOnlyContainsCount(player, computer);
    }

    /**
     * Nothing 여부를 체크 후 Nothing 인 경우 true 반환
     * */
    protected Boolean checkNothing(Player player, ComputerPlayer computer) {
        if (!isNotContainsAll(player, computer)) {
            return false;
        }

        return true;
    }

    /**
     * FourBall 여부를 체크 후 FourBall 인 경우 true 반환
     * */
    protected Boolean checkFourBall(Player player, ComputerPlayer computer) {
        if (!isContainsAll(player, computer)) {
            return false;
        }

        if (!isNotEqualsAll(player, computer)) {
            return false;
        }

        return true;
    }

    /**
     * Player 들이 null 인지 체크함
     * @return Null 이 아닐 경우 true 반환
     * */
    private Boolean isNotNullPlayer(Player player, ComputerPlayer computer) {
        if (!Optional.ofNullable(player).isPresent()) {
            return false;
        }

        if (!Optional.ofNullable(computer).isPresent()) {
            return false;
        }

        return true;
    }

    /**
     * Player 들이 가진 Number List 가 null 인지 체크함
     * @return Null 이 아닐 경우 true 반환
     * */
    private Boolean isNotNullPlayersNumber(List<Integer> numbers) {
        if (!Optional.ofNullable(numbers).isPresent()) {
            return false;
        }

        if (numbers.size() == 0) {
            return false;
        }

        return true;
    }

    /**
     * Player 의 모든 요소가 Computer 에 모두 포함 되어있는지 체크
     * @return 모든 요소가 포함 되어 있는 경우에만 true 반환
     * */
    private Boolean isContainsAll(Player player, ComputerPlayer computer) {
        boolean isContainsAll = true;
        int i = 0;

        while(isContainsAll && i < player.getSize()) {
            isContainsAll = computer.getNumbers().contains(player.getNumbers().get(i));

            i++;
        }

        return isContainsAll;
    }

    /**
     * Player 의 모든 요소가 Computer 에 모두 포함 되어 있지 않은지 체크
     * @return 모든 요소가 포함 되어 있지 않다면 true 반환
     * */
    private Boolean isNotContainsAll(Player player, ComputerPlayer computer) {
        boolean checkIsNotContainsAll = true;
        int i = 0;

        while(checkIsNotContainsAll && i < player.getSize()) {
            checkIsNotContainsAll = !computer.getNumbers().contains(player.getNumbers().get(i));

            i++;
        }

        return checkIsNotContainsAll;
    }

    /**
     * Player 의 요소와 Computer 의 요소가 같은 위치에 있는지 체크
     * @return Player 의 요소와 Computer 의 요소가 같은 위치에 있다면 1 반환
     * */
    private Integer checkSamePlaceCount(Integer playerNumber, Integer computerNumber) {
        if (computerNumber.equals(playerNumber)) {
            return 1;
        }

        return 0;
    }

    /**
     * Player 의 요소가 Computer 의 요소 중 한 개라도 포함 된다면 true 반환
     * */
    private Boolean isContainsAtLeastOne(Player player, ComputerPlayer computer) {
        int i = 0;
        boolean isContainsAtLeastOne = true;

        while(isContainsAtLeastOne && i < computer.getSize()) {
            isContainsAtLeastOne = !computer.getNumbers().contains(player.getNumbers().get(i));

            i++;
        }

        return !isContainsAtLeastOne;
    }

    /**
     * Player 의 모든 요소가 Computer 의 모든 요소와 다르다면 true 반환
     * */
    private Boolean isNotEqualsAll(Player player, ComputerPlayer computer) {
        int i = 0;
        boolean checkNotEquals = true;

        while (checkNotEquals && i < computer.getSize()) {
            checkNotEquals = !computer.getNumbers().get(i).equals(player.getNumbers().get(i));

            i++;
        }

        return checkNotEquals;
    }

    /**
     * Player 의 요소 가 Computer 의 요소에 몇 개나 포함 되어 있는지 그 개수를 반환
     * 만약 동일한 자리에 위치할 경우 갯수는 0 으로 함
     * @return Computer 에 포함된 Player 요소의 개수
     * */
    private Integer checkOnlyContainsCount(Player player, ComputerPlayer computer) {
        int getCount = 0;

        for (int i = 0; i < computer.getSize(); i++) {
            boolean isNotSamePlace = !isSamePlace(player.getNumbers().get(i), computer.getNumbers().get(i));
            boolean isContainAtLeastOne = computer.getNumbers().contains(player.getNumbers().get(i));

            getCount = (isContainAtLeastOne && isNotSamePlace)
                    ? getCount + 1
                    : getCount;
        }

        return getCount;
    }

    private Boolean isSamePlace(Integer playerNumber, Integer computerNumber) {
        return checkSamePlaceCount(playerNumber, computerNumber) != 0;
    }

}
