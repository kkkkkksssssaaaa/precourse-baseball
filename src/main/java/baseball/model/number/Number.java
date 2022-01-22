package baseball.model.number;

import baseball.model.number.exception.NumberException;

public class Number {

    private final int num;

    public static final Integer START_IDX = 1;
    public static final Integer END_IDX = 9;

    private Number(int num) {
        if (!isWithInRange(num)) {
            throw NumberException.notWithinRangeNumber(num);
        }

        this.num = num;
    }

    public static Number set(int num) {
        return new Number(num);
    }

    private boolean isWithInRange(int num) {
        return isOverThanStart(num) && isUnderThanEnd(num);
    }

    private boolean isOverThanStart(int num) {
        return num >= START_IDX;
    }

    private boolean isUnderThanEnd(int num) {
        return num <= END_IDX;
    }

}