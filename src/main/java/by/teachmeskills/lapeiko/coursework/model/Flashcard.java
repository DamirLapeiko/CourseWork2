package by.teachmeskills.lapeiko.coursework.model;

public class Flashcard {
    private final long id;
    private final String name;

    private final long learnedCards;
    private final long totalCardsCount;

    public Flashcard(long id, String name, long learnedCards, long totalCardsCount) {
        this.id = id;
        this.name = name;
        this.learnedCards = learnedCards;
        this.totalCardsCount = totalCardsCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getLearnedCards() {
        return learnedCards;
    }

    public long getTotalCardsCount() {
        return totalCardsCount;
    }
}
