CREATE DATABASE "BugCollection";

-- Создание таблицы для пользователей
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы для жуков
CREATE TABLE beetles
(
    id          SERIAL PRIMARY KEY,
    user_id     INT          NOT NULL,
    name        VARCHAR(255) NOT NULL,
    species     VARCHAR(255) NOT NULL,
    description TEXT,
    image_url   VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Создание таблицы для предложений обмена
CREATE TABLE exchange_offers
(
    id                SERIAL PRIMARY KEY,
    offer_id          INT          NOT NULL,                   -- ID пользователя, который делает предложение
    beetle_id         INT          NOT NULL,                   -- ID жука, который предлагается для обмена
    requested_species VARCHAR(255) NOT NULL,                   -- Вид жука, который хотят получить взамен
    status            VARCHAR(50)  DEFAULT 'open',             -- Статус предложения (открыто, закрыто, и т.д.)
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (offer_id) REFERENCES users (id),
    FOREIGN KEY (beetle_id) REFERENCES beetles (id)
);

-- Создание промежуточной таблицы для связи Many-to-Many между пользователями и жуками
CREATE TABLE users_beetles
(
    user_id    INT NOT NULL,
    beetle_id  INT NOT NULL,
    PRIMARY KEY (user_id, beetle_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (beetle_id) REFERENCES beetles (id) ON DELETE CASCADE
);