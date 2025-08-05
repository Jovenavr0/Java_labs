CREATE TABLE pets (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    breed VARCHAR(100) NOT NULL,
    color VARCHAR(20) NOT NULL,
    tail_length INT NOT NULL,
    owner_id BIGSERIAL NOT NULL references owners(id)
);

CREATE TABLE pets_friends (
    pet_id BIGSERIAL NOT NULL references pets(id) ON DELETE CASCADE,
    friend_id BIGSERIAL NOT NULL references pets(id) ON DELETE CASCADE,
    PRIMARY KEY (pet_id, friend_id)
);
