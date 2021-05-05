import java.util.List;

public class EvaluationResult {

    private final HandType handType;
    private final List<Card> extendedInfo;

    public EvaluationResult(HandType handType, List<Card> extendedInfo)
    {
        this.handType = handType;
        this.extendedInfo = extendedInfo;
    }

    public HandType getHandType(){
        return handType;
    }


    public List<Card> getExtendedInfo() {
        return extendedInfo;
    }
/*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EvaluationResult that = (EvaluationResult) obj;


        this.extendedInfo == that.extendedInfo

        return handType == that.handType &&
                ;
    }*/
}
