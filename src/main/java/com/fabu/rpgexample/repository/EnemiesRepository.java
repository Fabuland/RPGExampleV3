package com.fabu.rpgexample.repository;

import com.fabu.rpgexample.model.EnemiesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemiesRepository extends CrudRepository<EnemiesModel, Long> {
    // You can define additional query methods here if needed

}