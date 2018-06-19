package SpawningMamager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import Core.LangManager;
import Core.LoaderManager;
import Core.LangManager.LangHelper;
import Core.MyHeroMain;
import Core.MyHeroMain_Items;
import MobManager.MobManager;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector2;

public class SpawningManager {
	//public static HashMap<String,Spawner> AllSpawners = new HashMap<>();
	public static List<Spawner> AllSpawners = new ArrayList<Spawner>();

	@SuppressWarnings("unchecked")
	public static void Load()
	{
		//File SpawnFileRoot = new File(MyHeroMain.Main.getDataFolder() + "../MyHero/Spawners/");
		//File[] SpawnsFileList = SpawnFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(SpawnFileRoot.getPath());
		
		String path = MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "");
		File SpawnFileRoot = new File(path + "/MyHero/Spawners/");
		File[] SpawnsFileList = SpawnFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(MobFileRoot.getPath());
		
		if(!SpawnFileRoot.exists())
			SpawnFileRoot.mkdirs();
		LoaderManager.saveResource("SpawnTest.yml","SpawnTest.yml", false,"/MyHero/Spawners/");
		
		
		
		
		if(SpawnsFileList != null)
			for(File SpawnsFIle : SpawnsFileList)
			{
				MyHeroMain.Main.getLogger().info(SpawnsFIle.getPath());
				if(SpawnsFIle.isFile())
				{
					if(SpawnsFIle.getName().contains(".yml"))
					{
						
						Yaml SpawnsFIleYML = new Yaml();
						SpawnsFIleYML.load(SpawnsFIle.getPath());
						try
						{
							Map<String, Map<String, Object>> Spawns = SpawnsFIleYML.loadAs(Files.newInputStream(Paths.get(SpawnsFIle.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Spawn : Spawns.entrySet())
							{
								Spawner spawner = new Spawner();
								Map<String, Object> spawnoption = Spawn.getValue();
								try
								{
									Region r = new Region();
									
									r.setP1(new Vector2((int)spawnoption.get("X1"), (int)spawnoption.get("Y1")));
									r.setP2(new Vector2((int)spawnoption.get("X2"), (int)spawnoption.get("Y2")));
									spawner.setRegion(r);
									
									Level l = MyHeroMain.Main.getServer().getLevelByName((String)spawnoption.get("World"));
									if(l != null)
										spawner.setLevel(l);
									else
										break;
									
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
										if(MobManager.Mobs.containsKey(mobchance[0]))
											spawner.addMob(MobManager.Mobs.get(mobchance[0]).getRoot(),Integer.parseInt(mobchance[1]));
										else
											MyHeroMain.Main.getLogger().info(LangManager.Mod_do_not_exist.replace(LangHelper.MobName.toString(), mobchance[0]));
									}
									spawner.generateSpawnPoints();
									//AllSpawners.put(Spawn.getKey(), spawner);
									AllSpawners.add(spawner);
									
								}
								catch(NumberFormatException ex)
								{
									MyHeroMain.Main.getLogger().info(LangManager.Numerc_Error_Spawner.replace(LangHelper.SpawnerName.toString(), Spawn.getKey()));
								}
								catch(Exception ex)
								{
									MyHeroMain.Main.getLogger().info(ex.getMessage());
									
									MyHeroMain.Main.getLogger().info(LangManager.Spawner_Not_Loaded.replace(LangHelper.SpawnerName.toString(), Spawn.getKey()));
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
