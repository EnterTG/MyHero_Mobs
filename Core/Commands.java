package Core;

import ItemManager.ItemStackManager;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;

public class Commands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		MyHeroMain.Main.getLogger().info("Komenda");
		if(args.length > 0)
			switch (args[0]) 
			{
				case "mobs":
					MyHeroMain.Main.getLogger().info("Mob stworzony");
					//MobManager.Mobs.get("Test1").getSource().SpawnEntity(((Player)sender).getLevel(),((Player)sender).getLocation());
					break;
				case "items":
					if(args.length > 1)
					{
						String ItemName = args[1];
						if( ItemStackManager.ItemList.containsKey(ItemName))
							if(sender instanceof Player)
								((Player)sender).getInventory().addItem(ItemStackManager.ItemList.get(ItemName).getItem());
						else
								sender.sendMessage(LangManager.Prefix);
					}
					break;
				
			}
		return true;
	}
}
