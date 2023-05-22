package by.teachmeskills.lapeiko.coursework.repository;

import by.teachmeskills.lapeiko.coursework.model.Flashcard;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlashcardJdbcRepository implements FlashcardRepository {
    private final DataSource db;

    public FlashcardJdbcRepository(DataSource db) {
        this.db = db;
    }

    @Override
    public List<Flashcard> findAllCards() {
        String sql = """
                SELECT flashcard.id   AS id,
                       flashcard.name AS name,
                       count(card.id)    FILTER ( WHERE card.isLearned ) AS learned_cards,
                       count(card.id)                                    AS total_cards_count
                FROM flashcard
                         LEFT JOIN card ON flashcard.id = flashcard_id
                GROUP BY flashcard.id;
                """;
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Flashcard> flashcardsList = new ArrayList<>();
            while (resultSet.next()) {
                Flashcard flashcard = new Flashcard(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("learnedCards"),
                        resultSet.getLong("totalCardsCount")
                );
                flashcardsList.add(flashcard);
            }
            return flashcardsList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addFlashcard(String name) {
        String sql = """
                INSERT INTO flashcard (name)
                VALUES (?);
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeFlashcard(long flashcardId) {
        String sql = """
                DELETE  
                FROM flashcard
                WHERE flashcard.id = ?;
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashcardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
