package net.steepout.plugin.sd;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player){
			if(sender.isOp())
				if(adminCommand(args,sender))return true;
		}else{
			if(adminCommand(args,sender))return true;
		}
		if(args.length==0){
			help(sender);
		}else if(args.length==1){
			if(args[0].equalsIgnoreCase("info")){
				sender.sendMessage("Skull-Dropping Plugin v1.0.0 by Stephan_w(Phosphorus15).");
			}else if(args[0].equalsIgnoreCase("help")||args[0].equals("?")){
				help(sender);
			}
		}
		return true;
	}
	
	public boolean adminCommand(String[] args,CommandSender sender){
		if(args.length>1){
			if(args[0].equalsIgnoreCase("ratio")){
				PluginMain.ratio=Util.parseRatio(args[1]);
				PluginMain.saveCFG();
				sender.sendMessage(ChatColor.GREEN+"[Skull-Dropping] Succeed!");
				return true;
			}
		}else if(args.length==1){
			if(args[0].equalsIgnoreCase("ratio")){
				sender.sendMessage(ChatColor.GOLD+"[Skull-Dropping] Current ratio is:"+PluginMain.ratio+"%");
			return true;
			}
			else if(args[0].equalsIgnoreCase("enable")){
				PluginMain.enable=true;
				PluginMain.saveCFG();
				sender.sendMessage(ChatColor.GREEN+"[Skull-Dropping] Succeed!");
				return true;
			}else if(args[0].equalsIgnoreCase("disable")){
				PluginMain.enable=false;
				PluginMain.saveCFG();
				sender.sendMessage(ChatColor.GREEN+"[Skull-Dropping] Succeed!");
				return true;
			}else if(args[0].equalsIgnoreCase("reload")){
				PluginMain.loadCFG();
				sender.sendMessage(ChatColor.GREEN+"[Skull-Dropping] reloaded plugin successfully!");
				return true;
			}
		}
		return false;
	}
	
	void help(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN+"======ALL COMMANDS OF SKULL-DROPPING PLUGIN======");
		sender.sendMessage("/sd info - view information of this plugin");
		sender.sendMessage("/sd help | /sd ? - show these text");
		if(sender.isOp()){
			sender.sendMessage("/sd ratio <rat> - set the ratio");
			sender.sendMessage("/sd ratio - view current ratio");
			sender.sendMessage("/sd enable - enable this plugin");
			sender.sendMessage("/sd disable - disable this plugin");
			sender.sendMessage("/sd reload - reload this plugin");
		}
		sender.sendMessage(ChatColor.GREEN+"---------Phosphorus15");
	}
	
}