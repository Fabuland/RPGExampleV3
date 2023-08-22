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

    public StatsModel(Integer level, Integer totalExp, Integer health, Integer currentHealth, Integer atkPower, Integer critChance) {
        this.level = level;
        this.totalExp = totalExp;
        this.health = health;
        this.currentHealth = currentHealth;
        this.atkPower = atkPower;
        this.critChance = critChance;
    }

    public long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getTotalExp() {
        return totalExp;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getAtkPower() {
        return atkPower;
    }

    public Integer getCritChance() {
        return critChance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setTotalExp(Integer totalExp) {
        this.totalExp = totalExp;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setAtkPower(Integer atkPower) {
        this.atkPower = atkPower;
    }

    public void setCritChance(Integer critChance) {
        this.critChance = critChance;
    }

    public Integer getCurrentHealth() { return currentHealth; }

    public void setCurrentHealth(Integer currentHealth) { this.currentHealth = currentHealth; }

    @Override
    public String toString() {
        return "StatsModel{" +
                "level=" + level +
                ", health=" + health +
                ", atkPower=" + atkPower +
                ", critChance=" + critChance +
                '}';
    }
}
