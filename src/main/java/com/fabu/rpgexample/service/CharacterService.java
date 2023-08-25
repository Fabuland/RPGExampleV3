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
@Transactional
public class CharacterService {

    private CharacterRepository characterRepository;
    private StatsRepository statsRepository;
    private EnemiesRepository enemiesRepository;
    public Long enemyRandomId;

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
        /*TODO add the statscalc method depending on ID so that DB is updated before doing the model*/
        Optional<EnemiesModel> enemiesOptional = enemiesRepository.findById(enemyRandomId);
        enemiesOptional.ifPresent(enemiesmodel -> {
            model.addAttribute("enemiesmodel", enemiesmodel);
        });
    }

    public void randomEnemyIdGenerator(){
        int maxEnemyId = enemiesRepository.findMaxId();
        enemyRandomId = (long) Math.floor(Math.random() * (maxEnemyId - 1 + 1) + 1);
    }

    public void loadEnemyDataWithId(Model model) {
        Optional<EnemiesModel> enemiesOptional = enemiesRepository.findById(enemyRandomId);
        enemiesOptional.ifPresent(enemiesmodel -> {
            model.addAttribute("enemiesmodel", enemiesmodel);
        });
    }

    public int randomLevelGenerator(){
        //(int)Math.floor(Math.random() * (max - min + 1) + min);
        int maxCurrentLevel = 10;
        return (int)Math.floor(Math.random() * (maxCurrentLevel - 1 + 1) + 1);
    }

    public void ratStatsCalc(Long id, int level){
        EnemiesModel ratModel = new EnemiesModel();
        ratModel.setHealth(10);
        ratModel.setCurrentHealth(5);
        ratModel.setAtkPower(7);
        ratModel.setExpGiven(calculateRatExpGiven(level));
        enemiesRepository.updateEnemy(ratModel.getAtkPower(), ratModel.getCurrentHealth(), ratModel.getExpGiven(), ratModel.getHealth(), level, id);
        System.out.println("Rat random level is " + level);
    }

    public static int calculateRatExpGiven(int level) {
        int[] expGiven = new int[level];
        int value = 6; // Initial value
        int increment = 2; // Initial increment

        for (int i = 0; i < level; i++) {
            expGiven[i] = value;
            value += increment;
            increment = increment + 2; // Increase the increment for the next iteration
        }
        return expGiven[level - 1];
    }

    public void slimeStatsCalc(Long id, int level){

        System.out.println("Slime random level is "+ level);
    }

    public void skeletonStatsCalc(Long id, int level){

        System.out.println("Skeleton random level is "+ level);
    }

    public void statsCalcBasedOnId(){
        if(enemyRandomId == 1){
            ratStatsCalc(enemyRandomId, randomLevelGenerator());
        }
        else if(enemyRandomId == 2){
            slimeStatsCalc(enemyRandomId, randomLevelGenerator());
        }
        else if(enemyRandomId == 3){
            skeletonStatsCalc(enemyRandomId, randomLevelGenerator());
        }
    }

}