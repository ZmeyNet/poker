import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CardConvertorTest {

    CardConvertor cct = new CardConvertor();

    @ParameterizedTest()
    @MethodSource("validCards")
    void convertFromText(String cardText,Card cardExpected) {

        var result = cct.fromText(cardText);
        assertEquals(cardExpected, result);
    }

    @ParameterizedTest()
    @MethodSource("invalidCards")
    void convertFromInvalidText(String cardText) {

        assertThrows(IllegalArgumentException.class, ()->cct.fromText(cardText));
    }

    private static Stream<Arguments> invalidCards() {
            return Stream.of(
                    Arguments.of("XC"),
                    Arguments.of("10"),
                    Arguments.of("__"),
                    Arguments.of("qwerty"),
                    Arguments.of("3c"),
                    Arguments.of("4d"),
                    Arguments.of(""),
                    Arguments.of((String)null)

            );
    }

        private static Stream<Arguments> validCards() {
            return Stream.of(
                    Arguments.of("AC", new Card(CardRank.RANK_A, CardSuit.CLUB)),
                    Arguments.of("AD", new Card(CardRank.RANK_A, CardSuit.DIAMOND)),
                    Arguments.of("AH", new Card(CardRank.RANK_A, CardSuit.HEART)),
                    Arguments.of("AS", new Card(CardRank.RANK_A, CardSuit.SPADE)),
                    Arguments.of("3C", new Card(CardRank.RANK_3, CardSuit.CLUB)),
                    Arguments.of("4D", new Card(CardRank.RANK_4, CardSuit.DIAMOND)),
                    Arguments.of("5H", new Card(CardRank.RANK_5, CardSuit.HEART)),
                    Arguments.of("6S", new Card(CardRank.RANK_6, CardSuit.SPADE)),
                    Arguments.of("7S", new Card(CardRank.RANK_7, CardSuit.SPADE)),
                    Arguments.of("8S", new Card(CardRank.RANK_8, CardSuit.SPADE)),
                    Arguments.of("9S", new Card(CardRank.RANK_9, CardSuit.SPADE)),
                    Arguments.of("10S", new Card(CardRank.RANK_10, CardSuit.SPADE)),
                    Arguments.of("JC", new Card(CardRank.RANK_J, CardSuit.CLUB)),
                    Arguments.of("QC", new Card(CardRank.RANK_Q, CardSuit.CLUB)),
                    Arguments.of("KC", new Card(CardRank.RANK_K, CardSuit.CLUB))
            );
    }
}