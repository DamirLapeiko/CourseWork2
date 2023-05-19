--Главная страница: список папок
SELECT flashcard.id   AS id,
       flashcard.name AS name
FROM flashcard;

--Главная страница: добавление карточки
INSERT INTO flashcard (name)
VALUES (?);

--Набор: список карточек
SELECT card.id        AS id,
       card.question  AS question,
       card.answer    AS answer,
       card.isLearned AS isLearned
FROM card
WHERE flashcard_id = ?;

--Редактирование набора: удаление карточки
DELETE
FROM card
WHERE card.id = ?;

--Редактирование набора: добавление карточки
INSERT INTO card (flashcard_id, question, answer, isLearned)
VALUES (?, ?, ?, ?);

--Редактирование набора: удаление набора
DELETE
FROM card
WHERE flashcard_id = ?;

--Практика
SELECT card.id        AS id,
       card.question  AS question,
       card.answer    AS answer,
       card.isLearned AS isLearned
FROM card
WHERE flashcard_id = ?
  AND NOT card.isLearned
  AND card.id > ?
ORDER BY flashcard_id LIMIT 1;

--Практика
UPDATE card
SET isLearned = TRUE
WHERE card.id = ?;

--Главная страница: подсчет карточек в папках
SELECT flashcard.id   AS id,
       flashcard.name AS name,
       count(card.id)    FILTER ( WHERE card.isLearned ) AS learned_cards,
       count(card.id)                                    AS total_cards_count
FROM flashcard
         LEFT JOIN card ON flashcard.id = flashcard_id
GROUP BY flashcard.id;