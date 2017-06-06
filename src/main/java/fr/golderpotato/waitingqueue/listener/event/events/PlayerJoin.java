package fr.golderpotato.waitingqueue.listener.event.events;

import fr.golderpotato.waitingqueue.BukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Eliaz on 11/05/2017.
 */
public class PlayerJoin implements Listener{

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onJoin(final PlayerJoinEvent event){
        BukkitPlugin.getInstance().getServ().setOnline(Bukkit.getOnlinePlayers().size());
        BukkitPlugin.getInstance().syncServer();
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onLeave(final PlayerQuitEvent event){
        BukkitPlugin.getInstance().getServ().setOnline(Bukkit.getOnlinePlayers().size());
        BukkitPlugin.getInstance().syncServer();
    }

}
