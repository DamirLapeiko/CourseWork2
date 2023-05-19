CREATE TABLE flashcard
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE card
(
    id           BIGSERIAL PRIMARY KEY,
    flashcard_id BIGINT  NOT NULL REFERENCES flashcard ON DELETE CASCADE,
    question     TEXT    NOT NULL,
    answer       TEXT    NOT NULL,
    isLearned    BOOLEAN NOT NULL
);