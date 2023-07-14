package fr.yanis.blackcloveruhc.manager;

import fr.yanis.blackcloveruhc.roles.Role;

import java.util.ArrayList;

public class ConfigManager {
    private ArrayList<Role> activeRoles = new ArrayList<>();
    private int role_episode;
    private int pvp_time;
    private int protection_time;

    public ConfigManager(int role_episode, int pvp_time, int protection_time, ArrayList<Role> activeRoles) {
        this.role_episode = role_episode;
        this.pvp_time = pvp_time;
        this.protection_time = protection_time;
        this.activeRoles = activeRoles;
    }

    public ArrayList<Role> getActiveRoles() {
        return activeRoles;
    }
    public void addActiveRole(Role role) {
        activeRoles.add(role);
    }
    public void removeActiveRole(Role role) {
        activeRoles.remove(role);
    }
    public int getRoleEpisode() {
        return role_episode;
    }
    public void setRoleEpisode(int role_episode) {
        this.role_episode = role_episode;
    }
    public int getPvpTime() {
        return pvp_time;
    }
    public void setPvpTime(int pvp_time) {
        this.pvp_time = pvp_time;
    }
    public int getProtectionTime() {
        return protection_time;
    }
    public void setProtectionTime(int protection_time) {
        this.protection_time = protection_time;
    }
}
