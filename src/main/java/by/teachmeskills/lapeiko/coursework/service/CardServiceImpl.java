package by.teachmeskills.lapeiko.coursework.service;

import by.teachmeskills.lapeiko.coursework.exception.ServiceException;
import by.teachmeskills.lapeiko.coursework.model.Card;
import by.teachmeskills.lapeiko.coursework.model.Flashcard;
import by.teachmeskills.lapeiko.coursework.repository.CardRepository;
import by.teachmeskills.lapeiko.coursework.repository.FlashcardRepository;

import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements CardService {
    FlashcardRepository flashcardRepository;
    CardRepository cardRepository;

    public CardServiceImpl(FlashcardRepository flashcardRepository, CardRepository cardRepository) {
        this.flashcardRepository = flashcardRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Flashcard> findAllCards() {
        List<Flashcard> flashCards = flashcardRepository.findAllCards();
        if (!flashCards.isEmpty()) {
            return flashCards;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void addFlashcard(String name) {
        if (name.isEmpty()) throw new ServiceException();
        flashcardRepository.addFlashcard(name);
    }

    @Override
    public void removeFlashcard(long flashcardId) {
        flashcardRepository.removeFlashcard(flashcardId);
    }

    @Override
    public List<Card> findCardsByFlashcardId(long flashcardId) {
        List<Card> cardList = cardRepository.findCardsByFlashcardId(flashcardId);
        if (!cardList.isEmpty()) {
            return cardList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Card> findCardsByFlashcardIdAndOffset(long flashcardId, int offset) {
        List<Card> cardList = cardRepository.findCardsByFlashcardIdAndOffset(flashcardId, offset);
        if (!cardList.isEmpty()) {
            return cardList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void addCard(long cardId, String question, String answer, boolean isLearned) {
        if (!(question.isEmpty() && answer.isEmpty())) {
            cardRepository.addCard(cardId, question, answer, isLearned);
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public void removeCard(long cardId) {
        cardRepository.removeCard(cardId);
    }

    @Override
    public void updateLearnedCard(long cardId, boolean isLearned) {
        cardRepository.updateLearnedCard(cardId, isLearned);
    }
}
