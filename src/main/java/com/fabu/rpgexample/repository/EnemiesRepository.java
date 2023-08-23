package com.fabu.rpgexample.repository;

import org.springframework.data.jpa.repository.Modifying;
import com.fabu.rpgexample.model.EnemiesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemiesRepository extends CrudRepository<EnemiesModel, Long> {
    // You can define additional query methods here if needed

    @Query("SELECT MAX(id) FROM EnemiesModel")
    int findMaxId();
    @Modifying
    @Query("UPDATE EnemiesModel SET atkPower = 1, currentHealth = 50, expGiven = 5, health = 10, level = 1, name = 'Rat' WHERE id = 1")
    void updateResetSkeleton();

    @Modifying
    @Query("UPDATE EnemiesModel SET atkPower = 1, currentHealth = 50, expGiven = 5, health = 10, level = 1, name = 'Rat' WHERE id = 1")
    void updateResetRat();

    @Modifying
    @Query("UPDATE EnemiesModel SET atkPower = 1, currentHealth = 50, expGiven = 5, health = 10, level = 1, name = 'Rat' WHERE id = 1")
    void updateResetSlime();

}