package fr.yanis.blackcloveruhc.manager;

import fr.yanis.blackcloveruhc.list.RoleList;
import fr.yanis.blackcloveruhc.list.ScenarioList;
import fr.yanis.blackcloveruhc.roles.Role;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class GameManager {

    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> spectators = new ArrayList<Player>();
    private ArrayList<Player> alive = new ArrayList<Player>();
    private ScenarioList scenario;
    private boolean isStarted = false;
    private int episode = 1;
    private BukkitTask episodeTimer;
    public void giveRole(){
        for (Player player : alive) {
            PlayerManager.getPlayer(player);
        }
    }
    public void setScenario(ScenarioList scenario) {
        this.scenario = scenario;
    }
    public ScenarioList getScenario() {
        return scenario;
    }
    public Player getPlayerWithRole(RoleList role){
        for (Player player : players) {
            if(PlayerManager.getPlayer(player).getRole() == role){
                return player;
            }
        }
        return null;
    }
    public void setEpisode(int episode) {
        this.episode = episode;
    }
    public int getEpisode() {
        return episode;
    }
    public void setStarted(boolean started) {
        isStarted = started;
    }
    public boolean isStarted() {
        return isStarted;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public ArrayList<Player> getSpectators() {
        return spectators;
    }
    public ArrayList<Player> getAlive() {
        return alive;
    }
    public BukkitTask getEpisodeTimer() {
        return episodeTimer;
    }
    public void addPlayer(Player player){
        players.add(player);
        alive.add(player);
    }
    public void addSpectator(Player player){
        spectators.add(player);
    }
    public void removeAlive(Player player){
        alive.remove(player);
    }
    public void removeSpectator(Player player){
        spectators.remove(player);
    }
}
