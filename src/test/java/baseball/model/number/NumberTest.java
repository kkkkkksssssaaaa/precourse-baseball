package baseball.model.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @Nested
    @DisplayName("Failed Case")
    class FailedCase {

        @Test
        void 범위에_포함되지_않는_숫자를_입력하면_IllegalArgumentException을_던진다() {
            assertThrows(IllegalArgumentException.class, () -> Number.of(-1));
        }

    }

    @Nested
    @DisplayName("Pass Case")
    class PassCase {

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
        void 같은_값을_가지면_equalsTrue_를_반환한다() {
            Number compareLeft = Number.of(5);
            Number compareRight = Number.of(5);

            assertTrue(
                    compareLeft.equals(compareRight));
        }

    }

}