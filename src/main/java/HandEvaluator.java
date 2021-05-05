import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HandEvaluator implements IHandEvaluator {
    @Override
    public EvaluationResult Evaluate(List<Card> playerHand) {
        //sort cards by rank;
        var sortedHand = playerHand
                .stream()
                .sorted(Comparator.comparing(c->c.getRank().getRank()))
                .collect(Collectors.toList());

        boolean inOrder = isCardsInOrder(sortedHand);
        boolean allCardsHaveSameSuit = isCardsHaveSameSuit(sortedHand);


        if (inOrder)
        {
            if (allCardsHaveSameSuit)
            {
                boolean minCardIs10 = sortedHand.get(0).getRank() == CardRank.RANK_10;
                if (minCardIs10) {
                    return new EvaluationResult(HandType.ROYAL_FLUSH,null);
                } else {
                    return new EvaluationResult(HandType.STRAIGHT_FLUSH, List.of(sortedHand.get(0)));
                }
            }
            else
            {
                return new EvaluationResult(HandType.STRAIGHT,List.of(sortedHand.get(0)));
            }
        }
        else
        {
            var cardMatrix = groupCardByRank(sortedHand);

            switch (cardMatrix.size())
            {
                case 0:
                    if(allCardsHaveSameSuit){
                        return new EvaluationResult(HandType.FLUSH, sortedHand);
                    }
                    else{
                        return new EvaluationResult(HandType.HIGH_CARD, sortedHand);
                    }
                case 1:
                    var cmSize = cardMatrix.get(0).size();
                    if (cmSize == 4) {
                        return new EvaluationResult(HandType.FOUR_OF_A_KIND,List.of(cardMatrix.get(0).get(0)));
                    }
                    if (cmSize == 3) {
                        return new EvaluationResult(HandType.THREE_OF_A_KIND,List.of(cardMatrix.get(0).get(0)));
                    }
                    if (cmSize == 2) {
                        return new EvaluationResult(HandType.ONE_PAIR,List.of(cardMatrix.get(0).get(0)));
                    }
                    break ;
                case 2:
                    if (cardMatrix.get(0).size() == 2  && cardMatrix.get(1).size() == 2 ) {
                        return new EvaluationResult(HandType.TWO_PAIR,List.of(cardMatrix.get(0).get(0)
                                ,cardMatrix.get(1).get(0)));
                    }
                    else {
                        var resultFH = cardMatrix.get(0).size() == 3
                                ? List.of(cardMatrix.get(0).get(0), cardMatrix.get(1).get(0))
                                : List.of(cardMatrix.get(1).get(0), cardMatrix.get(0).get(0));
                        return new EvaluationResult(HandType.FULL_HOUSE, resultFH);
                    }

                default:
                    throw new IllegalArgumentException("something wrong with grouping");

            }



        }
        throw new IllegalArgumentException("something wrong");
    }

    private List<List<Card>> groupCardByRank(List<Card> playerHand) {
       return  playerHand
               .stream()
               .collect(Collectors.groupingBy(Card::getRankNumber))
               .values().stream().filter(c->c.size() > 1)
               .map(ArrayList::new)
               .collect(Collectors.toList());
    }

    private boolean isCardsInOrder(List<Card> playerHand) {

        var card0RankNumber = playerHand.get(0).getRankNumber();

        if (playerHand.get(1).getRankNumber() == card0RankNumber + 1
                && playerHand.get(2).getRankNumber() == card0RankNumber + 2
                && playerHand.get(3).getRankNumber() == card0RankNumber + 3
                && playerHand.get(4).getRankNumber() == card0RankNumber + 4) {
            return true;
        }

        return playerHand.get(0).getRank() == CardRank.RANK_2
                && playerHand.get(1).getRank() == CardRank.RANK_3
                && playerHand.get(2).getRank() == CardRank.RANK_4
                && playerHand.get(3).getRank() == CardRank.RANK_5
                && playerHand.get(4).getRank() == CardRank.RANK_A;
    }

    private boolean isCardsHaveSameSuit(List<Card> playerHand) {

        for (int i = 1, playerHandSize = playerHand.size(); i < playerHandSize; i++) {

            if (playerHand.get(i-1).getSuit() == playerHand.get(i).getSuit()) {
                continue;
            }
            return false;
        }

        return true;
    }

}
