package fr.golderpotato.waitingqueue.server;

/**
 * Created by Eliaz on 11/05/2017.
 */
public class Server {

    private int id;
    private int port;
    private String ip;
    private String name;
    private ServerStatus serverStatus;
    private ServerState serverState;
    private ServerType serverType;
    private int slots;
    private int online;

    public Server(ServerType serverType){
        this.serverType = serverType;
        this.serverStatus = ServerStatus.OFFLINE;
        this.serverState = ServerState.OPEN;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPort(){
        return this.port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ServerStatus getServerStatus(){
        return this.serverStatus;
    }

    public void setServerStatus(ServerStatus status){
        this.serverStatus = status;
    }

    public ServerState getServerState(){
        return this.serverState;
    }

    public void setServerState(ServerState state){
        this.serverState = state;
    }

    public ServerType getServerType(){
        return this.serverType;
    }

    public void setServerType(ServerType serverType){
        this.serverType = serverType;
    }

    public int getSlots(){
        return this.slots;
    }

    public void setSlots(int slots){
        this.slots = slots;
    }

    public int getOnline(){
        return this.online;
    }

    public void setOnline(int online){
        this.online = online;
    }

    public void add(){
        ServerManager.addServer(this);
    }

    public void sync(){
        ServerManager.updateServer(this);
    }

    public void crush(){
        ServerManager.removeServer(this);
    }

}
