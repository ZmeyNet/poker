public enum HandType {
    ROYAL_FLUSH(10,"Royal flush"),
    STRAIGHT_FLUSH(9,"Straight flush"),
    FOUR_OF_A_KIND(8,"Four of a kind"),
    FULL_HOUSE(7,"Full house"),
    FLUSH(6,"Flush"),
    STRAIGHT(5,"Straight"),
    THREE_OF_A_KIND(4,"Three of a kind"),
    TWO_PAIR(3,"Two pair"),
    ONE_PAIR(2,"One pair"),
    HIGH_CARD(1,"High card"),
    ;

    private final int power;
    private final String description;

    HandType(int power, String description) {

        this.power = power;
        this.description = description;
    }

    public int getPower() {
        return power;
    }

    public String getDescription() {
        return description;
    }
}
