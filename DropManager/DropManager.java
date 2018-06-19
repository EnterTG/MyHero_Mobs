package DropManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import Core.LangManager;
import Core.LoaderManager;
import Core.LangManager.LangHelper;
import Core.MyHeroMain;
import Core.MyHeroMain_Items;
import ItemManager.ItemStackManager;
import cn.nukkit.item.Item;

public class DropManager {

	public static HashMap<String,Drop> AllDrops = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static void Load()
	{
		
		//MyHeroMain.Main.getLogger().info(MobFileRoot.getPath());
		String path = MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "");
		File DropsFileRoot = new File(path + "/MyHero/Drops/");
		File[] DropsFileList = DropsFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(MobFileRoot.getPath());
		
		if(!DropsFileRoot.exists())
			DropsFileRoot.mkdirs();
		LoaderManager.saveResource("DropTest.yml","DropTest.yml", false,"/MyHero/Drops/");
		
		
		
		
		if(DropsFileList != null)
			for(File MobsFile : DropsFileList)
			{
				//MyHeroMain.Main.getLogger().info(MobsFile.getPath());
				if(MobsFile.isFile())
				{
					if(MobsFile.getName().contains(".yml"))
					{
						
						Yaml MobsFileYML = new Yaml();
						MobsFileYML.load(MobsFile.getPath());
						try
						{
							Map<String, Map<String, Object>> Drops = MobsFileYML.loadAs(Files.newInputStream(Paths.get(MobsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Drop : Drops.entrySet())
							{
								if(Drop.getValue().containsKey("Drop")) {
									Drop newdrop = new Drop();
									List<String> ItemsList = (List<String>)Drop.getValue().get("Drop");
									for(String DropItemString : ItemsList)
									{
										String[] ItemStrings = DropItemString.split(" ");
										MyHeroMain.Main.getLogger().info(DropItemString);
										if(ItemStrings.length > 0)
										{
											if(ItemStackManager.ItemList.containsKey(ItemStrings[0]))
											{
												ChanceItem cahnceitem = null;
												if(ItemStrings.length > 1)
													try
													{
														cahnceitem = new ChanceItem((Item) ItemStackManager.ItemList.get(ItemStrings[0]).getItem(), Double.parseDouble(ItemStrings[1]));
													}
													catch(Exception ex)
													{
														MyHeroMain.Main.getLogger().info(ex.getMessage());
														//To nie liczba
														continue;
													}
												else
													cahnceitem = new ChanceItem((Item) ItemStackManager.ItemList.get(ItemStrings[0]).getItem(), 1d);
												if(cahnceitem != null)
												{
													if(ItemStrings.length > 2)
														try
														{
															cahnceitem.setAmountmin(Integer.parseInt(ItemStrings[2]));
														}
														catch(Exception ex)
														{
															MyHeroMain.Main.getLogger().info(ex.getMessage());
															//To nie liczba
															continue;
														}
													if(ItemStrings.length > 3)
														try
														{
															cahnceitem.setAmountmax(Integer.parseInt(ItemStrings[3]));
														}
														catch(Exception ex)
														{
															MyHeroMain.Main.getLogger().info(ex.getMessage());
															//To nie liczba
															continue;
														}
												}
												newdrop.addItem(cahnceitem);
											}
											else	
											{
												
												MyHeroMain.Main.getLogger().info(LangManager.Item_Do_Not_Exist.replace("%Item_Name%", ItemStrings[0]));
												//item nie istnieje
											}
										}
										else
										{
											MyHeroMain.Main.getLogger().info(LangManager.Drop_Error.replace(LangHelper.DropName.toString(), ItemStrings[0]));
											//Blad w  dropie
										}
										
									}
									if(Drop.getValue().containsKey("DropChance"))
										newdrop.setDropChance((Double)Drop.getValue().get("DropChance"));
									if(Drop.getValue().containsKey("MinDrop"))
										newdrop.setMinDrop((Integer)Drop.getValue().get("MinDrop"));
									if(Drop.getValue().containsKey("MaxDrop"))
										newdrop.setMaxDrop((Integer)Drop.getValue().get("MaxDrop"));
									
									AllDrops.put(Drop.getKey(), newdrop);
									
								}
								else
								{
									//Wysalc wiadomosc ze drop zly
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
