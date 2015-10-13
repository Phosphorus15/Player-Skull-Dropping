package net.steepout.plugin.sd;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GlobalListener implements Listener{
	
	HashMap<String,String> killers = new HashMap<String,String>();
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerKill(PlayerDeathEvent e){
		if(e.getEntityType()==EntityType.PLAYER){
			Player player = e.getEntity();
			if(killers.containsKey(player.getName())){
				String killer = killers.get(player.getName());
				killers.remove(player.getName());
				ItemStack item = Util.getTool(killer);
				if(PluginMain.enable)
				if(Util.isWeapon(item)&&!Util.hasMark(item)){
					int level = Util.levelOfEnchantment(item);
					if(Util.randomDropping(level)){
						Util.dropSkull(player.getName());
						Bukkit.getPlayer(killer).setItemInHand(Util.mark(player.getName(), item));
					}
				}
			}
		}
	}
	
	@EventHandler(priority=EventPriority.MONITOR)//this method is used to mark the killer of the players
	public void onPlayerDamage(EntityDamageByEntityEvent e){
		if(e.getEntityType()==EntityType.PLAYER&&e.getDamager() instanceof Player){//if both victim and killer are player
			Player player = (Player) e.getEntity();
			Player damager = (Player) e.getDamager();
			killers.put(player.getName(), damager.getName());
		}
	}
	
}