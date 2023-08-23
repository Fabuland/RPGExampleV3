package com.fabu.rpgexample.service;

import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.model.EnemiesModel;
import com.fabu.rpgexample.model.StatsModel;
import com.fabu.rpgexample.repository.CharacterRepository;
import com.fabu.rpgexample.repository.EnemiesRepository;
import com.fabu.rpgexample.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class CharacterService {

    private CharacterRepository characterRepository;
    private StatsRepository statsRepository;
    private EnemiesRepository enemiesRepository;

    public CharacterService(){

    }

    @Autowired
    public CharacterService(CharacterRepository characterRepository, StatsRepository statsRepository, EnemiesRepository enemiesRepository) {
        this.characterRepository = characterRepository;
        this.statsRepository = statsRepository;
        this.enemiesRepository = enemiesRepository;
    }

    public void loadCharacterData(Model model, Long id) {
        Long maxId = characterRepository.findMaxId();
        if(id == null || id > maxId){
            id = 1L;
        }
        Optional<CharacterModel> characterOptional = characterRepository.findById(id);
        characterOptional.ifPresent(charactermodel -> {
            model.addAttribute("charactermodel", charactermodel);
        });
        Optional<StatsModel> statsOptional = statsRepository.findById(id);
        statsOptional.ifPresent(statsmodel -> {
            model.addAttribute("statsmodel", statsmodel);
        });
    }

    public void loadRandomEnemyData(Model model) {
        int maxEnemyId = enemiesRepository.findMaxId();
        int enemyRandomId = (int)Math.floor(Math.random() * (maxEnemyId - 1 + 1) + 1);
        Optional<EnemiesModel> enemiesOptional = enemiesRepository.findById((long) enemyRandomId);
        enemiesOptional.ifPresent(enemiesmodel -> {
            model.addAttribute("enemiesmodel", enemiesmodel);
        });
        System.out.println(enemiesOptional.get().getLevel());
    }

}