package MyHero_Mobs.SpawningManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataMobs;
import MyHero_Core.Managers.LangManager;
import MyHero_Core.Managers.LangManager.LangHelper;
import MyHero_Core.Managers.ResourceManager;
import MyHero_Mobs.RegionsManager.Region;
import MyHero_Mobs.RegionsManager.RegionSquare;
import MyHero_Mobs.RegionsManager.RegionPoint;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector2;

public class SpawningManager {
	//public static HashMap<String,Spawner> AllSpawners = new HashMap<>();
	

	@SuppressWarnings("unchecked")
	public static void Load()
	{
		//File SpawnFileRoot = new File(MyHeroMain.Main.getDataFolder() + "../MyHero/Spawners/");
		//File[] SpawnsFileList = SpawnFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(SpawnFileRoot.getPath());
		
		
		File SpawnFileRoot = new File(ResourceManager.getPathTo("Spawners"));
		File[] SpawnsFileList = SpawnFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(MobFileRoot.getPath());
		
		if(!SpawnFileRoot.exists())
			SpawnFileRoot.mkdirs();
		ResourceManager.saveResource("SpawnTest.yml","SpawnTest.yml", false,"Spawners");
		
		
		
		
		if(SpawnsFileList != null)
			for(File SpawnsFIle : SpawnsFileList)
			{

				if(SpawnsFIle.isFile())
				{
					if(SpawnsFIle.getName().contains(".yml"))
					{
						
						Yaml SpawnsFIleYML = new Yaml();
						SpawnsFIleYML.load(SpawnsFIle.getPath());
						try
						{
							DataMobs datamobs = MyHeroMain.getMyHeroData().getDataMobs();
							Map<String, Map<String, Object>> Spawns = SpawnsFIleYML.loadAs(Files.newInputStream(Paths.get(SpawnsFIle.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Spawn : Spawns.entrySet())
							{
								
								Spawner spawner = new Spawner();
								Map<String, Object> spawnoption = Spawn.getValue();
								try
								{
									Region r = null;
									if(spawnoption.containsKey("Location"))
									{
										String s[] = spawnoption.get("Location").toString().split(" ");
										
										
										r = new RegionPoint(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
									}
									else
									{
										r = new RegionSquare();
										((RegionSquare)r).setP1(new Vector2((int)spawnoption.get("X1"), (int)spawnoption.get("Y1")));
										((RegionSquare)r).setP2(new Vector2((int)spawnoption.get("X2"), (int)spawnoption.get("Y2")));
										
									}
									
									Level l = MyHeroMain.getMain().getServer().getLevelByName((String)spawnoption.get("World"));
									if(l != null)
										r.setWorld(l);
									else
										break;
									
									spawner.setRegion(r);
									spawner.generateSpawnPoints();
									
									if(spawnoption.containsKey("MinMobs"))
										spawner.setMinMobsPerSpawn((int)spawnoption.get("MinMobs"));
									if(spawnoption.containsKey("MaxMobs"))
										spawner.setMaxMobsPerSpawn((int)spawnoption.get("MaxMobs"));
									if(spawnoption.containsKey("Chance"))
										spawner.setChance((double)spawnoption.get("Chance"));
									
									List<String> Mobsinspawner = (List<String>)spawnoption.get("Mobs");
									for(String mob : Mobsinspawner)
									{
										String[] mobchance = mob.split(":");
										if(datamobs.MobExist(mobchance[0]))
											spawner.addMob(datamobs.getMob(mobchance[0]).getRoot(),Integer.parseInt(mobchance[1]));
										else
											LangManager.Log(LangManager.Mob_not_exist.replace(LangHelper.MobName.toString(), mobchance[0]));
									}
									
									//AllSpawners.put(Spawn.getKey(), spawner);
									datamobs.addSpawner(spawner);
									LangManager.Log(spawner.toString());
									
								}
								catch(NumberFormatException ex)
								{
									LangManager.Log(LangManager.Numerc_Error_Spawner.replace(LangHelper.SpawnerName.toString(), Spawn.getKey()));
								}
								catch(Exception ex)
								{
									ex.printStackTrace();
									
									LangManager.Log(LangManager.Spawner_Not_Loaded.replace(LangHelper.SpawnerName.toString(), Spawn.getKey()));
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
