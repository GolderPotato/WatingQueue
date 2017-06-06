package fr.golderpotato.waitingqueue.server;

/**
 * Created by Eliaz on 11/05/2017.
 */
public enum ServerState {

    OPEN(true, true),
    RESTRICTED(false, true),
    CLOSED(false, false);

    private boolean canJoin;
    private boolean opJoin;

    ServerState(boolean canJoin, boolean opJoin){
        this.canJoin = canJoin;
        this.opJoin = opJoin;
    }

    public boolean canJoin(){
        return canJoin;
    }

    public boolean canOpJoin(){
        return opJoin;
    }

}
