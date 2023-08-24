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

    private final CharacterService characterService;
    private final CharacterRepository characterRepository;
    private final StatsRepository statsRepository;
    private final EnemiesRepository enemiesRepository;
    public int enemyRandomId;
    public Long characterId;
    public Model characterEnemyModel;

    @Autowired
    public CharacterController(CharacterRepository characterRepository, StatsRepository statsRepository, EnemiesRepository enemiesRepository, CharacterService characterService) {
        this.characterRepository = characterRepository;
        this.statsRepository = statsRepository;
        this.enemiesRepository = enemiesRepository;
        this.characterService = characterService;
    }

    @GetMapping(value={"/{id}", "/"})
    public String getAllCharacters(@PathVariable(value = "id", required = false) Long id, Model model) {
        characterService.loadCharacterData(model, id);
        System.out.println("Inside home page method");
        return "index";
    }

    @GetMapping(value={"/combat/{id}", "/combat/"})
    public String getCombatPage(@PathVariable(value = "id", required = false) Long id, Model model){
        characterService.loadCharacterData(model, id);
        characterId = id;
        enemyRandomId = characterService.loadRandomEnemyData(model);
        characterService.statsCalcBasedOnId(enemyRandomId);
        characterEnemyModel = model;
        System.out.println("Inside combat page method");
        return "combat-page";
    }

    @GetMapping(value = "/combat/attack")
    public String getTestCombat(Model model){
        characterService.loadCharacterData(model, characterId);
        characterService.loadEnemyDataWithId(model, (long) enemyRandomId);
        System.out.println("Button works");
        return "combat-page";
    }
}
