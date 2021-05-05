public enum CardSuit {
    SPADE("♠"),
    HEART("♥"),
    DIAMOND("♦"),
    CLUB("♣"),
    ;

    @Override
    public String toString() {
        return suitSymbol;
    }

    private final String suitSymbol;

    CardSuit(String c) {
        suitSymbol = c;
    }
}
