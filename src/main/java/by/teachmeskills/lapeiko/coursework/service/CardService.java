package by.teachmeskills.lapeiko.coursework.service;

import by.teachmeskills.lapeiko.coursework.model.Card;
import by.teachmeskills.lapeiko.coursework.model.Flashcard;

import java.util.List;

public interface CardService {

    List<Flashcard> findAllCards();

    void addFlashcard(String name);


    void removeFlashcard(long flashcardId);

    List<Card> findCardsByFlashcardId(long flashcardId);

    List<Card> findCardsByFlashcardIdAndOffset(long flashcardId, int offset);

    void addCard(long cardId, String question, String answer, boolean isLearned);

    void removeCard(long cardId);

    void updateLearnedCard(long cardId, boolean isLearned);

}
