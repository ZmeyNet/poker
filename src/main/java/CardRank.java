public enum CardRank {
    RANK_2(2,"2"),
    RANK_3(3,"3"),
    RANK_4(4,"4"),
    RANK_5(5,"5"),
    RANK_6(6,"6"),
    RANK_7(7,"7"),
    RANK_8(8,"8"),
    RANK_9(9,"9"),
    RANK_10(10,"10"),
    RANK_J(11,"Jack"),
    RANK_Q(12,"Queen"),
    RANK_K(13,"King"),
    RANK_A(14,"Ace");

    private final int rank;
    private final String description;

    CardRank(int rank, String description) {

        this.rank = rank;
        this.description = description;
    }
    public int getRank(){
        return rank;
    }

    public String getDescription() {
        return description;
    }
}
