package baseball.controller;

import baseball.model.ComputerPlayer;
import baseball.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    ComputerPlayer computer = new ComputerPlayer();
    GameController scoreController = new GameController();

    @Nested
    @DisplayName("낫싱 테스트")
    class NothingTest {

        @Nested
        @DisplayName("Failed 테스트")
        class FailedTest {
            @Test
            void Null을_인자로_받으면_false를_반환한다() {
                Boolean isNothing = scoreController.isNothing(null, null);

                assertFalse(isNothing);
            }

            @Test
            void 인자_중_한_개라도_Null이_포함_되어_있다면_false를_반환한다() {
                Boolean isNothing = scoreController.isNothing(null, computer);

                assertFalse(isNothing);
            }

            @Test
            void 컬렉션의_요소가_비어있어도_false를_반환한다() {
                String playerNumber = "165";

                Player player = new Player(playerNumber);


                ComputerPlayer computer = new ComputerPlayer();
                computer.setNumbersOnlyTest(new ArrayList<>());

                Boolean isNothing = scoreController.isNothing(player, computer);

                assertFalse(isNothing);
            }

            @Test
            void 두_컬렉션을_비교하여_같은_요소가_존재한다면_false를_반환한다() {
                StringBuilder getNumbers = new StringBuilder();

                for (int i = 0; i < computer.getSize(); i++) {
                    getNumbers.append(computer.getNumbers().get(i));
                }

                getNumbers.reverse();

                Player player = new Player(getNumbers.toString());

                Boolean isNothing = scoreController.isNothing(player, computer);

                assertFalse(isNothing);
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "165", "145", "154", "156",
                    "175", "198", "197", "256",
                    "298", "276", "245", "256",
                    "365", "672", "265", "372",
                    "376", "269", "294", "731"
            })
            void 두_컬렉션을_비교하여_한_가지_요소만_같아도_false를_반환한다() {
                ComputerPlayer computer = createNewComputer("123");

                String playerNumber = "165";

                Player player = new Player(playerNumber);

                Boolean isNothing = scoreController.isNothing(player, computer);

                assertFalse(isNothing);
            }
        }

        @Nested
        @DisplayName("Success 테스트")
        class SuccessTest {
            @Test
            void 두_컬렉션을_비교하여_같은_요소가_존재하지_않는다면_true를_반환한다() {
                ComputerPlayer computer = createNewComputer("123");

                String playerNumber = "456";

                Player player = new Player(playerNumber);

                Boolean isNothing = scoreController.isNothing(player, computer);

                assertTrue(isNothing);
            }
        }

    }

    @Nested
    @DisplayName("포볼 테스트")
    class FourBallTest {

        @Nested
        @DisplayName("Failed 테스트")
        class FailedTest {

            @Test
            void Null을_인자로_받으면_false를_반환한다() {
                Boolean isFourBall = scoreController.isFourBall(null, null);

                assertFalse(isFourBall);
            }

            @Test
            void 인자_중_한_개라도_Null이_포함_되어_있다면_false를_반환한다() {
                Boolean isFourBall = scoreController.isFourBall(null, computer);

                assertFalse(isFourBall);
            }

            @Test
            void 컬렉션의_요소가_비어있어도_false를_반환한다() {
                String playerNumber = "165";

                Player player = new Player(playerNumber);


                ComputerPlayer computer = new ComputerPlayer();
                computer.setNumbersOnlyTest(new ArrayList<>());

                Boolean isFourBall = scoreController.isFourBall(player, computer);

                assertFalse(isFourBall);
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "165", "145", "154", "156",
                    "175", "198", "197", "256",
                    "298", "276", "245", "256",
                    "365", "672", "265", "372",
                    "376", "269", "294", "731"
            })
            void 두_컬렉션을_비교하여_한_가지_요소만_같아도_false를_반환한다() {
                ComputerPlayer computer = createNewComputer("123");

                String playerNumber = "165";

                Player player = new Player(playerNumber);

                Boolean isFourBall = scoreController.isFourBall(player, computer);

                assertFalse(isFourBall);
            }

            @Test
            void 두_컬렉션을_비교하여_같은_요소가_존재하지_않는다면_false를_반환한다() {
                ComputerPlayer computer = createNewComputer("123");

                String playerNumber = "456";

                Player player = new Player(playerNumber);

                Boolean isFourBall = scoreController.isFourBall(player, computer);

                assertFalse(isFourBall);
            }

            @ParameterizedTest
            @ValueSource(strings = { "132", "213", "321" })
            void 두_컬렉션을_비교하여_모든_요소를_포함하지만_한_자리만_같아도_false를_반환한다(String input) {
                ComputerPlayer computer = createNewComputer("123");

                Player player = new Player(input);

                Boolean isFourBall = scoreController.isFourBall(player, computer);

                assertFalse(isFourBall);
            }

        }

        @Nested
        @DisplayName("Success 테스트")
        class SuccessTest {
            @ParameterizedTest
            @CsvSource(value = {
                    "123:312", "123:231", "456:564", "456:645",
                    "789:897", "789:978", "243:432", "876:687"
            }, delimiter = ':')
            void 두_컬렉션을_비교하여_모든_요소를_포함하지만_자리가_다른_경우만_true를_반환한다(String playerNumber,
                                                                String computerNumber) {
                ComputerPlayer computer = createNewComputer(computerNumber);

                Player player = new Player(playerNumber);

                Boolean isFourBall = scoreController.isFourBall(player, computer);

                assertTrue(isFourBall);
            }
        }

    }

    @Nested
    @DisplayName("스트라이크 테스트")
    class StrikeTest {

        @Nested
        @DisplayName("Failed 테스트")
        class FailedTest {

            @Test
            void Null을_인자로_받으면_0을_반환한다() {
                Integer getCount = scoreController.getStrikeCount(null, null);

                assertEquals(getCount, 0);
            }

            @Test
            void 인자_중_한_개라도_Null이_포함_되어_있다면_0을_반환한다() {
                Integer getCount = scoreController.getStrikeCount(null, computer);

                assertEquals(getCount, 0);
            }

            @Test
            void 컬렉션의_요소가_비어있어도_0을_반환한다() {
                String playerNumber = "165";

                Player player = new Player(playerNumber);

                ComputerPlayer computer = new ComputerPlayer();
                computer.setNumbersOnlyTest(new ArrayList<>());

                Integer getCount = scoreController.getStrikeCount(player, computer);

                assertEquals(getCount, 0);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "123:456", "123:789", "456:123", "456:789",
                    "789:321", "789:654", "145:987", "615:987"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소가_없다면_0을_반환한다(String playerNumber,
                                                String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getStrikeCount(player, computer);

                assertEquals(getCount, 0);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "345:123", "256:123", "356:123", "124:456",
                    "512:456", "712:137", "412:125", "523:465"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소가_있지만_자리가_틀린_경우_0을_반환한다(String playerNumber,
                                                          String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getStrikeCount(player, computer);

                assertEquals(getCount, 0);
            }

        }

        @Nested
        @DisplayName("Success 테스트")
        class SuccessTest {

            @ParameterizedTest
            @CsvSource(value = {
                    "156:123", "178:123", "524:123", "527:123",
                    "729:123", "563:123", "873:123", "583:123"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소_중_한_개가_같은_값이며_모두_같은_자리에_있는_경우_1을_반환한다(String playerNumber,
                                                                         String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getStrikeCount(player, computer);

                assertEquals(getCount, 1);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "126:123", "173:123", "124:123", "523:123",
                    "723:123", "163:123", "173:123", "623:123"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소_중_한_개가_같은_값이며_모두_같은_자리에_있는_경우_2를_반환한다(String playerNumber,
                                                                         String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getStrikeCount(player, computer);

                assertEquals(getCount, 2);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "123:123", "234:234", "345:345", "534:534",
                    "567:567", "789:789", "987:987", "561:561"
            }, delimiter = ':')
            void 컬렉션의_요소가_모두_중복되며_모두_같은_자리에_있을_경우_3을_반환한다(String playerNumber,
                                                          String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getStrikeCount(player, computer);

                assertEquals(getCount, 3);
            }

        }

    }

    @Nested
    @DisplayName("볼 테스트")
    class BallTest {

        @Nested
        @DisplayName("Failed 테스트")
        class FailedTest {

            @Test
            void Null을_인자로_받으면_0을_반환한다() {
                Integer getCount = scoreController.getBallCount(null, null);

                assertEquals(getCount, 0);
            }

            @Test
            void 인자_중_한_개라도_Null이_포함_되어_있다면_0을_반환한다() {
                Integer getCount = scoreController.getBallCount(null, computer);

                assertEquals(getCount, 0);
            }

            @Test
            void 컬렉션의_요소가_비어있어도_0을_반환한다() {
                String playerNumber = "165";

                Player player = new Player(playerNumber);

                ComputerPlayer computer = new ComputerPlayer();
                computer.setNumbersOnlyTest(new ArrayList<>());

                Integer getCount = scoreController.getBallCount(player, computer);

                assertEquals(getCount, 0);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "123:456", "123:789", "456:123", "456:789",
                    "789:321", "789:654", "145:987", "615:987"

            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소가_없다면_0을_반환한다(String playerNumber,
                                                String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getBallCount(player, computer);

                assertEquals(getCount, 0);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "156:123", "178:123", "524:123", "527:123",
                    "729:123", "563:123", "873:123", "123:123",
                    "234:234", "345:345", "534:534", "567:567",
                    "789:789", "987:987", "781:789", "129:789"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소가_있지만_자리가_같은_경우라도_0을_반환한다(String playerNumber,
                                                                         String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getBallCount(player, computer);

                assertEquals(getCount, 0);
            }

        }

        @Nested
        @DisplayName("Success 테스트")
        class SuccessTest {

            @ParameterizedTest
            @CsvSource(value = {
                    "345:123", "245:123", "254:123", "256:123",
                    "367:123", "368:123", "269:123", "398:123",
                    "365:123", "285:123", "129:234", "173:234"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소_중_한_개가_같은_값이지만_자리가_다를_경우_1을_반환한다(String playerNumber,
                                                                         String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getBallCount(player, computer);

                assertEquals(getCount, 1);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "315:123", "215:123", "214:123", "216:123",
                    "317:123", "318:123", "261:123", "391:123",
                    "362:123", "281:123", "329:234", "143:234"
            }, delimiter = ':')
            void 컬렉션의_요소_중_중복되는_요소_중_두_개가_같은_값이지만_자리가_모두_다를_경우_2를_반환한다(String playerNumber,
                                                                         String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getBallCount(player, computer);

                assertEquals(getCount, 2);
            }

            @ParameterizedTest
            @CsvSource(value = {
                    "312:123", "423:234", "534:345", "453:534",
                    "756:567", "978:789", "798:987", "156:561"
            }, delimiter = ':')
            void 컬렉션의_요소가_모두_중복되지만_자리가_모두_다를_경우_3을_반환한다(String playerNumber,
                                                          String computerNumber) {
                Player player = new Player(playerNumber);
                ComputerPlayer computer = createNewComputer(computerNumber);

                Integer getCount = scoreController.getBallCount(player, computer);

                assertEquals(getCount, 3);
            }

        }

    }

    private ComputerPlayer createNewComputer(String numberString) {
        ComputerPlayer computer = new ComputerPlayer();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < numberString.length(); i++) {
            numbers.add(Integer.parseInt(numberString.substring(i, (i + 1))));
        }

        computer.setNumbersOnlyTest(numbers);

        return computer;
    }

}