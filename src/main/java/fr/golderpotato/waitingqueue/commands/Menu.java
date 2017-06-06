package fr.golderpotato.waitingqueue.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Eliaz on 05/06/2017.
 */
public class Menu implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))return false;
        Player player = (Player) commandSender;
        new fr.golderpotato.waitingqueue.menu.Menu(player).openMenu();
        return false;
    }
}
