package com.fabu.rpgexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "statsmodel")
public class StatsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "level")
    @NotNull(message = "The level can't be null")
    @Min(value = 1, message = "The level can't be less than 1")
    private Integer level;

    @Column(nullable = false, name = "totalexp")
    private Integer totalExp;

    @Column(nullable = false, name = "currentexp")
    private Integer currentExp;

    @Column(nullable = false, name = "expneedednextlevel")
    private Integer expNeededNextLevel;

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

    @Column(nullable = false, name = "critchance")
    @NotNull(message = "The level can't be null")
    private Integer critChance;

    public StatsModel() {
    }

    public StatsModel(long id, Integer level, Integer totalExp, Integer currentExp, Integer expNeededNextLevel, Integer health, Integer currentHealth, Integer atkPower, Integer critChance) {
        this.id = id;
        this.level = level;
        this.totalExp = totalExp;
        this.currentExp = currentExp;
        this.expNeededNextLevel = expNeededNextLevel;
        this.health = health;
        this.currentHealth = currentHealth;
        this.atkPower = atkPower;
        this.critChance = critChance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(Integer totalExp) {
        this.totalExp = totalExp;
    }

    public Integer getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(Integer currentExp) {
        this.currentExp = currentExp;
    }

    public Integer getExpNeededNextLevel() {
        return expNeededNextLevel;
    }

    public void setExpNeededNextLevel(Integer expNeededNextLevel) {
        this.expNeededNextLevel = expNeededNextLevel;
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

    public Integer getCritChance() {
        return critChance;
    }

    public void setCritChance(Integer critChance) {
        this.critChance = critChance;
    }

    @Override
    public String toString() {
        return "StatsModel{" +
                "id=" + id +
                ", level=" + level +
                ", totalExp=" + totalExp +
                ", currentExp=" + currentExp +
                ", expNeededNextLevel=" + expNeededNextLevel +
                ", health=" + health +
                ", currentHealth=" + currentHealth +
                ", atkPower=" + atkPower +
                ", critChance=" + critChance +
                '}';
    }
}
