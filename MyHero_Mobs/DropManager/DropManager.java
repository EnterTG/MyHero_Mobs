package MyHero_Mobs.DropManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import MyHero_Core.Core.Data;
import MyHero_Core.DataManagment.DataItems;
import MyHero_Core.DataManagment.DataMobs;
import MyHero_Core.Managers.LangManager;
import MyHero_Core.Managers.LangManager.LangHelper;
import MyHero_Core.Managers.ResourceManager;

public class DropManager {

	
	
	@SuppressWarnings("unchecked")
	public static void Load()
	{
		
		//LangManager.Log(MobFileRoot.getPath());
		//String path = MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "");
		File DropsFileRoot = new File(ResourceManager.getPathTo("Drops"));
		File[] DropsFileList = DropsFileRoot.listFiles();
		//LangManager.Log(MobFileRoot.getPath());
		
		if(!DropsFileRoot.exists())
			DropsFileRoot.mkdirs();
		ResourceManager.saveResource("DropTest.yml","DropTest.yml", false,"Drops");
		
		
		
		
		if(DropsFileList != null)
			for(File MobsFile : DropsFileList)
			{
				//LangManager.Log(MobsFile.getPath());
				if(MobsFile.isFile())
				{
					if(MobsFile.getName().contains(".yml"))
					{
						
						Yaml MobsFileYML = new Yaml();
						MobsFileYML.load(MobsFile.getPath());
						try
						{
							Data data = MyHero_Core.Core.MyHeroMain.getMyHeroData();
							DataItems dataitems = data.getDataItems();
							DataMobs datamobs = data.getDataMobs();
							
							Map<String, Map<String, Object>> Drops = MobsFileYML.loadAs(Files.newInputStream(Paths.get(MobsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Drop : Drops.entrySet())
							{
								if(Drop.getValue().containsKey("Drop")) {
									Drop newdrop = new Drop();
									List<String> ItemsList = (List<String>)Drop.getValue().get("Drop");
									for(String DropItemString : ItemsList)
									{
										String[] ItemStrings = DropItemString.split(" ");
										//LangManager.Log(DropItemString);
										if(ItemStrings.length > 0)
										{
											if(dataitems.itemExist(ItemStrings[0]))
											{
												ChanceItem cahnceitem = null;
												if(ItemStrings.length > 1)
													try
													{
														cahnceitem = new ChanceItem(dataitems.getItem(ItemStrings[0]).getRoot(), Double.parseDouble(ItemStrings[1]));
													}
													catch(Exception ex)
													{
														LangManager.Log(LangManager.Chance_Is_Not_Number.replace(LangHelper.DropName.toString(), Drop.getKey()).replaceAll(LangHelper.ItemName.toString(), DropItemString));
														//To nie liczba
														continue;
													}
												else
													cahnceitem = new ChanceItem(dataitems.getItem(ItemStrings[0]).getRoot(), 1d);
												if(cahnceitem != null)
												{
													if(ItemStrings.length > 2)
														try
														{
															cahnceitem.setAmountmin(Integer.parseInt(ItemStrings[2]));
														}
														catch(Exception ex)
														{
															LangManager.Log(LangManager.Chance_Is_Not_Number.replace(LangHelper.DropName.toString(), Drop.getKey()).replaceAll(LangHelper.ItemName.toString(), DropItemString));
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
															LangManager.Log(LangManager.Chance_Is_Not_Number.replace(LangHelper.DropName.toString(), Drop.getKey()).replaceAll(LangHelper.ItemName.toString(), DropItemString));
															//To nie liczba
															continue;
														}
												}
												newdrop.addItem(cahnceitem);
											}
											else	
											{
												
												LangManager.Log(LangManager.Item_Do_Not_Exist.replace(LangHelper.ItemName.toString(), ItemStrings[0]));
												//item nie istnieje
											}
										}
										else
										{
											LangManager.Log(LangManager.Drop_Error.replace(LangHelper.DropName.toString(), ItemStrings[0]));
											//Blad w  dropie
										}
										
									}
									if(Drop.getValue().containsKey("DropChance"))
										newdrop.setDropChance((Double)Drop.getValue().get("DropChance"));
									if(Drop.getValue().containsKey("MinDrop"))
										newdrop.setMinDrop((Integer)Drop.getValue().get("MinDrop"));
									if(Drop.getValue().containsKey("MaxDrop"))
										newdrop.setMaxDrop((Integer)Drop.getValue().get("MaxDrop"));
									
									datamobs.addDrop(Drop.getKey(), newdrop);
									
								}
								else
								{
									//Wysalc wiadomosc ze drop zly
								}
								
							}
						}
						catch ( IOException e )
						
						{
							LangManager.Log(e.getMessage());
						}
						
					}
				}
			}
	}
}
