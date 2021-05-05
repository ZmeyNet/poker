import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class HandEvaluatorTest {

    HandEvaluator handEvaluator = new HandEvaluator();

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("validHands")
    void evaluateValid(@SuppressWarnings("unused") String testName, List<Card> cards,EvaluationResult expectedResult) {

        var result = handEvaluator.Evaluate(cards);

        Assertions.assertEquals(result.getHandType(), expectedResult.getHandType());
        Assertions.assertEquals(result.getExtendedInfo(), expectedResult.getExtendedInfo());

    }
    private static Stream<Arguments> validHands() {
        CardConvertor cc = new CardConvertor();
        return Stream.of(
                Arguments.of("Royal flush",
                        List.of(
                                cc.fromText("AH"),
                                cc.fromText("KH"),
                                cc.fromText("JH"),
                                cc.fromText("QH"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.ROYAL_FLUSH, null)),
                Arguments.of("Straight flush",
                        List.of(
                                cc.fromText("7H"),
                                cc.fromText("6H"),
                                cc.fromText("8H"),
                                cc.fromText("9H"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.STRAIGHT_FLUSH, List.of(cc.fromText("6H")))),
                Arguments.of("Straight flush Lowest",
                        List.of(
                                cc.fromText("2H"),
                                cc.fromText("AH"),
                                cc.fromText("3H"),
                                cc.fromText("4H"),
                                cc.fromText("5H")
                        ), new EvaluationResult(HandType.STRAIGHT_FLUSH, List.of(cc.fromText("2H")))),
                Arguments.of("Four of a kind",
                        List.of(
                                cc.fromText("7H"),
                                cc.fromText("7S"),
                                cc.fromText("7C"),
                                cc.fromText("7D"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.FOUR_OF_A_KIND,
                                List.of(cc.fromText("7H")))),
                Arguments.of("Full house",
                        List.of(
                                cc.fromText("7H"),
                                cc.fromText("7S"),
                                cc.fromText("7C"),
                                cc.fromText("10D"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.FULL_HOUSE,
                                List.of(
                                        cc.fromText("7H"),
                                        cc.fromText("10D")))),
                Arguments.of("Flush",
                        List.of(
                                cc.fromText("7H"),
                                cc.fromText("9H"),
                                cc.fromText("JH"),
                                cc.fromText("2H"),
                                cc.fromText("5H")
                        ), new EvaluationResult(HandType.FLUSH,
                                List.of(
                                        cc.fromText("2H"),
                                        cc.fromText("5H"),
                                        cc.fromText("7H"),
                                        cc.fromText("9H"),
                                        cc.fromText("JH")
                                ))),
                Arguments.of("Straight",
                        List.of(
                                cc.fromText("7H"),
                                cc.fromText("6S"),
                                cc.fromText("8H"),
                                cc.fromText("9S"),
                                cc.fromText("10D")
                        ), new EvaluationResult(HandType.STRAIGHT, List.of(cc.fromText("6S")))),
                Arguments.of("Three of a kind",
                        List.of(
                                cc.fromText("8H"),
                                cc.fromText("8S"),
                                cc.fromText("8C"),
                                cc.fromText("9D"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.THREE_OF_A_KIND, List.of(cc.fromText("8H")))),
                Arguments.of("Two pair",
                        List.of(
                                cc.fromText("8H"),
                                cc.fromText("8S"),
                                cc.fromText("9C"),
                                cc.fromText("9D"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.TWO_PAIR, List.of(
                                cc.fromText("8H"),
                                cc.fromText("9C")))),
                Arguments.of("One pair",
                        List.of(
                                cc.fromText("8H"),
                                cc.fromText("8S"),
                                cc.fromText("2C"),
                                cc.fromText("9D"),
                                cc.fromText("10H")
                        ), new EvaluationResult(HandType.ONE_PAIR, List.of(cc.fromText("8H")))),
                Arguments.of("High card",
                        List.of(
                                cc.fromText("2H"),
                                cc.fromText("4S"),
                                cc.fromText("8C"),
                                cc.fromText("9D"),
                                cc.fromText("JH")
                        ), new EvaluationResult(HandType.HIGH_CARD,
                                List.of(
                                        cc.fromText("2H"),
                                        cc.fromText("4S"),
                                        cc.fromText("8C"),
                                        cc.fromText("9D"),
                                        cc.fromText("JH")
                                )))
        );
    }
}