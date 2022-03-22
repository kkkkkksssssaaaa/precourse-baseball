package baseball.model.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {

    @Nested
    @DisplayName("Failed Test")
    class FailedTest {

        @Test
        void 컬렉션의_크기가_지정된_크기보다_크다면_IllegalArgumentException을_던진다() {
            List<Number> numberList =
                    Arrays.asList(
                            Number.of(1),
                            Number.of(2),
                            Number.of(3),
                            Number.of(4));


            assertThrows(
                    IllegalArgumentException.class,
                    () -> Numbers.of(numberList));
        }

        @Test
        void 컬렉션의_크기가_지정된_크기보다_작다면_IllegalArgumentException을_던진다() {
            List<Number> numberList =
                    Arrays.asList(
                            Number.of(1),
                            Number.of(2));


            assertThrows(
                    IllegalArgumentException.class,
                    () -> Numbers.of(numberList));
        }

    }

    @Nested
    @DisplayName("Pass Test")
    class PassTest {

        @Test
        void 중복된_숫자가_있어도_중복되지_않은_숫자를_이용하여_지정된_크기의_컬렉션을_만들_수_있다() {
            List<Number> numberList =
                    Arrays.asList(
                            Number.of(1),
                            Number.of(2),
                            Number.of(3),
                            Number.of(3));

            assertDoesNotThrow(() -> Numbers.of(numberList));
            assertEquals(Numbers.of(numberList).size(), 3);
        }

    }

}