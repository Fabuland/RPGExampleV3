package com.fabu.rpgexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "enemiesmodel")
public class EnemiesModel {

    /*TODO after the character attack, the HTML should refresh to update enemy health, then a 0.5s delay and then the same for the enemy attack*/
    /*TODO look if you can refresh HTML without updating whole page*/
    /*TODO After all of that is done (enemy stat calc and updating on db), update enemy and character health based on attack power when attk button is clicked*/
    /*TODO for finished product make only enemies appear from a range close to character level*/
    /*TODO grey version of character sprite when you die, 10 sec on stats page to regain health?*/
    /*FIXME enemy model blinks every time attk button is clicked (due to updating site)*/

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
