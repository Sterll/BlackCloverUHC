package fr.yanis.blackcloveruhc.events.custom;

import fr.yanis.blackcloveruhc.manager.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameEndEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private GameManager game;
    private Player winner;

    public GameEndEvent(GameManager game, Player winner) {
        this.game = game;
        this.winner = winner;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public GameManager getGame() {
        return game;
    }
    public Player getWinner() {
        return winner;
    }

}
