package fr.golderpotato.waitingqueue.bungee;

import fr.golderpotato.waitingqueue.listener.event.listener.BungeeListener;
import fr.golderpotato.waitingqueue.mq.RabbitMQ;
import fr.golderpotato.waitingqueue.runnable.Queue;
import fr.golderpotato.waitingqueue.server.Server;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetSocketAddress;
import java.util.*;

/**
 * Created by Eliaz on 11/05/2017.
 */
public class BungeePlugin extends Plugin{

    private static BungeePlugin instance;
    public HashMap<String, Server> servers = new HashMap<>();
    public int count = 0;
    public Queue runnable;

    @Override
    public void onEnable() {
        instance = this;
        new BungeeListener();
        runnable = new Queue();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static BungeePlugin getInstance(){
        return instance;
    }

    public void addServer(Server server){
        this.servers.put(server.getName(), server);
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1" , server.getPort());
        ServerInfo info = ProxyServer.getInstance().constructServerInfo(server.getName(), socketAddress, "kinda cool modtd tbf", false);
        ProxyServer.getInstance().getServers().put(info.getName(), info);
    }

    public void removeServer(Server server){
        this.servers.remove(server.getName());
        ProxyServer.getInstance().getServers().remove(server.getName());
    }

    public HashMap<String, Server> getServers(){
        return servers;
    }
}
