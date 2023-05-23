package by.teachmeskills.lapeiko.coursework.repository;

import by.teachmeskills.lapeiko.coursework.model.Flashcard;

import java.util.List;

public interface FlashcardRepository {

    List<Flashcard> findAllFlashcards();

    void addFlashcard(String name);


    void removeFlashcard(long flashcardId);
}
