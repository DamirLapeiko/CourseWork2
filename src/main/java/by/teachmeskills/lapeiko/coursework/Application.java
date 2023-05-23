package by.teachmeskills.lapeiko.coursework;

import by.teachmeskills.lapeiko.coursework.model.Card;
import by.teachmeskills.lapeiko.coursework.model.Flashcard;
import by.teachmeskills.lapeiko.coursework.repository.CardJdbcRepository;
import by.teachmeskills.lapeiko.coursework.repository.CardRepository;
import by.teachmeskills.lapeiko.coursework.repository.FlashcardJdbcRepository;
import by.teachmeskills.lapeiko.coursework.repository.FlashcardRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Программа запущена");
        try (HikariDataSource db = new HikariDataSource()) {
            db.setJdbcUrl(System.getenv("DB_URL"));
            db.setUsername(System.getenv("DB_USER"));
            db.setPassword(System.getenv("DB_PASSWORD"));

            FlashcardRepository flashcardRepo = new FlashcardJdbcRepository(db);
            CardRepository cardRepo = new CardJdbcRepository(db);

            flashcardRepo.addFlashcard("Английский: Цвета");
            flashcardRepo.addFlashcard("Английский: Животные");

            List<Flashcard> flashcards = flashcardRepo.findAllFlashcards();
            System.out.println(flashcards);

            flashcardRepo.removeFlashcard(2);

            cardRepo.addCard(1, "Red", "Красный", false);
            cardRepo.addCard(1, "Yellow", "Желтый", false);
            cardRepo.addCard(1, "Green", "Зеленый", false);
            cardRepo.addCard(1, "Blue", "Синий", false);
            cardRepo.addCard(1, "Black", "Черный", false);
            cardRepo.addCard(1, "Purple", "Фиолетовый", false);

            List<Card> cards = cardRepo.findCardsByFlashcardId(1);
            System.out.println(cards);

            Optional<Card> cardsOpt = cardRepo.findCardsByFlashcardIdAndOffset(1, 0);
            System.out.println(cardsOpt);

            cardRepo.updateLearnedCard(1, true);
            cardRepo.updateLearnedCard(3, true);
            cardRepo.updateLearnedCard(5, true);
            cardRepo.removeCard(6);
            System.out.println(cards);
            log.info("Программа закончена");
        }
    }


}