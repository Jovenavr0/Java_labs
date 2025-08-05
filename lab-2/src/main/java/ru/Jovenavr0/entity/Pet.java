package ru.Jovenavr0.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PetColor color;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "pets_friends",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<Pet> friends = new HashSet<>();

    public void SetColor(PetColor color) {
        this.color = color;
    }

    public void SetBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void SetOwner(Owner owner) {
        this.owner = owner;
    }

    public void SetName(String string) {
        this.name = string;
    }

    public void SetBreed(String string) {
        this.breed = string;
    }

    public Set<Pet> getFriends() {
        return friends;
    }

    public void  addFriend(Pet friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(UUID id){
        this.id = id;
    }

}
