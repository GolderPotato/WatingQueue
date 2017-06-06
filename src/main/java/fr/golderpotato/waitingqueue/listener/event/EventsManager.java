package fr.golderpotato.waitingqueue.listener.event;

import fr.golderpotato.waitingqueue.listener.event.events.PlayerJoin;
import fr.golderpotato.waitingqueue.menu.Menu;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Eliaz on 11/05/2017.
 */
public class EventsManager {

    private Plugin plugin;
    private PluginManager pluginManager;

    public EventsManager(Plugin plugin){
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
    }

    public void registerEvents(){
        register(new PlayerJoin());
        register(new Menu(null));
    }

    private void register(Listener listener){
        this.pluginManager.registerEvents(listener, this.plugin);
    }
}
