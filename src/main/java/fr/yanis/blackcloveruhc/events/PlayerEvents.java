package fr.yanis.blackcloveruhc.events;

import fr.yanis.blackcloveruhc.BlackCloverUHC;
import fr.yanis.blackcloveruhc.manager.GameManager;
import fr.yanis.blackcloveruhc.manager.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PlayerManager.getPlayer(player);
        GameManager gameManager = BlackCloverUHC.getInstance().getGameManager();
        if(gameManager.isStarted()) {
            gameManager.addSpectator(player);
        } else{
            gameManager.addPlayer(player);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player player = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            PlayerManager playerManager = PlayerManager.getPlayer(player);
            PlayerManager damagerManager = PlayerManager.getPlayer(damager);
            e.setDamage(damagerManager.getRole().getInstance().getDamage((float) e.getDamage()) * playerManager.getRole().getInstance().getResistance());
        }
    }

}
