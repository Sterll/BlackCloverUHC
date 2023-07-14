package fr.yanis.blackcloveruhc.roles;

import fr.yanis.blackcloveruhc.BlackCloverUHC;
import fr.yanis.blackcloveruhc.list.EffectList;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Role {

    private Player player;
    private boolean isAlive = true;
    private boolean isTaked = false;
    private ArrayList<Role> knowRoles = new ArrayList<Role>();
    private ArrayList<EffectList> effects = new ArrayList<>();
    private HashMap<String, BukkitTask> tasks = new HashMap<>();

    public void onDay(){

    }
    public void onNight(){

    }
    public void onDamage(){
    }
    public float getDamage(float baseDamage){
        if(effects.contains(EffectList.STRENGTH1)){
            return baseDamage * (EffectList.STRENGTH1.getMultiplier() / 100F) + 1;
        } else if(effects.contains(EffectList.STRENGTH2)){
            return baseDamage * (EffectList.STRENGTH2.getMultiplier() / 100F) + 1;
        } else {
            return baseDamage;
        }
    }

    public float getResistance(){
        if(effects.contains(EffectList.RESISTANCE1)){
            return 1 - (EffectList.RESISTANCE1.getMultiplier() / 100F);
        } else if(effects.contains(EffectList.RESISTANCE2)){
            return 1 - (EffectList.RESISTANCE2.getMultiplier() / 100F);
        } else {
            return 1;
        }
    }
    public void onDeath(){

    }
    public void onKill(){

    }
    public void onReceive(Player player){
        setAlive(true);
        setTaked(true);
        setPlayer(player);
    }
    public Player getPlayer() {
            return player;
        }
    public void setPlayer(Player player) {
            this.player = player;
    }
    public boolean isAlive() {
            return isAlive;
    }
    public void setAlive(boolean alive) {
            isAlive = alive;
    }
    public boolean isTaked() {
            return isTaked;
    }
    public void setTaked(boolean taked) {
        isTaked = taked;
    }
    public ArrayList<Role> getKnowRoles() {
        return knowRoles;
    }
    public void setKnowRoles(ArrayList<Role> knowRoles) {
        this.knowRoles = knowRoles;
    }
    public ArrayList<EffectList> getEffects() {
        return effects;
    }
    public void setEffects(ArrayList<EffectList> effects) {
        this.effects = effects;
    }
    public void addEffect(EffectList effect){ this.effects.add(effect); }
    public void removeEffect(EffectList effect){ this.effects.remove(effect); }
    public boolean hasTransformation(){
        return false;
    }
    public void onTransformation(String cdName, int cd){
        addTask(cdName, new BukkitRunnable() {
            @Override
            public void run() {
                onTransformationEnd();
            }
        }.runTaskLater(BlackCloverUHC.getInstance(), cd));
    }
    public void onTransformationEnd(){

    }

    public HashMap<String, BukkitTask> getTasks() {
        return tasks;
    }
    public void setTasks(HashMap<String, BukkitTask> tasks) {
        this.tasks = tasks;
    }
    public void addTask(String name, BukkitTask task){
        this.tasks.put(name, task);
    }
    public void removeTask(String name){
        this.tasks.remove(name);
    }
    public void cancelTask(String name){
        this.tasks.get(name).cancel();
    }
}
