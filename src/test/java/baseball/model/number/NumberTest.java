package baseball.model.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @Nested
    @DisplayName("Failed Test")
    class FailedTest{

        @Test
        void 범위에_포함되지_않는_숫자를_입력하면_IllegalArgumentException을_던진다() {
            assertThrows(IllegalArgumentException.class, () -> Number.of(-1));
        }

    }

    @Nested
    @DisplayName("Pass Test")
    class PassTest{

        @Test
        void 최소값인_숫자를_입력하면_예외가_반환되지_않는다() {
            assertDoesNotThrow(() -> Number.of(1));
        }

        @Test
        void 최대값인_숫자를_입력하면_예외가_반환되지_않는다() {
            assertDoesNotThrow(() -> Number.of(9));
        }

        @Test
        void 범위내_숫자를_입력하면_예외가_반환되지_않는다() {
            assertDoesNotThrow(() -> Number.of(5));
        }

        @Test
        void 같은_값을_가진다면_동등성을_보장한다() {
            Number compareLeft = Number.of(5);
            Number compareRight = Number.of(5);

            assertEquals(compareLeft.hashCode(), compareRight.hashCode());
            assertEquals(compareLeft, compareRight);
        }

    }

}