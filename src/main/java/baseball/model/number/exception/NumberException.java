package baseball.model.number.exception;

import baseball.model.number.Number;

public class NumberException {

    private final String message;

    private static final String WITHIN_RANGE_NUMBER_MESSAGE =
            "[%d] is not within range. " +
            "Please check range, over than %d " +
            "and under than %d";

    private NumberException(String message, Object... args) {
        this.message = setMessage(message, args);
    }

    public static IllegalArgumentException notWithinRangeNumber(int num) {
        NumberException instance =
                new NumberException(WITHIN_RANGE_NUMBER_MESSAGE, num, Number.START_IDX, Number.END_IDX);
        throw new IllegalArgumentException(instance.getMessage());
    }

    private String getMessage() {
        return this.message;
    }

    private String setMessage(String messageTemplate, Object... args) {
        String exceptionMessage = String.format(messageTemplate, args);
        System.err.println(exceptionMessage);

        return exceptionMessage;
    }

}
