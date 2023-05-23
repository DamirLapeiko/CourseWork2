package by.teachmeskills.lapeiko.coursework.service;

import by.teachmeskills.lapeiko.coursework.model.Card;
import by.teachmeskills.lapeiko.coursework.model.Flashcard;

import java.util.List;
import java.util.Optional;

public interface CardService {

    List<Flashcard> findAllFlashcards();

    void addFlashcard(String name);


    void removeFlashcard(long flashcardId);

    List<Card> findCardsByFlashcardId(long flashcardId);

    Optional<Card> findCardsByFlashcardIdAndOffset(long flashcardId, int offset);

    void addCard(long cardId, String question, String answer, boolean isLearned);

    void removeCard(long cardId);

    void updateLearnedCard(long cardId, boolean isLearned);

}
