package me.Block2Block.HotPotato.Kits.Abilities;

import me.Block2Block.HotPotato.Main;
import me.Block2Block.HotPotato.Managers.CacheManager;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Leaper implements Listener {

    @EventHandler
    public void onLeap(PlayerToggleFlightEvent e) {
        if (e.isFlying()) {
            if (CacheManager.getPlayers().containsKey(e.getPlayer().getUniqueId())) {
                if (CacheManager.getPlayers().get(e.getPlayer().getUniqueId()).getKit().name().equals("Leaper")) {
                    e.setCancelled(true);
                    e.getPlayer().setFlying(false);
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().setY(0.3).normalize().multiply(1.175));
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.GHAST_FIREBALL, 100, 1);

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            e.getPlayer().setAllowFlight(true);
                            e.getPlayer().setFlying(false);
                        }
                    }.runTaskLater(Main.getInstance(), 10);
                }
            }
        }
    }

}
