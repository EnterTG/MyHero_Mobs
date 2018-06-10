package MobManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import Core.LangManager;
import Core.MyHeroMain;
import de.kniffo80.mobplugin.entities.animal.flying.Bat;
import de.kniffo80.mobplugin.entities.animal.flying.Parrot;
import de.kniffo80.mobplugin.entities.animal.jumping.Rabbit;
import de.kniffo80.mobplugin.entities.animal.swimming.Squid;
import de.kniffo80.mobplugin.entities.animal.walking.Chicken;
import de.kniffo80.mobplugin.entities.animal.walking.Cow;
import de.kniffo80.mobplugin.entities.animal.walking.Donkey;
import de.kniffo80.mobplugin.entities.animal.walking.Horse;
import de.kniffo80.mobplugin.entities.animal.walking.Llama;
import de.kniffo80.mobplugin.entities.animal.walking.Mooshroom;
import de.kniffo80.mobplugin.entities.animal.walking.Mule;
import de.kniffo80.mobplugin.entities.animal.walking.Ocelot;
import de.kniffo80.mobplugin.entities.animal.walking.Pig;
import de.kniffo80.mobplugin.entities.animal.walking.PolarBear;
import de.kniffo80.mobplugin.entities.animal.walking.Sheep;
import de.kniffo80.mobplugin.entities.animal.walking.SkeletonHorse;
import de.kniffo80.mobplugin.entities.animal.walking.Villager;
import de.kniffo80.mobplugin.entities.animal.walking.ZombieHorse;
import de.kniffo80.mobplugin.entities.monster.flying.Blaze;
import de.kniffo80.mobplugin.entities.monster.flying.EnderDragon;
import de.kniffo80.mobplugin.entities.monster.flying.Ghast;
import de.kniffo80.mobplugin.entities.monster.flying.Vex;
import de.kniffo80.mobplugin.entities.monster.flying.Wither;
import de.kniffo80.mobplugin.entities.monster.jumping.MagmaCube;
import de.kniffo80.mobplugin.entities.monster.jumping.Slime;
import de.kniffo80.mobplugin.entities.monster.swimming.ElderGuardian;
import de.kniffo80.mobplugin.entities.monster.swimming.Guardian;
import de.kniffo80.mobplugin.entities.monster.walking.CaveSpider;
import de.kniffo80.mobplugin.entities.monster.walking.Creeper;
import de.kniffo80.mobplugin.entities.monster.walking.Enderman;
import de.kniffo80.mobplugin.entities.monster.walking.Endermite;
import de.kniffo80.mobplugin.entities.monster.walking.Evoker;
import de.kniffo80.mobplugin.entities.monster.walking.Husk;
import de.kniffo80.mobplugin.entities.monster.walking.IronGolem;
import de.kniffo80.mobplugin.entities.monster.walking.PigZombie;
import de.kniffo80.mobplugin.entities.monster.walking.Shulker;
import de.kniffo80.mobplugin.entities.monster.walking.Silverfish;
import de.kniffo80.mobplugin.entities.monster.walking.Skeleton;
import de.kniffo80.mobplugin.entities.monster.walking.SnowGolem;
import de.kniffo80.mobplugin.entities.monster.walking.Spider;
import de.kniffo80.mobplugin.entities.monster.walking.Stray;
import de.kniffo80.mobplugin.entities.monster.walking.Vindicator;
import de.kniffo80.mobplugin.entities.monster.walking.Witch;
import de.kniffo80.mobplugin.entities.monster.walking.WitherSkeleton;
import de.kniffo80.mobplugin.entities.monster.walking.Wolf;
import de.kniffo80.mobplugin.entities.monster.walking.Zombie;
import de.kniffo80.mobplugin.entities.monster.walking.ZombieVillager;

public class MobManager
{
	public static HashMap<String,MobOption> Mobs = new HashMap<>();
	public static HashMap<MobsType,EntityInterface> MobsInterface= new HashMap<>();

	public static HashMap<Long,MyHeroMobCreator> AllSpawnedMobs = new HashMap<>();
	
	
	@SuppressWarnings("unchecked")
	public static void Load()
	{
		File MobFileRoot = new File(MyHeroMain.Main.getDataFolder() + "/Mobs/");
		File[] MobsFileList = MobFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(MobFileRoot.getPath());
		if(MobsFileList != null)
			for(File MobsFile : MobsFileList)
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
							Map<String, Map<String, Object>> Mobs = MobsFileYML.loadAs(Files.newInputStream(Paths.get(MobsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Mob : Mobs.entrySet())
							{
								//MyHeroMain.Main.getLogger().info("Key: " + Mob.getKey());
								if(Mob.getValue()  != null &&Mob.getValue().containsKey("Type"))
								{
									int MobID = 0;
									try
									{
										MobID = Integer.parseInt(Mob.getValue().get("Type").toString());
									}
									catch(NumberFormatException nfe)
									{
										MyHeroMain.Main.getLogger().info(LangManager.Mob_Type_Is_Not_Int.replaceAll("%Mob_Name%",Mob.getKey()));
									}
										MyHeroMobCreator mobCreator = new MyHeroMobCreator(Mob.getKey(),MobsType.findByKey(MobID));
										AbstractMobOption OptionLast = mobCreator;
										//MyHeroMain.Main.getLogger().info("Mob id: " + MobID);
										
										for(Map.Entry<String, Object> Options : Mob.getValue().entrySet())
										{
											if(MobOptionManager.ListOptions.containsKey(Options.getKey()))
											{
												//MyHeroMain.Main.getLogger().info("Option add: " + Options.getKey());
												//MyHeroMain.Main.getLogger().info("Option value: " + Options.getValue().toString());
												MobOption Option = MobOptionManager.ListOptions.get(Options.getKey().toString()).Create(OptionLast);
												Option.addMobOption(Options.getValue().toString());
												OptionLast = Option;
											}
											else if(Options.getKey().equalsIgnoreCase("drops"))
											{
												
												List<String> drops = (List<String>)Options.getValue();
												for(String s : drops)
												{
													MyHeroMain.Main.getLogger().info("Dodaje drop");
													if(DropManager.DropManager.AllDrops.containsKey(s))
													{
														OptionLast.getRoot().setDropList(s);
													}
												}
											}
										}
										MyHeroMain.Main.getLogger().info(LangManager.Mob_Load_Succes.replaceAll("%Mob_Name%",Mob.getKey()) );
										MobManager.Mobs.put(Mob.getKey(),(MobOption)OptionLast);
								}
								else
								{
									MyHeroMain.Main.getLogger().info(LangManager.Mob_Dont_Have_Type.replaceAll("%Mob_Name%",Mob.getKey()));
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
		
		MobsInterface.put(MobsType.Bat, (c,t) -> { return new Bat(c,t);} );
		MobsInterface.put(MobsType.Parrot, (c,t) -> { return new Parrot(c,t);} );
		MobsInterface.put(MobsType.Rabbit, (c,t) -> { return new Rabbit(c,t);} );
		MobsInterface.put(MobsType.Dolphin, (c,t) -> { return new Zombie(c,t);} );
		MobsInterface.put(MobsType.Squid, (c,t) -> { return new Squid(c,t);} );
		MobsInterface.put(MobsType.Chicken, (c,t) -> { return new Chicken(c,t);} );
		MobsInterface.put(MobsType.Cow, (c,t) -> { return new Cow(c,t);} );
		MobsInterface.put(MobsType.Donkey, (c,t) -> { return new Donkey(c,t);} );
		MobsInterface.put(MobsType.Horse, (c,t) -> { return new Horse(c,t);} );
		MobsInterface.put(MobsType.Llama, (c,t) -> { return new Llama(c,t);} );
		MobsInterface.put(MobsType.Mooshroom, (c,t) -> { return new Mooshroom(c,t);} );
		MobsInterface.put(MobsType.Mule, (c,t) -> { return new Mule(c,t);} );
		MobsInterface.put(MobsType.Ocelot, (c,t) -> { return new Ocelot(c,t);} );
		MobsInterface.put(MobsType.Pig, (c,t) -> { return new Pig(c,t);} );
		MobsInterface.put(MobsType.PolarBear, (c,t) -> { return new PolarBear(c,t);} );
		MobsInterface.put(MobsType.Sheep, (c,t) -> { return new Sheep(c,t);} );
		MobsInterface.put(MobsType.SkeletonHorse, (c,t) -> { return new SkeletonHorse(c,t);} );
		MobsInterface.put(MobsType.Villager, (c,t) -> { return new Villager(c,t);} );
		MobsInterface.put(MobsType.ZombieHorse, (c,t) -> { return new ZombieHorse(c,t);} );
		MobsInterface.put(MobsType.Blaze, (c,t) -> { return new Blaze(c,t);} );
		MobsInterface.put(MobsType.EnderDragon, (c,t) -> { return new EnderDragon(c,t);} );
		MobsInterface.put(MobsType.Ghast, (c,t) -> { return new Ghast(c,t);} );
		MobsInterface.put(MobsType.Vex, (c,t) -> { return new Vex(c,t);} );
		MobsInterface.put(MobsType.Wither, (c,t) -> { return new Wither(c,t);} );
		MobsInterface.put(MobsType.MagmaCube, (c,t) -> { return new MagmaCube(c,t);} );
		MobsInterface.put(MobsType.Slime, (c,t) -> { return new Slime(c,t);} );
		MobsInterface.put(MobsType.ElderGuardian, (c,t) -> { return new ElderGuardian(c,t);} );
		MobsInterface.put(MobsType.Guardian, (c,t) -> { return new Guardian(c,t);} );
		MobsInterface.put(MobsType.CaveSpider, (c,t) -> { return new CaveSpider(c,t);} );
		MobsInterface.put(MobsType.Creeper, (c,t) -> { return new Creeper(c,t);} );
		MobsInterface.put(MobsType.Enderman, (c,t) -> { return new Enderman(c,t);} );
		MobsInterface.put(MobsType.Endermite, (c,t) -> { return new Endermite(c,t);} );
		MobsInterface.put(MobsType.Evoker, (c,t) -> { return new Evoker(c,t);} );
		MobsInterface.put(MobsType.Skeleton, (c,t) -> { return new Skeleton(c,t);} );
		MobsInterface.put(MobsType.Husk, (c,t) -> { return new Husk(c,t);} );
		MobsInterface.put(MobsType.IronGolem, (c,t) -> { return new IronGolem(c,t);} );
		MobsInterface.put(MobsType.PigZombie, (c,t) -> { return new PigZombie(c,t);} );
		MobsInterface.put(MobsType.Shulker, (c,t) -> { return new Shulker(c,t);} );
		MobsInterface.put(MobsType.Silverfish, (c,t) -> { return new Silverfish(c,t);} );
		MobsInterface.put(MobsType.SnowGolem, (c,t) -> { return new SnowGolem(c,t);} );
		MobsInterface.put(MobsType.Spider, (c,t) -> { return new Spider(c,t);} );
		MobsInterface.put(MobsType.Stray, (c,t) -> { return new Stray(c,t);} );
		MobsInterface.put(MobsType.Vindicator, (c,t) -> { return new Vindicator(c,t);} );
		MobsInterface.put(MobsType.Witch, (c,t) -> { return new Witch(c,t);} );
		MobsInterface.put(MobsType.WitherSkeleton, (c,t) -> { return new WitherSkeleton(c,t);} );
		MobsInterface.put(MobsType.Wolf, (c,t) -> { return new Wolf(c,t);} );
		MobsInterface.put(MobsType.Zombie, (c,t) -> { return new Zombie(c,t);} );
		MobsInterface.put(MobsType.ZombieVillager, (c,t) -> { return new ZombieVillager(c,t);} );


		
		/*MobOption mobo = new MobOption_Name(new MyHeroMobCreator(43));
		mobo.addMobOption("test");
		Mobs.put("test",mobo);*/
		
	}

}
