package fr.golderpotato.waitingqueue.server;

import fr.golderpotato.waitingqueue.mq.RabbitMQ;

/**
 * Created by Eliaz on 11/05/2017.
 */
public class ServerManager {

    public static void addServer(Server server){
        RabbitMQ.getInstance().publish("waitingqueue", "serverinfo", "create;"+server.getId()+";"+server.getPort()+";"+server.getName()+";"+server.getSlots()+";"+server.getOnline()+";"+server.getServerStatus().toString()+";"+server.getServerState().toString()+";"+server.getServerType().toString());
    }

    public static void removeServer(Server server){
        RabbitMQ.getInstance().publish("waitingqueue", "serverinfo", "remove;"+server.getId()+";"+server.getPort()+";"+server.getName()+";"+server.getSlots()+";"+server.getOnline()+";"+server.getServerStatus().toString()+";"+server.getServerState().toString()+";"+server.getServerType().toString());
    }

    public static void updateServer(Server server){
        RabbitMQ.getInstance().publish("waitingqueue", "serverinfo", "update;"+server.getId()+";"+server.getPort()+";"+server.getName()+";"+server.getSlots()+";"+server.getOnline()+";"+server.getServerStatus().toString()+";"+server.getServerState().toString()+";"+server.getServerType().toString());
    }

}
