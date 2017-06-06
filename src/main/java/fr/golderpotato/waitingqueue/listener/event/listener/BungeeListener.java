package fr.golderpotato.waitingqueue.listener.event.listener;

import fr.golderpotato.waitingqueue.bungee.BungeePlugin;
import fr.golderpotato.waitingqueue.server.*;
import fr.golderpotato.waitingqueue.util.NameUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
/**
 * Created by Eliaz on 04/06/2017.
 */
public class BungeeListener extends RabbitListener{

    @Override
    public void receive(ServerAction action, int id, int port, String name, int slots, int online, ServerStatus status, ServerState state, ServerType type) {
        System.out.println("> "+port);
        switch (action){
            case CREATE:
                System.out.println("SERVER ADDED > "+name);
                Server server = new Server(type);
                server.setId(BungeePlugin.getInstance().count+1);
                server.setPort(port);
                server.setName(name);
                server.setSlots(slots);
                server.setOnline(online);
                server.setServerStatus(status);
                server.setServerState(state);
                boolean add = true;
                for(Server servers : BungeePlugin.getInstance().getServers().values()){
                    if(servers.getName() == server.getName()){
                        add = false;
                        break;
                    }
                }
                if(add == true){
                    BungeePlugin.getInstance().addServer(server);
                }
                BungeePlugin.getInstance().runnable.findServer(server);
                break;
            case REMOVE:
                System.out.println("SERVER REMOVED > "+name);
                Server server1 = new Server(type);
                server1.setId(id);
                server1.setPort(port);
                server1.setName(name);
                server1.setSlots(slots);
                server1.setOnline(online);
                server1.setServerStatus(status);
                server1.setServerState(state);
                BungeePlugin.getInstance().removeServer(server1);
                break;
            case UPDATE:
                System.out.println("SERVER UPDATED > "+name);
                Server server2 = new Server(type);
                server2.setId(id);
                server2.setPort(port);
                server2.setName(name);
                server2.setSlots(slots);
                server2.setOnline(online);
                server2.setServerStatus(status);
                server2.setServerState(state);
                BungeePlugin.getInstance().servers.replace(server2.getName(), server2);
                BungeePlugin.getInstance().runnable.findServer(server2);
                break;
            default:
                break;
        }
    }


    @Override
    public void queue(String player, String serverType) {
        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);
        pp.sendMessage(new TextComponent(NameUtils.getPrefix()+"Requete recue avec succès..."));
        ServerType type = ServerType.valueOf(serverType);
        pp.sendMessage(new TextComponent(NameUtils.getPrefix()+"Vous avez été ajouté à la file pour "+type.getDisplayName()));
        for(Server server : BungeePlugin.getInstance().getServers().values()){
            if(server.getServerType().equals(type) && server.getServerStatus().equals(ServerStatus.ONLINE) && server.getServerState().equals(ServerState.OPEN) && server.getOnline() < server.getSlots()){
                ServerInfo info = ProxyServer.getInstance().getServerInfo(server.getName());
                pp.sendMessage(new TextComponent(NameUtils.getPrefix()+"Partie trouvée! Connection sur "+server.getName()));
                pp.connect(info);
                return;
            }
        }
        pp.sendMessage(NameUtils.getPrefix()+"Il semblerait qu'aucune partie n'ai été trouvée! Veuillez patienter avant qu'une partie se libère! ");
        BungeePlugin.getInstance().runnable.queues.get(type).add(pp);
    }
}
