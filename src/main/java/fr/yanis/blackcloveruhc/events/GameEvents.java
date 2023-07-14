package fr.yanis.blackcloveruhc.events;

import fr.yanis.blackcloveruhc.BlackCloverUHC;
import fr.yanis.blackcloveruhc.events.custom.EpisodeChangeEvent;
import fr.yanis.blackcloveruhc.events.custom.GameStartEvent;
import fr.yanis.blackcloveruhc.list.RoleList;
import fr.yanis.blackcloveruhc.list.TimeList;
import fr.yanis.blackcloveruhc.manager.GameManager;
import fr.yanis.blackcloveruhc.utils.CustomWorldGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEvents implements Listener {

    @EventHandler
    public void onGameStart(GameStartEvent e) {
        // Crée le monde
        WorldCreator worldCreator = new WorldCreator("UHCMAP");
        worldCreator.generator(new CustomWorldGenerator());
        Bukkit.getServer().createWorld(worldCreator);

        // Téléporte les joueurs & créer une cage autour d'eux

        int centerX = 0;
        int centerY = 150;
        int centerZ = 0;
        int radius = 1000;
        double angleBetweenEach = 2 * Math.PI / e.getGame().getAlive().size();

        for (int i = 0; i < e.getGame().getAlive().size(); i++) {
            Player player = e.getGame().getAlive().get(i);
            // Calcul de l'angle pour ce joueur
            double angle = angleBetweenEach * i;
            int x = centerX + (int) (radius * Math.cos(angle));
            int z = centerZ + (int) (radius * Math.sin(angle));
            Location location = new Location(Bukkit.getWorld("UHCMAP"), x, centerY, z);
            player.teleport(location);
            int cageRadius = 2;
            for (int xc = -cageRadius; xc <= cageRadius; xc++) {
                for (int yc = 0; yc <= 2 * cageRadius; yc++) {
                    for (int zc = -cageRadius; zc <= cageRadius; zc++) {
                        // Si on est sur le bord de la cage, placez un bloc
                        if (Math.abs(xc) == cageRadius || yc == 0 || yc == 2 * cageRadius || Math.abs(zc) == cageRadius) {
                            location.clone().add(xc, yc, zc).getBlock().setType(Material.GLASS);
                        }
                    }
                }
            }
        }
        // Gestion de l'épisode
        Bukkit.getServer().getScheduler().runTaskTimer(BlackCloverUHC.getInstance(), () -> {
            TimeList time;
            if(e.getGame().getEpisode() % 2 == 0){
                time = TimeList.NIGHT;
            } else {
                time = TimeList.DAY;
            }
            Bukkit.getServer().getPluginManager().callEvent(new EpisodeChangeEvent(e.getGame(), e.getGame().getEpisode(), e.getGame().getEpisode() + 1, time));
        }, 0, 20 * 60 * 20);
    }

    @EventHandler
    public void onEpisodeChange(EpisodeChangeEvent e){
        GameManager gameManager = e.getGame();
        gameManager.setEpisode(e.getNewEpisode());

        // Give les rôles au joueur
        if(e.getNewEpisode() == 2){
            gameManager.giveRole();
        }

        // Gestion du temps
        if(e.getTime() == TimeList.DAY){
            Bukkit.getWorld("UHCMAP").setTime(6000);
            for (RoleList value : RoleList.values()) {
                value.getInstance().onDay();
            }
        }
        if(e.getTime() == TimeList.NIGHT){
            Bukkit.getWorld("UHCMAP").setTime(12000);
            for (RoleList value : RoleList.values()) {
                value.getInstance().onNight();
            }
        }
    }

}
