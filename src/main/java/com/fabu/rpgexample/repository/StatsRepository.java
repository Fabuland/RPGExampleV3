package com.fabu.rpgexample.repository;

import com.fabu.rpgexample.model.StatsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends CrudRepository<StatsModel, Long> {
    // You can define additional query methods here if needed
}