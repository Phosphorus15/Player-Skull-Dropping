package net.steepout.plugin.sd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullFactory{
	static Material SKULL = Material.SKULL_ITEM;
	//create a player's skull by name
	public static synchronized ItemStack SkullByName(String name){
		try{
		Log.info("created skull with "+name);
		ItemStack skl = new ItemStack(SKULL);
		skl.setDurability((short) 3);
		SkullMeta skm = (SkullMeta) Bukkit.getItemFactory().getItemMeta(SKULL);//get item meta
		skm.setOwner(name);//set skull owner
		skl.setItemMeta(skm);
		return skl;
		}catch(Throwable r){
			r.printStackTrace();
			return new ItemStack(Material.AIR);
		}
	}
	public static synchronized ItemStack SkullByPlayer(Player player){
		return SkullByName(player.getName());
	}
}