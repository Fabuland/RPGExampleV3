package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.model.EnemiesModel;
import com.fabu.rpgexample.model.LogModel;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterRepository characterRepository;
    private final StatsRepository statsRepository;
    private final EnemiesRepository enemiesRepository;


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
        return "index";
    }

    @GetMapping(value={"/combat/{id}", "/combat/", "/combat"})
    public String getCombatPage(@PathVariable(value = "id", required = false) Long id, Model model){
        /*TODO combine all of the methods into one method inside service*/
        characterService.getCombatPageStart(id, model);
        return "combat-page";
    }

    @GetMapping(value = "/combat/attack")
    public String getCombatTurn(Model model){
        characterService.getCombatTurnStart(model);
        return "combat-page";
    }

    @GetMapping(value = "/combat/heal")
    public String getHealTurn(Model model){
        characterService.getHealTurnStar(model);
        return "combat-page";
    }
}
