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
    @Size(min = 5, message = "The name needs more than 4 characters")
    private String name;

    @Column(nullable = false, name = "level")
    @NotNull(message = "The level can't be null")
    @Min(value = 1, message = "The level can't be less than 1")
    private Integer level;

    @Column(nullable = false, name = "characterclass")
    @NotEmpty(message = "The class can't be null")
    @Size(min = 3, message = "The class needs more than 2 characters")
    private String characterClass;

    // Constructors
    public CharacterModel() {
    }

    public CharacterModel(String name, Integer level, String characterClass) {
        this.name = name;
        this.level = level;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
                ", level=" + level +
                ", characterClass='" + characterClass + '\'' +
                '}';
    }
}
