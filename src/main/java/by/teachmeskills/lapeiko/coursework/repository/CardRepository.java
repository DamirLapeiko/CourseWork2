package by.teachmeskills.lapeiko.coursework.repository;

import by.teachmeskills.lapeiko.coursework.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    List<Card> findCardsByFlashcardId(long flashcardId);

    Optional<Card> findCardsByFlashcardIdAndOffset(long flashcardId, int offset);

    void addCard(long cardId, String question, String answer, boolean isLearned);

    void removeCard(long cardId);

    void updateLearnedCard(long cardId, boolean isLearned);
}
