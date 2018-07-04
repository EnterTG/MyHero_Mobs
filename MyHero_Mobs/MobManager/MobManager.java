package MyHero_Mobs.MobManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import MyHero_Core.Core.Data;
import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataMobs;
import MyHero_Core.Managers.LangManager;
import MyHero_Core.Managers.ResourceManager;
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
	
	
	
	@SuppressWarnings("unchecked")
	public static void Load()
	{
		//String path = MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "");
		File MobFileRoot = new File(ResourceManager.getPathTo("Mobs"));
		File[] MobsFileList = MobFileRoot.listFiles();
		//LangManager.Log(MobFileRoot.getPath());
		
		if(!MobFileRoot.exists())
			MobFileRoot.mkdirs();
		ResourceManager.saveResource("MobTest.yml","MobTest.yml", false,"Mobs");
		
		Data data = MyHeroMain.getMyHeroData();
		DataMobs datamobs = data.getDataMobs();
		if(MobsFileList != null)
			for(File MobsFile : MobsFileList)
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
							
							Map<String, Map<String, Object>> Mobs = MobsFileYML.loadAs(Files.newInputStream(Paths.get(MobsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Mob : Mobs.entrySet())
							{
								//LangManager.Log("Key: " + Mob.getKey());
								if(Mob.getValue()  != null &&Mob.getValue().containsKey("Type"))
								{
									int MobID = 0;
									try
									{
										MobID = Integer.parseInt(Mob.getValue().get("Type").toString());
									}
									catch(NumberFormatException nfe)
									{
										LangManager.Log(LangManager.Mob_Type_Is_Not_Int.replaceAll("%Mob_Name%",Mob.getKey()));
									}
									if(MobsType.findByKey(MobID) == null)
									{
										LangManager.Log(LangManager.Mob_Dont_Have_Type.replaceAll("%Mob_Name%",Mob.getKey()) );
										continue;
									}
									MyHeroMobCreator mobCreator = new MyHeroMobCreator(Mob.getKey(),MobsType.findByKey(MobID));
									AbstractMobOption OptionLast = mobCreator;
									//LangManager.Log("Mob id: " + MobID);
									
									for(Map.Entry<String, Object> Options : Mob.getValue().entrySet())
									{
										if(datamobs.OptionExist(Options.getKey()))
										{
											//LangManager.Log("Option add: " + Options.getKey());
											//LangManager.Log("Option value: " + Options.getValue().toString());
											MobOption Option = datamobs.getOption(Options.getKey().toString()).Create(OptionLast);
											Option.addMobOption(Options.getValue());
											OptionLast = Option;
										}
										else if(Options.getKey().equalsIgnoreCase("drops"))
										{
											
											List<String> drops = (List<String>)Options.getValue();
											for(String s : drops)
											{
												
												if(datamobs.DropExist(s))
												{
													OptionLast.getRoot().addDrop(s);
												}
											}
										}
									}
									LangManager.Log(LangManager.Mob_Load_Succes.replaceAll("%Mob_Name%",Mob.getKey()) );
									datamobs.addMob(Mob.getKey(), (MobOption)OptionLast);//MobManager.Mobs.put(Mob.getKey(),(MobOption)OptionLast);
								}
								else
								{
									LangManager.Log(LangManager.Mob_Dont_Have_Type.replaceAll("%Mob_Name%",Mob.getKey()));
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
		//DataMobs datamobs = MyHeroMain.getMyHeroData().getDataMobs();
		datamobs.addMobType(MobsType.Bat, (c,t) -> { return new Bat(c,t);} );
		datamobs.addMobType(MobsType.Parrot, (c,t) -> { return new Parrot(c,t);} );
		datamobs.addMobType(MobsType.Rabbit, (c,t) -> { return new Rabbit(c,t);} );
		datamobs.addMobType(MobsType.Dolphin, (c,t) -> { return new Zombie(c,t);} );
		datamobs.addMobType(MobsType.Squid, (c,t) -> { return new Squid(c,t);} );
		datamobs.addMobType(MobsType.Chicken, (c,t) -> { return new Chicken(c,t);} );
		datamobs.addMobType(MobsType.Cow, (c,t) -> { return new Cow(c,t);} );
		datamobs.addMobType(MobsType.Donkey, (c,t) -> { return new Donkey(c,t);} );
		datamobs.addMobType(MobsType.Horse, (c,t) -> { return new Horse(c,t);} );
		datamobs.addMobType(MobsType.Llama, (c,t) -> { return new Llama(c,t);} );
		datamobs.addMobType(MobsType.Mooshroom, (c,t) -> { return new Mooshroom(c,t);} );
		datamobs.addMobType(MobsType.Mule, (c,t) -> { return new Mule(c,t);} );
		datamobs.addMobType(MobsType.Ocelot, (c,t) -> { return new Ocelot(c,t);} );
		datamobs.addMobType(MobsType.Pig, (c,t) -> { return new Pig(c,t);} );
		datamobs.addMobType(MobsType.PolarBear, (c,t) -> { return new PolarBear(c,t);} );
		datamobs.addMobType(MobsType.Sheep, (c,t) -> { return new Sheep(c,t);} );
		datamobs.addMobType(MobsType.SkeletonHorse, (c,t) -> { return new SkeletonHorse(c,t);} );
		datamobs.addMobType(MobsType.Villager, (c,t) -> { return new Villager(c,t);} );
		datamobs.addMobType(MobsType.ZombieHorse, (c,t) -> { return new ZombieHorse(c,t);} );
		datamobs.addMobType(MobsType.Blaze, (c,t) -> { return new Blaze(c,t);} );
		datamobs.addMobType(MobsType.EnderDragon, (c,t) -> { return new EnderDragon(c,t);} );
		datamobs.addMobType(MobsType.Ghast, (c,t) -> { return new Ghast(c,t);} );
		datamobs.addMobType(MobsType.Vex, (c,t) -> { return new Vex(c,t);} );
		datamobs.addMobType(MobsType.Wither, (c,t) -> { return new Wither(c,t);} );
		datamobs.addMobType(MobsType.MagmaCube, (c,t) -> { return new MagmaCube(c,t);} );
		datamobs.addMobType(MobsType.Slime, (c,t) -> { return new Slime(c,t);} );
		datamobs.addMobType(MobsType.ElderGuardian, (c,t) -> { return new ElderGuardian(c,t);} );
		datamobs.addMobType(MobsType.Guardian, (c,t) -> { return new Guardian(c,t);} );
		datamobs.addMobType(MobsType.CaveSpider, (c,t) -> { return new CaveSpider(c,t);} );
		datamobs.addMobType(MobsType.Creeper, (c,t) -> { return new Creeper(c,t);} );
		datamobs.addMobType(MobsType.Enderman, (c,t) -> { return new Enderman(c,t);} );
		datamobs.addMobType(MobsType.Endermite, (c,t) -> { return new Endermite(c,t);} );
		datamobs.addMobType(MobsType.Evoker, (c,t) -> { return new Evoker(c,t);} );
		datamobs.addMobType(MobsType.Skeleton, (c,t) -> { return new Skeleton(c,t);} );
		datamobs.addMobType(MobsType.Husk, (c,t) -> { return new Husk(c,t);} );
		datamobs.addMobType(MobsType.IronGolem, (c,t) -> { return new IronGolem(c,t);} );
		datamobs.addMobType(MobsType.PigZombie, (c,t) -> { return new PigZombie(c,t);} );
		datamobs.addMobType(MobsType.Shulker, (c,t) -> { return new Shulker(c,t);} );
		datamobs.addMobType(MobsType.Silverfish, (c,t) -> { return new Silverfish(c,t);} );
		datamobs.addMobType(MobsType.SnowGolem, (c,t) -> { return new SnowGolem(c,t);} );
		datamobs.addMobType(MobsType.Spider, (c,t) -> { return new Spider(c,t);} );
		datamobs.addMobType(MobsType.Stray, (c,t) -> { return new Stray(c,t);} );
		datamobs.addMobType(MobsType.Vindicator, (c,t) -> { return new Vindicator(c,t);} );
		datamobs.addMobType(MobsType.Witch, (c,t) -> { return new Witch(c,t);} );
		datamobs.addMobType(MobsType.WitherSkeleton, (c,t) -> { return new WitherSkeleton(c,t);} );
		datamobs.addMobType(MobsType.Wolf, (c,t) -> { return new Wolf(c,t);} );
		datamobs.addMobType(MobsType.Zombie, (c,t) -> { return new Zombie(c,t);} );
		datamobs.addMobType(MobsType.ZombieVillager, (c,t) -> { return new ZombieVillager(c,t);} );


		
		/*MobOption mobo = new MobOption_Name(new MyHeroMobCreator(43));
		mobo.addMobOption("test");
		Mobs.put("test",mobo);*/
		
	}

}
