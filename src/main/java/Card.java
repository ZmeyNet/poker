public class Card {

    private final CardRank cardRank;
    private final CardSuit cardSuit;

    public Card(CardRank rank, CardSuit suit){
        this.cardRank = rank;
        this.cardSuit = suit;
    }

    public CardRank getRank() {
        return this.cardRank;
    }


    public int getRankNumber() {
        return cardRank.getRank();
    }


    public CardSuit getSuit() {
        return this.cardSuit;
    }


    public String toString() {
        return getRank().getRank() + getSuit().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card that = (Card) o;
        return cardRank == that.cardRank &&
                cardSuit == that.cardSuit;
    }
}
