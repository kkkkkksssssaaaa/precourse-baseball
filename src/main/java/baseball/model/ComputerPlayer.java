package baseball.model;

import nextstep.utils.Randoms;

import java.util.*;

public class ComputerPlayer extends AbstractPlayer {

    // TODO must modified be numbers variable is `final`
    private List<Integer> numbers = new ArrayList<>();

    public ComputerPlayer() {
        numbers.addAll(initializeNumbers());
    }

    @Override
    public List<Integer> getNumbers() {
        return this.numbers;
    }

    @Override
    public Integer getSize() { return this.numbers.size(); }

    private List<Integer> initializeNumbers() {
        List<Integer> numbers = new ArrayList<>();

        while(numbers.size() < 3) {
            numbers.add(getNotDuplicatedNumber(numbers));
        }

        return numbers;
    }

    private Integer getNotDuplicatedNumber(List<Integer> numbers) {
        Integer randomNumber = Randoms.pickNumberInRange(startIdx, endIdx);

        while(numbers.contains(randomNumber)) {
            randomNumber = Randoms.pickNumberInRange(startIdx, endIdx);
        }

        return randomNumber;
    }

    /**
     * @deprecated
     * [TEST] 테스트 목적 이외에는 사용 불가
     * List<Integer> 를 입력받아 해당 입력 인자를 해당 클래스의 필드로 사용
     */
    public void setNumbersOnlyTest(List<Integer> numbers) {
        this.numbers.clear();
        this.numbers.addAll(numbers);
    }

}
