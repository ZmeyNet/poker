public class CardConvertor implements ICardConvertor {

    private final int MIN_CARD_TEXT_SIZE = 2;
    private final int MAX_CARD_TEXT_SIZE = 3;
    @Override
    public Card fromText(String txt) {

        if (txt == null
                || txt.length() < MIN_CARD_TEXT_SIZE
                || txt.length() > MAX_CARD_TEXT_SIZE) {
            throw new IllegalArgumentException("txt");
        }

        CardSuit cardSuits ;

        switch (txt.charAt(txt.length() - 1)) {
            case 'S' -> cardSuits = CardSuit.SPADE;
            case 'H' -> cardSuits = CardSuit.HEART;
            case 'D' -> cardSuits = CardSuit.DIAMOND;
            case 'C' -> cardSuits = CardSuit.CLUB;
            default -> throw new IllegalArgumentException("txt");
        }
        var rank = CardRank.valueOf("RANK_" + txt.substring(0, txt.length() - 1));


        return new Card(rank, cardSuits);

    }
}
