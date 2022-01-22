package baseball.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = { "122", "233", "111", "333", "000", "666", "166", "545555" })
    void 중복된_숫자인_경우_빈_ArrayList를_반환한다(String input) {
        Player player = new Player(input);

        assertEquals(player.getSize(), 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "123asdf", "", "55555555555555dfgsdfgsdfg", "al;khfkjalher", "'", "!$(*)@^IOUAETYO", "ahfklaeh5rakjsrh", "012" })
    void 허용되지_않은_문자를_입력하면_빈_ArrayList를_반환한다(String input) {
        Player player = new Player(input);

        assertEquals(player.getSize(), 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "123", "531", "724", "918", "854", "721" })
    void 중복되지_않은_1부터_9까지의_숫자를_입력하면_ArrayList로_변환되어_반환한다(String input) {
        Player player = new Player(input);

        assertEquals(player.getSize(), player.maxSize);
    }

}