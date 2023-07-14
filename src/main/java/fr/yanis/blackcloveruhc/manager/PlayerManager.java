package fr.yanis.blackcloveruhc.manager;

import fr.yanis.blackcloveruhc.list.RoleList;
import fr.yanis.blackcloveruhc.roles.Role;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager {

    private RoleList role;
    private boolean isDead = false;

    private static HashMap<Player, PlayerManager> players = new HashMap<Player, PlayerManager>();

    public PlayerManager(Player player, RoleList role) {
        this.role = role;
        players.put(player, this);
    }

    public static HashMap<Player, PlayerManager> getPlayers() {
        return players;
    }
    public static PlayerManager getPlayer(Player player){
        return players.get(player);
    }
    public static void removePlayer(Player player){
        players.remove(player);
    }

    public RoleList getRole() {
        return role;
    }
    public void setRole(RoleList role) {
        this.role = role;
    }
    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
