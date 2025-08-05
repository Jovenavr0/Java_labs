CREATE TABLE pets (
    id uuid PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    breed VARCHAR(100) NOT NULL,
    color VARCHAR(20) NOT NULL,
    owner_id uuid NOT NULL references owners(id)
);

CREATE TABLE pets_friends (
    pet_id uuid NOT NULL references pets(id),
    friend_id uuid NOT NULL references pets(id),
    PRIMARY KEY (pet_id, friend_id)
);
