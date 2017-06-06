package fr.golderpotato.waitingqueue.server;

/**
 * Created by Eliaz on 04/06/2017.
 */
public enum ServerAction{

    CREATE("create"),
    UPDATE("update"),
    REMOVE("remove");

    String action;

    ServerAction(String action){
        this.action = action;
    }

    public static ServerAction getAction(String action){
        for(ServerAction actions : values()){
            if(actions.action.equals(action)){
                return actions;
            }
        }
        return null;
    }

}
