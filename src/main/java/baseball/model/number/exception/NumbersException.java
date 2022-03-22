package baseball.model.number.exception;

public class NumbersException {

    private final String message;

    private static final String WRONG_COLLECTION_SIZE =
            "This collection size is must be %d.";

    private NumbersException(String message, Object... args) {
        this.message = setMessage(message, args);
    }

    public static IllegalArgumentException wrongCollectionSize(int size) {
        NumbersException instance =
                new NumbersException(WRONG_COLLECTION_SIZE, size);
        return new IllegalArgumentException(instance.getMessage());
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
