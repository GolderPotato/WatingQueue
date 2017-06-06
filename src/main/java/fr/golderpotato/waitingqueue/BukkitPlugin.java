package fr.golderpotato.waitingqueue;

import fr.golderpotato.waitingqueue.commands.Menu;
import fr.golderpotato.waitingqueue.listener.event.EventsManager;
import fr.golderpotato.waitingqueue.listener.event.listener.BukkitListener;
import fr.golderpotato.waitingqueue.mq.RabbitMQ;
import fr.golderpotato.waitingqueue.server.Server;
import fr.golderpotato.waitingqueue.server.ServerState;
import fr.golderpotato.waitingqueue.server.ServerStatus;
import fr.golderpotato.waitingqueue.server.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Eliaz on 11/05/2017.
 */
public class BukkitPlugin extends JavaPlugin{

    private static BukkitPlugin instance;
    private Server server;

    private int id;
    private int port;
    private String ip;
    private String name;
    private ServerStatus serverStatus;
    private ServerState serverState;
    private ServerType serverType;
    private int slots;
    private int online;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        RabbitMQ.getInstance();

        new BukkitListener();
        new EventsManager(this).registerEvents();

        getCommand("menu").setExecutor(new Menu());

        this.server = new Server(ServerType.valueOf(getConfig().getString("server.type").toUpperCase()));
        this.server.setName(getConfig().getString("server.name"));
        this.server.setPort(Bukkit.getPort());
        this.server.setIp(Bukkit.getIp());
        this.server.setServerState(ServerState.OPEN);
        this.server.setServerStatus(ServerStatus.ONLINE);
        this.server.setSlots(getServer().getMaxPlayers());
        this.server.setOnline(this.getServer().getOnlinePlayers().size());
        this.server.add();
    }

    @Override
    public void onDisable() {
        if(this.server != null){
            this.server.setServerStatus(ServerStatus.OFFLINE);
            this.server.setServerState(ServerState.CLOSED);
            this.server.crush();
        }
    }

    public static BukkitPlugin getInstance(){
        return instance;
    }

    public Server getServ(){
        return this.server;
    }

    public void syncServer(){
        this.server.sync();
    }
}
