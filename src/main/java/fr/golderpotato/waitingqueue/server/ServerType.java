package fr.golderpotato.waitingqueue.server;

import fr.golderpotato.waitingqueue.mq.RabbitMQ;

/**
 * Created by Eliaz on 11/05/2017.
 */
public enum  ServerType {

    HUB("hub", "Hub", false, "AIR"),
    HG("hg","§l§eHungerGames", true, "GOLD_SWORD"),
    SKYWARS("skywars", "§l§2SkyWars", true, "GRASS"),
    BEDWARS("bedwars", "§l§cBedWars", true, "BED"),
    ALL("all", "ALL", false, "AIR");

    private String name;
    private String displayName;
    private boolean render;
    private String material;

    ServerType(String name, String displayName, boolean render, String material){
        this.name = name;
        this.displayName = displayName;
        this.render = render;
        this.material = material;
    }

    public String getName(){
        return this.name;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public void queue(String player){
        RabbitMQ.getInstance().publish("waitingqueue", "queue", player+";"+name.toUpperCase());
    }

    public static ServerType getByName(String name){
        ServerType toReturn = null;
        for(ServerType serverType : values()){
            if(serverType.displayName.equalsIgnoreCase(name)){
                toReturn = serverType;
                break;
            }
        }
        return toReturn;
    }

    public boolean doRender(){
        return this.render;
    }

    public String getMaterial(){
        return material;
    }

}
