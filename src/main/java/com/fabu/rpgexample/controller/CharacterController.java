package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.model.StatsModel;
import com.fabu.rpgexample.repository.CharacterRepository;
import com.fabu.rpgexample.repository.StatsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.fabu.rpgexample.model.CharacterModel;

import java.util.Optional;

@Controller
public class CharacterController {

    private final CharacterRepository characterRepository;
    private final StatsRepository statsRepository;

    @Autowired
    public CharacterController(CharacterRepository characterRepository, StatsRepository statsRepository) {
        this.characterRepository = characterRepository;
        this.statsRepository = statsRepository;
    }

    @GetMapping(value={"/{id}", "/"})
    public String getAllCharacters(@PathVariable(value = "id", required = false) Long id, Model model) {
        if(id == null){
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
        System.out.println("Inside index method");
        return "home-page";
    }

}
