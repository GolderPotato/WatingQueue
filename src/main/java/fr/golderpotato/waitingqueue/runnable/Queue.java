package fr.golderpotato.waitingqueue.runnable;

import fr.golderpotato.waitingqueue.server.Server;
import fr.golderpotato.waitingqueue.server.ServerState;
import fr.golderpotato.waitingqueue.server.ServerStatus;
import fr.golderpotato.waitingqueue.server.ServerType;
import fr.golderpotato.waitingqueue.util.NameUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eliaz on 05/06/2017.
 */
public class Queue {

    public HashMap<ServerType, ArrayList<ProxiedPlayer>> queues = new HashMap<>();

    public Queue(){
        for(ServerType types : ServerType.values()){
            queues.put(types, new ArrayList<>());
        }
    }

    public void addPlayer(ProxiedPlayer player, ServerType serverType){
        HashMap<ServerType, ProxiedPlayer> toRemove = new HashMap<>();
        for(ArrayList<ProxiedPlayer> pp : queues.values()){
            if(pp.contains(player)){
                pp.remove(player);
            }
        }
        queues.get(serverType).add(player);
    }

    public void findServer(Server server){
        ArrayList<ProxiedPlayer> toRemove = new ArrayList<>();
        if(queues.get(server.getServerType()).size() > 0){
            if(server.getServerStatus().equals(ServerStatus.ONLINE) && server.getServerState().equals(ServerState.OPEN) && server.getSlots() > server.getOnline()){
                for(ProxiedPlayer player : queues.get(server.getServerType())){
                    if(server.getSlots() > server.getOnline()){
                        if(!player.getServer().getInfo().getName().equals(server.getName())){
                            player.sendMessage(new TextComponent(NameUtils.getPrefix()+"Partie trouvée! Connection sur "+server.getName()));
                            toRemove.add(player);
                            player.connect(ProxyServer.getInstance().getServerInfo(server.getName()));
                        }else{
                            toRemove.add(player);
                            player.sendMessage(NameUtils.getPrefix()+"Vous vous trouvez déjà sur ce mode de jeu!");
                        }
                    }else{
                        return;
                    }
                }
            }
        }
        queues.get(server.getServerType()).removeAll(toRemove);
    }
}
