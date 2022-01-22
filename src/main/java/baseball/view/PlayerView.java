package baseball.view;

import baseball.model.ComputerPlayer;
import baseball.model.Player;
import nextstep.utils.Console;

public class PlayerView {

    public Player initializePlayer() {
        System.out.println("1부터 9까지의 중복되지 않은 숫자 세 개를 연속하여 입력해주세요.");

        String consoleInput = Console.readLine();
        Player player = new Player(consoleInput);

        while (player.getSize() == 0) {
            System.out.println("[ERROR] 유효하지 않은 입력입니다. 다시 입력해주세요.");

            String retry = Console.readLine();
            player = new Player(retry);
        }

        return player;
    }

    public ComputerPlayer initializeComputer() {
        return new ComputerPlayer();
    }

}
