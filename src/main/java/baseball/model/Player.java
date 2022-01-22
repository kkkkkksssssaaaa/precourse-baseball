package baseball.model;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Player extends AbstractPlayer {

    // TODO must modified be numbers variable is `final`
    private List<Integer> numbers = new ArrayList<>();

    public Player(String consoleInput) {
        numbers.addAll(initializeNumbers(consoleInput));
    }

    @Override
    public List<Integer> getNumbers() { return this.numbers; }

    @Override
    public Integer getSize() { return this.numbers.size(); }

    private List<Integer> initializeNumbers(String consoleInput) {
        Boolean isInteger = isIntegerString(consoleInput);

        if (isInteger) {
            return toList(consoleInput);
        }

        return new ArrayList<Integer>();
    }

    private List<Integer> toList(String consoleInput) {
        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < consoleInput.length(); i++) {
            Integer toInteger = Integer.valueOf(consoleInput.substring(i, (i + 1)));
            output.add(toInteger);
        }

        return checkSizeAfterReturn(output);
    }

    private Boolean isIntegerString(String input) {
        try {
            Integer toInteger = Integer.valueOf(input);

            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private List<Integer> checkSizeAfterReturn(List<Integer> input) {
        Set<Integer> toSet = new HashSet<>(checkValidValueAfterReturn(input));

        if (toSet.size() == maxSize) {
            return input;
        }

        return new ArrayList<Integer>();
    }

    private List<Integer> checkValidValueAfterReturn(List<Integer> input) {
        if (input.contains(0)) {
            return new ArrayList<Integer>();
        }

        return input;
    }

}
