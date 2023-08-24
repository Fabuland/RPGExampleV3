package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CharacterController {

    private final CharacterService characterService;
    public int enemyRandomId;
    public Long characterId;
    public Model characterEnemyModel;

    @Autowired
    public CharacterController(CharacterService characterService) {
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
