package fr.golderpotato.waitingqueue.menu;

import fr.golderpotato.waitingqueue.server.ServerType;
import fr.golderpotato.waitingqueue.util.NameUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Eliaz on 05/06/2017.
 */
public class Menu implements Listener{

    public Inventory main;
    public Inventory game;
    public Player player;

    public Menu(Player player){
        this.player = player;
        this.main = Bukkit.createInventory(null, 27, "Jeux");
        this.game = Bukkit.createInventory(null, 27, "REJOINDRE");
    }

    public void openMenu(){
        for(ServerType serverType : ServerType.values()) {
            if(serverType.doRender()){
                ItemStack itemStack = new ItemStack(Material.valueOf(serverType.getMaterial()));
                ItemMeta meta = itemStack.getItemMeta();
                meta.setDisplayName(serverType.getDisplayName());
                itemStack.setItemMeta(meta);
                main.addItem(itemStack);
            }
        }
        player.openInventory(this.main);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equals(main.getTitle())){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && ServerType.getByName(event.getCurrentItem().getItemMeta().getDisplayName()) != null){
                game.clear();
                ItemStack item = new ItemStack(Material.FEATHER);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("Rejoindre une partie de "+event.getCurrentItem().getItemMeta().getDisplayName());
                item.setItemMeta(meta);
                game.setItem(13, item);
                player.openInventory(game);
            }
        }else if(player.getOpenInventory().getTitle().equals(game.getTitle())){
            if(event.getCurrentItem() != null){
                event.setCancelled(true);
                if(!event.getCurrentItem().getType().equals(Material.FEATHER))return;
                player.sendMessage(NameUtils.getPrefix()+"Traitement de la requete...");
                String s = event.getCurrentItem().getItemMeta().getDisplayName().replace("Rejoindre une partie de ", "");
                ServerType serverType = null;
                for(ServerType type : ServerType.values()){
                    if(type.getDisplayName().equals(s)){
                        serverType = type;
                    }
                }
                serverType.queue(player.getName());
            }
        }
    }

}
