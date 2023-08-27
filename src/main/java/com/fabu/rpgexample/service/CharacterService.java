package com.fabu.rpgexample.service;

import com.fabu.rpgexample.controller.CharacterController;
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
    private CharacterController characterController;
    public Long characterId;
    public Long enemyRandomId;
    public int chLevel;
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
        characterId = id;
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
        Optional<EnemiesModel> enemiesOptional = enemiesRepository.findById(enemyRandomId);
        enemiesOptional.ifPresent(enemiesmodel -> {
            model.addAttribute("enemiesmodel", enemiesmodel);
        });
    }

    public void randomEnemyIdGenerator(){
        int maxEnemyId = enemiesRepository.findMaxId();
        enemyRandomId = (long) Math.floor(Math.random() * (maxEnemyId - 1 + 1) + 1);
    }

    public void getCharacterLevel(){
        chLevel = statsRepository.chLevel(characterId);
    }

    public int randomLevelGenerator(){
        //(int)Math.floor(Math.random() * (max - min + 1) + min);
        int maxCurrentLevel = chLevel + 2;
        System.out.println(chLevel + " " + maxCurrentLevel);
        int minCurrentLevel = chLevel - 2;
        if(minCurrentLevel <= 1){
            minCurrentLevel = 1;
        }
        return (int)Math.floor(Math.random() * (maxCurrentLevel - minCurrentLevel + 1) + minCurrentLevel);
    }

    public void ratStatsCalc(Long id, int level){
        EnemiesModel ratModel = new EnemiesModel();
        ratModel.setHealth(calculateRatHealth(level));
        ratModel.setCurrentHealth(calculateRatHealth(level));
        ratModel.setAtkPower(calculateRatAtkPower(level));
        ratModel.setExpGiven(calculateRatExpGiven(level));
        enemiesRepository.updateEnemy(ratModel.getAtkPower(), ratModel.getCurrentHealth(), ratModel.getExpGiven(), ratModel.getHealth(), level, id);
        System.out.println("Rat random level is " + level + ", atk power: " + ratModel.getAtkPower() + ", health: " + ratModel.getHealth() + ", exp given: " + ratModel.getExpGiven());
    }

    public int calculateRatExpGiven(int level) {
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

    public int calculateRatHealth(int level){
        int[] totalHealth = new int[level];
        int value = 4; // Initial value
        int increment = 2;
        int lvlThreshold = 3;

        for (int i = 0; i < level; i++) {
            if(i >= lvlThreshold){
                increment++;
                lvlThreshold += 4;
            }
            totalHealth[i] = value;
            value = value + increment;
        }
        return totalHealth[level-1];
    }

    public int calculateRatAtkPower(int level){
        int[] atkPower = new int[level];
        int current = 0; // Initial increment

        for (int i = 1; current < level; i++) {
            atkPower[current++] = i;
            if (current < level) {
                atkPower[current++] = i;
            }
        }
        return atkPower[level-1];
    }

    public void slimeStatsCalc(Long id, int level){
        EnemiesModel slimeModel = new EnemiesModel();
        slimeModel.setHealth(calculateSlimeHealth(level));
        slimeModel.setCurrentHealth(calculateSlimeHealth(level));
        slimeModel.setAtkPower(calculateSlimeAtkPower(level));
        slimeModel.setExpGiven(calculateSlimeExpGiven(level));
        enemiesRepository.updateEnemy(slimeModel.getAtkPower(), slimeModel.getCurrentHealth(), slimeModel.getExpGiven(), slimeModel.getHealth(), level, id);
        System.out.println("Slime random level is " + level + ", atk power: " + slimeModel.getAtkPower() + ", health: " + slimeModel.getHealth() + ", exp given: " + slimeModel.getExpGiven());
    }

    public int calculateSlimeExpGiven(int level) {
        int[] expGiven = new int[level];
        int value = 9; // Initial value
        int increment = 3; // Initial increment

        for (int i = 0; i < level; i++) {
            expGiven[i] = value;
            value += increment;
            increment = increment + 3; // Increase the increment for the next iteration
        }
        return expGiven[level - 1];
    }

    public int calculateSlimeHealth(int level){
        int[] totalHealth = new int[level];
        int value = 5; // Initial value
        int increment = 3;
        int lvlThreshold = 3;

        for (int i = 0; i < level; i++) {
            if(i >= lvlThreshold){
                increment++;
                lvlThreshold += 4;
            }
            totalHealth[i] = value;
            value = value + increment;
        }
        return totalHealth[level-1];
    }

    public int calculateSlimeAtkPower(int level){
        int[] atkPower = new int[level];
        int current = 0; // Initial increment

        for (int i = 1; current < level; i++) {
            atkPower[current++] = i;
            if (current < level) {
                atkPower[current++] = i;
            }
        }
        return atkPower[level-1];
    }

    public void skeletonStatsCalc(Long id, int level){
        EnemiesModel skeletonModel = new EnemiesModel();
        skeletonModel.setHealth(calculateSkeletonHealth(level));
        skeletonModel.setCurrentHealth(calculateSkeletonHealth(level));
        skeletonModel.setAtkPower(calculateSkeletonAtkPower(level));
        skeletonModel.setExpGiven(calculateSkeletonExpGiven(level));
        enemiesRepository.updateEnemy(skeletonModel.getAtkPower(), skeletonModel.getCurrentHealth(), skeletonModel.getExpGiven(), skeletonModel.getHealth(), level, id);
        System.out.println("Skeleton random level is " + level + ", atk power: " + skeletonModel.getAtkPower() + ", health: " + skeletonModel.getHealth() + ", exp given: " + skeletonModel.getExpGiven());
    }

    public int calculateSkeletonExpGiven(int level) {
        int[] expGiven = new int[level];
        int value = 12; // Initial value
        int increment = 4; // Initial increment

        for (int i = 0; i < level; i++) {
            expGiven[i] = value;
            value += increment;
            increment = increment + 5; // Increase the increment for the next iteration
        }
        return expGiven[level - 1];
    }

    public int calculateSkeletonHealth(int level){
        int[] totalHealth = new int[level];
        int value = 6; // Initial value
        int increment = 4;
        int lvlThreshold = 3;

        for (int i = 0; i < level; i++) {
            if(i >= lvlThreshold){
                increment+=2;
                lvlThreshold += 4;
            }
            totalHealth[i] = value;
            value = value + increment;
        }
        return totalHealth[level-1];
    }

    public int calculateSkeletonAtkPower(int level){
        int[] atkPower = new int[level];
        if(level == 1){
            atkPower[0] = 1;
        }else if(level == 2){
            atkPower[1] = 1;
        }else if(level == 3){
            atkPower[2] = 2;
        }else if(level == 4){
            atkPower[3] = 2;
        }else if(level == 5){
            atkPower[4] = 3;
        }else if(level == 6){
            atkPower[5] = 3;
        }else if (level > 6){
            for (int i = 6; i < level; i++) {
                atkPower[i] = i-2; // Start from 4 and increment
            }
        }
        return atkPower[level-1];
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

    public int characterDamageCalculator(int atkPower){
        int maxDamage = atkPower * 2;
        int minDamage = maxDamage - 2;
        if(minDamage < 1){
            minDamage = 1;
        }
        return (int) Math.floor(Math.random() * (maxDamage - minDamage + 1) + minDamage);
    }

    public int enemyDamageCalculator(int atkPower){
        int minDamage = atkPower - 2;
        if(minDamage < 1){
            minDamage = 1;
        }
        return (int) Math.floor(Math.random() * (atkPower - minDamage + 1) + minDamage);
    }

    public void combatTurnCalc(){
        int characterDamage = characterDamageCalculator(statsRepository.chDamage(characterId));
        int enemyDamage = enemyDamageCalculator(enemiesRepository.enmyDamage(enemyRandomId));
        int chCurrentHealth = statsRepository.chCurrentHealth(characterId);
        int enemyCurrentHealth = enemiesRepository.enemyCurrentHealth(enemyRandomId);
        if(enemyCurrentHealth == 0){

        }
        int newEnemyCurrentHealth = enemyCurrentHealth - characterDamage;
        if(newEnemyCurrentHealth <= 0){
            newEnemyCurrentHealth = 0;
        }
        int newChCurrentHealth = chCurrentHealth - enemyDamage;
        if(newChCurrentHealth <= 0){
            //DEATH
            newChCurrentHealth = 0;
        }
        enemiesRepository.updateEnemyCurrentHealth(newEnemyCurrentHealth, enemyRandomId);
        statsRepository.updateChCurrentHealth(newChCurrentHealth, characterId);
    }

    public void healCharacter(){
        statsRepository.updateChCurrentHealth(statsRepository.chTotalHealth(characterId), characterId);
    }

}