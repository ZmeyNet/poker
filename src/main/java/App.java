import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private final static int ERROR_EXIT_CODE = -1;

    public static void main(String[] args) {

        try {

            IHandResultComparator handResultComparator = new HandResultComparator();

            ICardConvertor converter = new CardConvertor();
            IInputParser inputParser = new InputParser(converter);

            List<List<Card>> cards = inputParser.getParsedCardsFromInput(args);
            if (cards == null) {
                showUsage();
                System.exit(ERROR_EXIT_CODE);
            } else {

                IHandEvaluator evaluator = new HandEvaluator();

                var result = handResultComparator.CompareHands(
                        evaluator.Evaluate(cards.get(0)),
                        evaluator.Evaluate(cards.get(1)));

                printResult(result, cards);
            }

        } catch (Exception e) {

            System.out.println("There where an error during execution");
            System.out.println(e.getMessage());
        }
    }

    private static void printResult(ComparatorResult result, List<List<Card>> initialCards) {

        switch (result.getWinner()) {
            case DRAW -> {
                System.out.println("IT IS DRAW ! :( both player have equal hands like "
                        + result.getHandTypeP1().getDescription());
            }
            case PLAYER1 -> {
                System.out.println(MessageFormat.format(
                        "PLAYER 1 is Winner with hand [{0}] , PLAYER 2 only had [{1}]",
                        result.getHandTypeP1().getDescription(),
                        result.getHandTypeP2().getDescription())) ;
            }
            case PLAYER2 -> {
                System.out.println(MessageFormat.format("PLAYER 2 is Winner with hand [{0}], PLAYER 1  only had [{1}]",
                        result.getHandTypeP2().getDescription(),
                        result.getHandTypeP1().getDescription()));
            }

        }
        System.out.println("Player 1 cards ");
        System.out.println(initialCards.get(0).stream()
                .map( c -> c.getRank().getDescription() +  c.getSuit().toString())
                .collect( Collectors.joining( "," )));

        System.out.println("Player 2 cards ");
        System.out.println(initialCards.get(1).stream()
                .map( c -> c.getRank().getDescription() +  c.getSuit().toString())
                .collect( Collectors.joining( "," )));
    }

    private static void showUsage()
    {
        System.out.println("Please input two \"poker hand\" separated by space");
        System.out.println("Allowed symbols for card are [2,3,4,5,6,7,8,9,10,J,Q,K,A]");
        System.out.println("And for suits [S -> ♠, H -> ♡, D->♢, C -> ♣ ]");
    }
}
