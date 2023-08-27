package com.fabu.rpgexample.repository;

import com.fabu.rpgexample.model.StatsModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends CrudRepository<StatsModel, Long> {
    // You can define additional query methods here if needed

    @Query("SELECT atkPower from StatsModel WHERE id = :id")
    int chDamage(Long id);

    @Query("SELECT level from StatsModel WHERE id = :id")
    int chLevel(Long id);

    @Query("SELECT health FROM StatsModel WHERE id = :id")
    int chTotalHealth(Long id);

    @Query("SELECT currentHealth FROM StatsModel WHERE id = :id")
    int chCurrentHealth(Long id);

    @Modifying
    @Query("UPDATE StatsModel SET currentHealth = :currentHealth WHERE id = :id")
    void updateChCurrentHealth(@Param("currentHealth") int currentHealth, @Param("id") Long id);


}