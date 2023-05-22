package by.teachmeskills.lapeiko.coursework.repository;

import by.teachmeskills.lapeiko.coursework.model.Card;

import java.util.List;

public interface CardRepository {
    List<Card> findCardsByFlashcardId(long flashcardId);

    List<Card> findCardsByFlashcardIdAndOffset(long flashcardId, int offset);

    void addCard(long cardId, String question, String answer, boolean isLearned);

    void removeCard(long cardId);

    void updateLearnedCard(long cardId, boolean isLearned);
}
