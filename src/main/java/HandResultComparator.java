public class HandResultComparator implements IHandResultComparator {
    @Override
    public ComparatorResult CompareHands(EvaluationResult resultFirst, EvaluationResult resultSecond) {
        if (resultFirst.getHandType().getPower() > resultSecond.getHandType().getPower()) {
            return new ComparatorResult(Winner.PLAYER1,resultFirst.getHandType(),resultSecond.getHandType());
        }
        if (resultFirst.getHandType().getPower() < resultSecond.getHandType().getPower()) {
            return new ComparatorResult(Winner.PLAYER2,resultFirst.getHandType(),resultSecond.getHandType());
        }
        // equal
        //verify ext info sizes  should be equal

        if (resultFirst.getExtendedInfo() == null) {
            return new ComparatorResult(Winner.DRAW,resultFirst.getHandType(),resultSecond.getHandType());
        }

        for (int i = 0; i < resultFirst.getExtendedInfo().size(); i++) {
            var infoP1 = resultFirst.getExtendedInfo().get(i);
            var infoP2 = resultSecond.getExtendedInfo().get(i);

            if (infoP1.getRankNumber() > infoP2.getRankNumber()) {
                return new ComparatorResult(Winner.PLAYER1,resultFirst.getHandType(),resultSecond.getHandType());
            }
            if (infoP1.getRankNumber() < infoP2.getRankNumber()) {
                return new ComparatorResult(Winner.PLAYER2,resultFirst.getHandType(),resultSecond.getHandType());
            }
        }

        //DRAW
        return new ComparatorResult(Winner.DRAW,resultFirst.getHandType(),resultSecond.getHandType());
    }
}
