public class ComparatorResult {

    private final Winner winner;
    private final HandType handTypeP1;
    private final HandType handTypeP2;

    public ComparatorResult(Winner winner, HandType handTypeP1, HandType handTypeP2) {

        this.winner = winner;
        this.handTypeP1 = handTypeP1;
        this.handTypeP2 = handTypeP2;
    }

    public Winner getWinner() {
        return winner;
    }

    public HandType getHandTypeP1() {
        return handTypeP1;
    }

    public HandType getHandTypeP2() {
        return handTypeP2;
    }
}
