package com.fabu.rpgexample.service;

import com.fabu.rpgexample.controller.CharacterController;
import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.model.EnemiesModel;
import com.fabu.rpgexample.model.LogModel;
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
    public Model characterEnemyModel;
    public static LogModel logEntries = new LogModel();
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
        model.addAttribute("logModel", logEntries);
        int chCurrentHealth = statsRepository.chCurrentHealth(id);
        if(chCurrentHealth <= 0){
            healCharacter();
        }
    }

    public void loadRandomEnemyData(Model model) {
        Optional<EnemiesModel> enemiesOptional = enemiesRepository.findById(enemyRandomId);
        enemiesOptional.ifPresent(enemiesmodel -> {
            model.addAttribute("enemiesmodel", enemiesmodel);
        });
    }

    public void getCombatPageStart(Long id, Model model){
        randomEnemyIdGenerator();
        checkLevelUp(getCharacterLevel());
        loadCharacterData(model, id);
        statsCalcBasedOnId();
        loadRandomEnemyData(model);
        characterEnemyModel = model;
    }

    public void getCombatTurnStart(Model model){
        combatTurnCalc();
        loadCharacterData(model, characterId);
        loadRandomEnemyData(model);

    }

    public void getHealTurnStar(Model model){
        healCharacter();
        loadCharacterData(model, characterId);
        loadRandomEnemyData(model);
    }

    public void randomEnemyIdGenerator(){
        int maxEnemyId = enemiesRepository.findMaxId();
        enemyRandomId = (long) Math.floor(Math.random() * (maxEnemyId - 1 + 1) + 1);
    }

    public int getCharacterLevel(){
        chLevel = statsRepository.chLevel(characterId);
        return chLevel;
    }

    public int randomLevelGenerator(){
        //(int)Math.floor(Math.random() * (max - min + 1) + min);
        int maxCurrentLevel = chLevel + 2;
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
        int damageDealt = (int) Math.floor(Math.random() * (maxDamage - minDamage + 1) + minDamage);
        int getCritChance = statsRepository.chCurrentCritChance(characterId);
        boolean isCrit = false;
        int critRandomizer = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);
        System.out.println("Crit random number: " + critRandomizer);
        if (critRandomizer <= getCritChance){
            damageDealt = damageDealt*2;
            System.out.println("Crit! " + damageDealt);
        }
        return damageDealt;
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
        int newEnemyCurrentHealth = enemyCurrentHealth - characterDamage;
        String damageLog = characterRepository.chName(characterId) + " dealt " + characterDamage + " damage. " + enemiesRepository.enemyName(enemyRandomId) + " did " + enemyDamage + " damage.";
        addNewLog(damageLog);
        if(newEnemyCurrentHealth <= 0){
            newEnemyCurrentHealth = 0;
            giveExpToCharacter();

        }
        int newChCurrentHealth = chCurrentHealth - enemyDamage;
        if(newChCurrentHealth <= 0){
            newChCurrentHealth = 0;
        }
        enemiesRepository.updateEnemyCurrentHealth(newEnemyCurrentHealth, enemyRandomId);
        statsRepository.updateChCurrentHealth(newChCurrentHealth, characterId);
    }

    public void healCharacter(){
        statsRepository.updateChCurrentHealth(statsRepository.chTotalHealth(characterId), characterId);
    }

    public int calcExpNeedPerLevel(int maxLevel) {
        int[] expNeeded = new int[maxLevel];

        for (int level = 1; level <= maxLevel; level++) {
            // Replace this formula with your actual experience point calculation
            int exp = 10 * level + level * (level * 14); // Just a simple example, adjust as needed
            expNeeded[level - 1] = exp;
        }

        return expNeeded[maxLevel - 1];
    }

    public int calcTotalExpNeedPerLevel(int maxLevel) {
        int[] expNeeded = new int[maxLevel];

        int totalExp = 0;
        for (int level = 1; level <= maxLevel; level++) {
            // Replace this formula with your actual experience point calculation
            int exp = 10 * level + level * (level * 14); // Just a simple example, adjust as needed
            totalExp += exp;
            expNeeded[level - 1] = totalExp;
        }

        return expNeeded[maxLevel - 1];
    }

    public void checkLevelUp(int level){
        int currentTotalExp = statsRepository.chCurrentTotalExp(characterId);
        int totalExpNeeded = calcTotalExpNeedPerLevel(level);
        if(currentTotalExp >= totalExpNeeded){
            chLevel = chLevel + 1;
            statsRepository.updateChLevel(chLevel, characterId);
            chStatsCalcUp(characterId, chLevel);
            String levelUpLog = characterRepository.chName(characterId) + " reached lvl " + chLevel + "!";
            addNewLog(levelUpLog);
        }
    }

    public int chHealthUp(int level){
        return 10 + (level * 5);
    }
    public int chAtkPowerUp(int level){
        return level;
    }
    public int chCritChanceUp(int level){
        return level*2;
    }

    public void chStatsCalcUp(Long id, int level){
        StatsModel chModel = new StatsModel();
        chModel.setHealth(chHealthUp(level));
        chModel.setCurrentHealth(chHealthUp(level));
        chModel.setAtkPower(chAtkPowerUp(level));
        chModel.setCritChance(chCritChanceUp(level));
        statsRepository.updateChLevelUp(chModel.getAtkPower(), chModel.getCurrentHealth(), chModel.getCritChance(), chModel.getHealth(), id);
    }

    public void giveExpToCharacter(){
        int enemyExpGiven = enemiesRepository.enemyExpGiven(enemyRandomId);
        String enemyDefeatedLog = characterRepository.chName(characterId) + " defeated a " + enemiesRepository.enemyName(enemyRandomId) + "! You receive " + enemyExpGiven + " exp.";
        addNewLog(enemyDefeatedLog);
        int chTotalExp = statsRepository.chCurrentTotalExp(characterId) + enemyExpGiven;
        statsRepository.updateChTotalExp(chTotalExp, characterId);
    }

    public static void addNewLog(String newLog) {
        String[] logs = logEntries.logs;
        for (int i = logs.length - 1; i > 0; i--) {
            logs[i] = logs[i - 1];
        }
        logs[0] = newLog;
    }

}