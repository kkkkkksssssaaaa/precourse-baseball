package baseball.model;

public enum GameStatus {

    RESTART(1),
    END_GAME(2),
    PLAYING(0),
    NOTHING(3)
    ;

    private final int value;

    GameStatus(int value) {
        this.value = value;
    }

    public static GameStatus findStatus(int input) {
        if (input == 1) {
            return RESTART;
        }

        if (input == 2) {
            return END_GAME;
        }

        return NOTHING;
    }

    public int getValue() {
        return this.value;
    }

}
