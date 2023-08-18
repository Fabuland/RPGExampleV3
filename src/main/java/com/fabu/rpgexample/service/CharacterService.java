package com.fabu.rpgexample.service;

import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.model.StatsModel;
import com.fabu.rpgexample.repository.CharacterRepository;
import com.fabu.rpgexample.repository.StatsRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private CharacterRepository characterRepository;
    private StatsRepository statsRepository;

    public CharacterService(){

    }

    public CharacterService(CharacterRepository characterRepository, StatsRepository statsRepository) {
        this.characterRepository = characterRepository;
        this.statsRepository = statsRepository;
    }

    // Add more calculation methods as needed

}