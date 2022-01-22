package baseball.model.number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @Test
    void 범위에_포함되지_않는_숫자를_입력하면_IllegalArgumentException을_반환한다() {
        assertThrows(IllegalArgumentException.class, () -> Number.set(-1));
    }

    @Test
    void 최소값인_숫자를_입력하면_예외가_반환되지_않는다() {
        assertDoesNotThrow(() -> Number.set(1));
    }

    @Test
    void 최대값인_숫자를_입력하면_예외가_반환되지_않는다() {
        assertDoesNotThrow(() -> Number.set(9));
    }

    @Test
    void 범위내_숫자를_입력하면_예외가_반환되지_않는다() {
        assertDoesNotThrow(() -> Number.set(5));
    }

}