package baseball.model.number;

import baseball.model.number.exception.NumberException;

public class Number {

    private final int num;

    public static final Integer START_IDX = 1;
    public static final Integer END_IDX = 9;

    private Number(int num) {
        if (!isWithinRange()) {
            throw NumberException.notWithinRangeNumber(num);
        }

        this.num = num;
    }

    public static Number set(int num) {
        return new Number(num);
    }

    public boolean equals(Number number) {
        return this.get() == number.get();
    }

    private int get() {
        return this.num;
    }

    private boolean isWithinRange() {
        return isOverThanStart() && isUnderThanEnd();
    }

    private boolean isOverThanStart() {
        return this.num >= START_IDX;
    }

    private boolean isUnderThanEnd() {
        return this.num <= END_IDX;
    }

}