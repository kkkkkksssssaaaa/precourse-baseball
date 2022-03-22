package baseball.model.number;

import baseball.model.number.exception.NumbersException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Numbers {

    private static final int SIZE = 3;
    private final Set<Number> numbers;

    private Numbers(List<Number> numbers) {
        this.numbers = toSet(numbers);
    }

    public static Numbers of(List<Number> numbers) {
        return new Numbers(numbers);
    }

    public int size() {
        return this.numbers.size();
    }

    private Set<Number> toSet(List<Number> numbers) {
        Set<Number> numberSet = new HashSet<>(numbers);

        if (numberSet.size() != SIZE) {
            throw NumbersException.wrongCollectionSize(SIZE);
        }

        return numberSet;
    }

}
