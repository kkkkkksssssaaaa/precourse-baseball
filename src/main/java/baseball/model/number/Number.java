package baseball.model.number;

import baseball.model.number.exception.NumberException;

public class Number {

    private final int num;

    public static final Integer START_IDX = 1;
    public static final Integer END_IDX = 9;

    private Number(int num) {
        this.num = num;

        if (!isWithinRange()) {
            throw NumberException.notWithinRangeNumber(num);
        }
    }

    public static Number of(int num) {
        return new Number(num);
    }

    @Override
    public boolean equals(Object number) {
        if (!(number instanceof Number)) {
            return false;
        }

        return this.get() == ((Number) number).get();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.num);
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