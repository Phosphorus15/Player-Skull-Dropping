package net.steepout.plugin.sd;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Skull-Dropping minecraft plugin by Phosphorus15
 * @author Phosphorus15
 *
 */
public class PluginMain extends JavaPlugin{
	static int ratio=0;
	static boolean enable=true;
	static int enchantment=21;
	static boolean need_enchant=true;
	static int max=1;
	static boolean record = true;
	static int dl;
	static FileConfiguration config;
	static JavaPlugin self;
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new GlobalListener(), this);//register event listener
		getCommand("sd").setExecutor(new Commands());             //register commands
		getCommand("skull-dropping").setExecutor(new Commands());
		config = getConfig();
		loadCFG();
		Log.info("[Skull-Dropping] Hooked on with ratio: "+ratio+"% (* "+dl+")");
		Log.info("Skull-Dropping Plugin v1.0.0 by Stephan_w(Phosphorus15).");
		self=this;
	}
	public void onLoad(){
		saveDefaultConfig();
	}
	public static void loadCFG(){
		enable=config.getBoolean("Enable");
		ratio=Util.parseRatio(config.getString("Ratio"));
		need_enchant=config.getBoolean("Need-Enchantment");
		enchantment=config.getInt("Enchantment-id");
		max=config.getInt("Maximize");
		record=config.getBoolean("Record");
		dl=config.getInt("Default-Level");
	}
	public static void saveCFG(){
		config.set("Enable", enable);
		config.set("Ratio", ratio);
		config.set("Need-Enchantment", need_enchant);
		config.set("Enchantment-id", enchantment);
		config.set("Maximize", max);
		config.set("Record", record);
		config.set("Default-Level", dl);
	}
	public void onDisable(){
		saveCFG();
	}
}