package ItemManager;

import Core.LangManager;
import Core.MyHeroMain;
import MobManager.*;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ItemStackManager
{
	public static HashMap<String,AbstractItemStackOption> ItemList = new HashMap<>();
	
	public static void LoadItems()
	{
		File ItemFileRoot = new File(MyHeroMain.Main.getDataFolder() + "/Items/");
		File[] ItemFileList = ItemFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(ItemFileRoot.getPath());
		if(ItemFileList != null)
			for(File ItemsFile : ItemFileList)
			{
				//MyHeroMain.Main.getLogger().info(ItemsFile.getPath());
				if(ItemsFile.isFile())
				{
					if(ItemsFile.getName().contains(".yml"))
					{
						
						Yaml ItemsFileYML = new Yaml();
						ItemsFileYML.load(ItemsFile.getPath());
						try
						{
							Map<String, Map<String, Object> > Items = ItemsFileYML.loadAs(Files.newInputStream(Paths.get(ItemsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Item : Items.entrySet())
							{
								//MyHeroMain.Main.getLogger().info("Key: " + Item.getKey());
								if(Item.getValue() != null && Item.getValue().containsKey("ID"))
								{
									int ItemID = 0;
									try
									{
										ItemID = Integer.parseInt(Item.getValue().get("ID").toString());
									}
									catch(NumberFormatException nfe)
									{
										MyHeroMain.Main.getLogger().info(LangManager.Item_ID_Is_Not_Int.replaceAll("%Mob_Name%",Item.getKey()));
									}
									ItemStackConstructor ItemCreator = new ItemStackConstructor(ItemID);
									AbstractItemStackOption OptionLast = ItemCreator;
									//MyHeroMain.Main.getLogger().info("Item id: " + ItemID);
									for(Map.Entry<String, Object> Options : Item.getValue().entrySet())
									{
										if( ItemStackOptionManager.ListOptions.containsKey(Options.getKey()))
										{
											//MyHeroMain.Main.getLogger().info("Option add: " + Options.getKey());
											//MyHeroMain.Main.getLogger().info("Option value: " + Options.getValue().toString());
											if(Options.getValue() == null) MyHeroMain.Main.getLogger().info(LangManager.Item_Error.replaceAll("%Item_Name%",Item.getKey()));
											ItemStackOption Option = ItemStackOptionManager.ListOptions.get(Options.getKey().toString()).Create(OptionLast);
											Option.addItemStackOption(Options.getValue());
											OptionLast = Option;
											
										}
									}
									//MyHeroMain.Main.getLogger().info(LangManager.Item_Load_Succes.replaceAll("%Item_Name%",Item.getKey()) );
									ItemStackManager.ItemList.put(Item.getKey(),OptionLast);
								}
								else
								{
									MyHeroMain.Main.getLogger().info(LangManager.Item_No_ID.replaceAll("%Item_Name%",Item.getKey()));
								}
							}
						}
						catch ( IOException e )
						
						{
							e.printStackTrace();
						}
						
					}
				}
			}
	}
	
}
