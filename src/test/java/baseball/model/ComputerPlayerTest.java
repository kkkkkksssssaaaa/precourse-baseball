package baseball.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void 중복되지_않은_숫자로_이루어진_배열을_리턴한다() {
        int testCount = 1000;

        for (int i = 0; i < testCount; i++) {
            ComputerPlayer number = new ComputerPlayer();

            assertEquals(number.getNumbers().size(), 3);

            System.out.println("print number : " + number.getNumbers());
        }
    }


    @Test
    void setNumbersOnlyTest를_호출하여_컬렉션을_초기화_할_수_있다() {
        ComputerPlayer computer = new ComputerPlayer();

        List<Integer> numbers = new ArrayList<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        computer.setNumbersOnlyTest(numbers);

        assertEquals(computer.getSize(), computer.maxSize);
        assertEquals(computer.getNumbers(), numbers);
    }

}