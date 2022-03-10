package baseball.model.number.exception;

import baseball.model.number.Number;

public class NumbersException {

    private final String message;

    private static final String INDEX_OUTBOUND_MESSAGE =
            "This collection cannot be more than %d in size.";

    private static final String DUPLICATE_NUMBER =
            "This collection contains duplicate number.";

    private NumbersException(String message, Object... args) {
        this.message = setMessage(message, args);
    }

    public static IndexOutOfBoundsException indexOutOfBoundsException(int size) {
        NumbersException instance =
                new NumbersException(INDEX_OUTBOUND_MESSAGE, size);
        throw new IndexOutOfBoundsException(instance.getMessage());
    }

    public static IllegalArgumentException duplicateNumber() {
        NumbersException instance =
                new NumbersException(DUPLICATE_NUMBER);
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
