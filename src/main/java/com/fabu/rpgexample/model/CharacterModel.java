package com.fabu.rpgexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "charactermodel")
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    @NotEmpty(message = "The name can't be null")
    @Size(min = 3, max = 15, message = "The name needs more than 2 characters")
    private String name;

    @Column(nullable = false, name = "characterclass")
    @NotEmpty(message = "The class can't be null")
    @Size(min = 2, message = "The class needs more than 1 character")
    private String characterClass;

    // Constructors
    public CharacterModel() {
    }

    public CharacterModel(String name, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    // toString() method for easy logging
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", characterClass='" + characterClass + '\'' +
                '}';
    }
}
