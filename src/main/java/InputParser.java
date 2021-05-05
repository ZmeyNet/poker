import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser implements IInputParser {

    private final int INPUT_PARAMETERS_COUNT = 2;
    private final int MAX_CARD_IN_HAND = 5;

    private final String PATTERN_CARD = "(([23456789JQKA]|(10))([SHDC]))";
    private final Pattern GROUP_PATTERN = Pattern.compile(PATTERN_CARD,Pattern.CASE_INSENSITIVE);

    private final Predicate<String> MATCH_PATTERN = Pattern.compile(PATTERN_CARD +"{"+MAX_CARD_IN_HAND+"}")
            .asMatchPredicate();

    private final ICardConvertor cardConvertor;

    public InputParser(ICardConvertor cardConvertor) {
        this.cardConvertor = cardConvertor;
    }

    private boolean isInputValid(String[] cmdLineInputs,HashSet<String> cardsTextP1,HashSet<String> cardsTextP2) {

        if (cmdLineInputs == null
                || cmdLineInputs.length != INPUT_PARAMETERS_COUNT
                || cmdLineInputs[0] == null
                || cmdLineInputs[1] == null
                || cmdLineInputs[0].equals("")
                || cmdLineInputs[1].equals("")
                || !MATCH_PATTERN.test(cmdLineInputs[0])
                || !MATCH_PATTERN.test(cmdLineInputs[1]))
        return false;

        var matcherP1 = GROUP_PATTERN.matcher(cmdLineInputs[0]);

        while(matcherP1.find())
        {
            if(!cardsTextP1.add(matcherP1.group())){
                return false;
            }
        }
        var matcherP2 = GROUP_PATTERN.matcher(cmdLineInputs[1]);
        while(matcherP2.find())
        {
            if(!cardsTextP2.add(matcherP2.group())){
                return false;
            }
        }

        return     cardsTextP1.size() == MAX_CARD_IN_HAND
                && cardsTextP2.size() == MAX_CARD_IN_HAND;

    }

    @Override
    public List<List<Card>> getParsedCardsFromInput(String[] cmdLine) {

        HashSet<String> cardsTextP1 = new HashSet<>(MAX_CARD_IN_HAND);
        HashSet<String> cardsTextP2 = new HashSet<>(MAX_CARD_IN_HAND);

        if(!isInputValid(cmdLine,cardsTextP1,cardsTextP2)){
            return null;
        }

        List<List<Card>> parsedResult = new ArrayList<>(INPUT_PARAMETERS_COUNT);

        parsedResult.add(cardsTextP1.stream()
                .map(cardConvertor::fromText)
                .collect(Collectors.toList()));
        parsedResult.add(cardsTextP2.stream()
                .map(cardConvertor::fromText)
                .collect(Collectors.toList()));
        return parsedResult;
    }

}
