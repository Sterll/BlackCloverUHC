package fr.yanis.blackcloveruhc.roles;

import fr.yanis.blackcloveruhc.BlackCloverUHC;
import fr.yanis.blackcloveruhc.list.EffectList;
import fr.yanis.blackcloveruhc.list.RoleList;
import fr.yanis.blackcloveruhc.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Asta extends Role {

    @Override
    public void onDay() {
        super.onDay();
        setEffects(new ArrayList<>());
    }

    @Override
    public void onNight() {
        super.onNight();
        setEffects((ArrayList<EffectList>) Collections.singletonList(EffectList.STRENGTH1));
    }

    @Override
    public void onReceive(Player player) {
        super.onReceive(player);
        getPlayer().setMaxHealth(24);
        Random random = new Random();
        int i = random.nextInt(4);
        if(i == 0){
            getPlayer().getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§0Drainda").toItemStack());
        } else if (i == 1){
            getPlayer().getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§0Dispelda").toItemStack());
        } else if (i == 2){
            getPlayer().getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§0Annihilda").toItemStack());
        }
        getPlayer().getInventory().addItem(new ItemBuilder(Material.NETHER_STAR).setName("§0Black Asta").setLore(Arrays.asList("", "§c§nPermet de te transformer sous ta forme démoniaque")).toItemStack());
    }

    @Override
    public void setKnowRoles(ArrayList<Role> knowRoles) {
        super.setKnowRoles((ArrayList<Role>) Collections.singletonList(RoleList.YUNO.getInstance()));
    }
    @Override
    public boolean hasTransformation() {
        return true;
    }

    @Override
    public void onTransformation(String cdName, int cd) {
        super.onTransformation(cdName, 20 * (60 * 5));
        addEffect(EffectList.SPEED1);
        addEffect(EffectList.RESISTANCE1);
    }

    @Override
    public void onTransformationEnd() {
        super.onTransformationEnd();
        removeEffect(EffectList.SPEED1);
        removeEffect(EffectList.RESISTANCE1);
        cancelTask("transformation");
    }
}
