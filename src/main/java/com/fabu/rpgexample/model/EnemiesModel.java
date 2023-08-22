package com.fabu.rpgexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "enemiesmodel")
public class EnemiesModel {

    /*TODO add inserts in data.sql*/
    /*TODO in charactercontroller, add a random for the ID to get random enemies*/
    /*TODO add name, lvl and health to user and enemy card on HTML*/
    /*TODO create exp, health and lvlup calcs on service*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    @NotEmpty(message = "The name can't be null")
    @Size(min = 5, max = 9, message = "The name needs more than 4 characters")
    private String name;

    @Column(nullable = false, name = "level")
    @NotNull(message = "The level can't be null")
    @Min(value = 1, message = "The level can't be less than 1")
    private Integer level;

    @Column(nullable = false, name = "expgiven")
    @NotNull(message = "The exp given can't be null")
    @Min(value = 1, message = "The exp given can't be less than 1")
    private Integer expGiven;

    @Column(nullable = false, name = "health")
    @NotNull(message = "The health can't be null")
    @Min(value = 1, message = "The health can't be less than 1")
    private Integer health;

    @Column(nullable = false, name = "currenthealth")
    @NotNull(message = "The health can't be null")
    private Integer currentHealth;

    @Column(nullable = false, name = "atkpower")
    @NotNull(message = "The level can't be null")
    @Min(value = 1, message = "The atk power can't be less than 1")
    private Integer atkPower;

    public EnemiesModel() {
    }

    public EnemiesModel(String name, Integer level, Integer expGiven, Integer health, Integer currentHealth, Integer atkPower) {
        this.name = name;
        this.level = level;
        this.expGiven = expGiven;
        this.health = health;
        this.currentHealth = currentHealth;
        this.atkPower = atkPower;
    }

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

    public Integer getExpGiven() {
        return expGiven;
    }

    public void setExpGiven(Integer expGiven) {
        this.expGiven = expGiven;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Integer getAtkPower() {
        return atkPower;
    }

    public void setAtkPower(Integer atkPower) {
        this.atkPower = atkPower;
    }

    @Override
    public String toString() {
        return "EnemiesModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", expGiven=" + expGiven +
                ", health=" + health +
                ", currentHealth=" + currentHealth +
                ", atkPower=" + atkPower +
                '}';
    }
}
