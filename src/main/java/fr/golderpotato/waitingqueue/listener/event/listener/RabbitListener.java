package fr.golderpotato.waitingqueue.listener.event.listener;

import fr.golderpotato.waitingqueue.mq.RabbitMQ;
import fr.golderpotato.waitingqueue.server.ServerAction;
import fr.golderpotato.waitingqueue.server.ServerState;
import fr.golderpotato.waitingqueue.server.ServerStatus;
import fr.golderpotato.waitingqueue.server.ServerType;

/**
 * Created by Eliaz on 04/06/2017.
 */
public abstract class RabbitListener implements RabbitMQ.ISubscriber{

    public RabbitListener(){
        RabbitMQ.getInstance().subscribe(this, "waitingqueue");
    }

    @Override
    public void getMessage(String route, String message) {
        String[] args = message.split(";");
        if(route.equals("serverinfo")) {
            ServerAction action = ServerAction.getAction(args[0]);
            int id = Integer.valueOf(args[1]);
            int port = Integer.valueOf(args[2]);
            String name = args[3];
            int slots = Integer.valueOf(args[4]);
            int online = Integer.valueOf(args[5]);
            ServerStatus status = ServerStatus.valueOf(args[6].toUpperCase());
            ServerState state = ServerState.valueOf(args[7].toUpperCase());
            ServerType type = ServerType.valueOf(args[8].toUpperCase());
            receive(action, id, port, name, slots, online, status, state, type);
            return;
        }
        if(route.equals("queue")){
            queue(args[0], args[1]);
        }
    }

    public void receive(ServerAction action, int id, int port, String name, int slots, int online, ServerStatus status, ServerState state, ServerType type){

    }

    public void queue(String player, String serverType){

    }
}
