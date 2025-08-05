package ru.Jovenavr0.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(name = "birth_date", nullable = false)
    LocalDate birthDay;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setName(String str){
        this.name = str;
    }

    public void setBirthDay(LocalDate localDate){
        this.birthDay = localDate;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

}