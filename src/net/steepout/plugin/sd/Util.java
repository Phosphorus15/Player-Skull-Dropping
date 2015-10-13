package net.steepout.plugin.sd;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util{
	public static ItemStack getTool(String player){
		return Bukkit.getPlayer(player).getItemInHand();
	}
	public static boolean isWeapon(ItemStack item){
		Material mats[] = new Material[]{Material.WOOD_SWORD,Material.STONE_SWORD,Material.IRON_SWORD,Material.DIAMOND_SWORD};
		for(Material mat:mats){
			if(item.getType().equals(mat)){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static int levelOfEnchantment(ItemStack item){
		if(PluginMain.need_enchant)
		return item.getEnchantmentLevel(Enchantment.getById(PluginMain.enchantment));
		else
		return PluginMain.dl;
	}
	
	public static ItemStack mark(String player,ItemStack item){
		ItemMeta meta;
		List<String> lores;
		if(item.hasItemMeta()){
			meta=item.getItemMeta();
			if(meta.hasLore()){
				lores=meta.getLore();
			}else{
				lores= new LinkedList<String>();
			}
		}else{
			meta=Bukkit.getItemFactory().getItemMeta(item.getType());
			lores=new LinkedList<String>();
		}
		List<String> tmp= new LinkedList<String>(lores);
		if((tmp=parseLore(tmp))!=null){
			lores.add(ChatColor.GOLD+"Bloods count: 1");
		}else{
			lores=tmp;
		}
		if(PluginMain.record)
		lores.add("- "+ChatColor.RED+player+"'s blood");
		meta.setLore(lores);
		item.setItemMeta(meta);
		return item;
	}
	
	public static List<String> parseLore(List<String> lores){
		boolean found=false;
		String target="";
		for(String x:lores){
			if(x.contains("Bloods count:")){
				found=true;
				target=x;
			}
		}
		if(found){
			int index=lores.indexOf(target);
			int level=Integer.parseInt(target.replaceAll(ChatColor.GOLD+"Bloods count: ", ""));
			lores.set(index, ChatColor.GOLD+"Bloods count: "+(level+1));
			return lores;
		}else{
			return null;
		}
	}
	
	public static boolean hasMark(ItemStack item){
		int count=0;
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				for(String x:item.getItemMeta().getLore()){
					if(x.contains("Bloods count:")){
						return Integer.parseInt(x.replaceAll(ChatColor.GOLD+"Bloods count: ", ""))>=PluginMain.max;
					}
				}
			}
		}else{
			return false;
		}
		return false;
	}
	
	public static void dropSkull(String target){
		Player tg = Bukkit.getPlayer(target);
		World world=tg.getWorld();
		world.dropItem(tg.getLocation(), SkullFactory.SkullByPlayer(tg));
	}
	
	public static boolean randomDropping(int level){
		if(level==0||level==-1)return false;
		Random rand = new Random();
		return rand.nextInt(100)<(PluginMain.ratio*level);
	}
	
	public static int parseRatio(String data){
		if(data.endsWith("%")){
			return Integer.parseInt(data.replaceAll("%", ""));
		}else{
			data=data.toUpperCase();
			switch(data){
			case "NORMAL":
				return 15;
			case "ALWAYS":
				return 35;
			case "NEVER":
				return 0;
			case "HARDLY":
				return 5;
			case "SOMETIMES":
				return 15;
			case "OFTEN":
				return 20;
			case "USUALLY":
				return 27;
			case "SELDOM":
				return 7;
			case "RARELY":
				return 1;
			}
		}
		return 0;
	}
	
}