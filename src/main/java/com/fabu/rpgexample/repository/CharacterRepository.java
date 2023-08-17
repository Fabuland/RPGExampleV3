package com.fabu.rpgexample.repository;

import com.fabu.rpgexample.model.CharacterModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterModel, Long> {
    // You can define additional query methods here if needed
    List<CharacterModel> findByName(String name);
}