package by.teachmeskills.lapeiko.coursework.repository;

import by.teachmeskills.lapeiko.coursework.model.Card;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardJdbcRepository implements CardRepository {
    private final DataSource db;

    public CardJdbcRepository(DataSource db) {
        this.db = db;
    }

    @Override
    public List<Card> findCardsByFlashcardId(long flashcardId) {
        String sql = """
                SELECT card.id        AS id,
                       card.question  AS question,
                       card.answer    AS answer,
                       card.isLearned AS isLearned
                FROM card
                WHERE flashcardId = ?;
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashcardId);
            ResultSet resultSet = statement.executeQuery();
            List<Card> cardsList = new ArrayList<>();
            while (resultSet.next()) {
                Card card = new Card(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("isLearned")
                );
                cardsList.add(card);
            }
            return cardsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Card> findCardsByFlashcardIdAndOffset(long flashcardId, int offset) {
        String sql = """
                SELECT card.id        AS id,
                       card.question  AS question,
                       card.answer    AS answer,
                       card.isLearned AS isLearned
                FROM card
                WHERE flashcardId = ?;
                AND NOT card.isLearned
                ORDER BY card.id
                LIMIT 1 OFFSET ?;
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flashcardId);
            statement.setLong(2, offset);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Card(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("isLearned")
                ));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCard(long cardId, String question, String answer, boolean isLearned) {
        String sql = """
                INSERT INTO card (flashcardId, question, answer, isLearned)
                VALUES (?, ?, ?, ?);
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cardId);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, isLearned);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeCard(long cardId) {
        String sql = """
                DELETE
                FROM card
                WHERE card.id = ?;
                """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLearnedCard(long cardId, boolean isLearned) {
        String sql = """
                UPDATE card
                SET isLearned = ?
                WHERE card.id = ?;
                 """;
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isLearned);
            statement.setLong(2, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
