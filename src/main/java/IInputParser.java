import java.util.List;

public interface IInputParser {
    List<List<Card>> getParsedCardsFromInput(String[] cmdLine);
}
