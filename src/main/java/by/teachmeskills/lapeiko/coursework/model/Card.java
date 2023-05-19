package by.teachmeskills.lapeiko.coursework.model;

public class Card {
    private final long id;
    private final String question;
    private final String answer;
    private final boolean isLearned;

    public Card(long id, String question, String answer, boolean isLearned) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.isLearned = isLearned;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isLearned() {
        return isLearned;
    }
}

