package fr.yanis.blackcloveruhc;

import fr.yanis.blackcloveruhc.events.GameEvents;
import fr.yanis.blackcloveruhc.events.PlayerEvents;
import fr.yanis.blackcloveruhc.events.RoleEvents;
import fr.yanis.blackcloveruhc.manager.ConfigManager;
import fr.yanis.blackcloveruhc.manager.GameManager;
import fr.yanis.blackcloveruhc.roles.Role;
import fr.yanis.blackcloveruhc.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BlackCloverUHC extends JavaPlugin {

    private static BlackCloverUHC instance;
    private GameManager gameManager;
    private ConfigManager configManager;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(this);
        gameManager = new GameManager();
        configManager = new ConfigManager(fileManager.getConfig().getInt("game.role_episode"), fileManager.getConfig().getInt("game.pvp_time"), fileManager.getConfig().getInt("game.protection_time"), (ArrayList<Role>) fileManager.getConfig().getList("game.roles"));
        getServer().getPluginManager().registerEvents(new GameEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new RoleEvents(), this);
    }

    @Override
    public void onDisable() {
        fileManager.getConfig().set("game.role_episode", configManager.getRoleEpisode());
        fileManager.getConfig().set("game.pvp_time", configManager.getPvpTime());
        fileManager.getConfig().set("game.protection_time", configManager.getProtectionTime());
        fileManager.getConfig().set("game.roles", configManager.getActiveRoles());
        fileManager.saveConfig();
    }

    public static BlackCloverUHC getInstance() {
        return instance;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
    public ConfigManager getConfigManager() {
        return configManager;
    }
    public FileManager getFileManager() {
        return fileManager;
    }
}
