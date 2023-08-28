package com.fabu.rpgexample.repository;

import org.springframework.data.jpa.repository.Modifying;
import com.fabu.rpgexample.model.EnemiesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemiesRepository extends CrudRepository<EnemiesModel, Long> {
    // You can define additional query methods here if needed

    @Query("SELECT MAX(id) FROM EnemiesModel")
    int findMaxId();

    //Get a new object of EnemiesModel based on the id given, so you can get information
    @Query("SELECT e FROM EnemiesModel e WHERE e.id = :id")
    EnemiesModel getEnemyById(@Param("id") Long id);

    @Query("SELECT atkPower FROM EnemiesModel WHERE id = :id")
    int enmyDamage(Long id);

    @Query("SELECT currentHealth FROM EnemiesModel WHERE id = :id")
    int enemyCurrentHealth(Long id);

    @Query("SELECT expGiven FROM EnemiesModel WHERE id = :id")
    int enemyExpGiven(Long id);

    // Update the enemy based on every parameter given, which are all the variables of EnemiesModel
    @Modifying
    @Query("UPDATE EnemiesModel SET atkPower = :atkPower, currentHealth = :currentHealth, expGiven = :expGiven, health = :health, level = :level WHERE id = :id")
    void updateEnemy(@Param("atkPower") int atkPower, @Param("currentHealth") int currentHealth, @Param("expGiven") int expGiven, @Param("health") int health, @Param("level") int level, @Param("id") Long id);

    @Modifying
    @Query("UPDATE EnemiesModel SET currentHealth = :currentHealth WHERE id = :id")
    void updateEnemyCurrentHealth(@Param("currentHealth") int currentHealth, @Param("id") Long id);

}