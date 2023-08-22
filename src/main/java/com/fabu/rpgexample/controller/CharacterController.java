package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.model.EnemiesModel;
import com.fabu.rpgexample.model.StatsModel;
import com.fabu.rpgexample.repository.CharacterRepository;
import com.fabu.rpgexample.repository.EnemiesRepository;
import com.fabu.rpgexample.repository.StatsRepository;
import com.fabu.rpgexample.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fabu.rpgexample.model.CharacterModel;

import java.util.Optional;

@Controller
public class CharacterController {

    private final CharacterRepository characterRepository;
    private final StatsRepository statsRepository;
    private final EnemiesRepository enemiesRepository;
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterRepository characterRepository, StatsRepository statsRepository, EnemiesRepository enemiesRepository, CharacterService characterService) {
        this.characterRepository = characterRepository;
        this.statsRepository = statsRepository;
        this.enemiesRepository = enemiesRepository;
        this.characterService = characterService;
    }

    @GetMapping(value={"/{id}", "/"})
    public String getAllCharacters(@PathVariable(value = "id", required = false) Long id, Model model) {
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
        System.out.println("Inside home page method");
        return "index";
    }

    @GetMapping(value={"/combat/{id}", "/combat/"})
    public String getCombatPage(@PathVariable(value = "id", required = false) Long id, Model model){
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
        int maxEnemyId = enemiesRepository.findMaxId();
        int enemyRandomId = (int)Math.floor(Math.random() * (maxEnemyId - 1 + 1) + 1);
        Optional<EnemiesModel> enemiesOptional = enemiesRepository.findById((long) enemyRandomId);
        enemiesOptional.ifPresent(enemiesmodel -> {
            model.addAttribute("enemiesmodel", enemiesmodel);
        });
        System.out.println(enemiesOptional.get().getLevel());
        System.out.println("Inside combat page method");
        return "combat-page";
    }

}
